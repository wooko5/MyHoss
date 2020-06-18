package com.example.mycanister.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycanister.Consultation;
import com.example.mycanister.R;
import com.example.mycanister.model.Hospital;
import com.example.mycanister.model.Reservation;
import com.example.mycanister.recycler.Data;
import com.example.mycanister.recycler.RecyclerAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class Frag4 extends Fragment implements Runnable {

    /*리사이클러로만 만들어보자 ^^ */
    private RecyclerAdapter adapter;
    private List<String> listTitle = new ArrayList<>();
    private List<String> listContent = new ArrayList<>();
    private View ReservationView;
    public String id;
    private String name;
    private List<Reservation> reservations = new ArrayList<>();
    private List<Hospital> hospitals = new ArrayList<>();
    //예약 시간, 예약승인 현황, 예약 대기시간, 진료실 번호를 가져오기
    //user_id를 서버에 보내서 확인시킨 후 정보 가져오기

    int pos;
    private boolean ThreadFlag = true;
    Frag4 frag4 = this;

    @SneakyThrows
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ReservationView = inflater.inflate(R.layout.activity_reservation, container, false);

        Bundle bundle = getArguments();
        id = bundle.getString("id");
        name = bundle.getString("name");
        Thread thread = new Thread(this);
        thread.start();
        return ReservationView;
    }

    private void init() {
        RecyclerView recyclerView = ReservationView.findViewById(R.id.reservation_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("알림").setMessage("예약을 취소하시겠습니까?");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        pos = position;
                        ThreadFlag = false;
                        Thread thread = new Thread(frag4);
                        thread.start();
                        Toast.makeText(getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerAdapter.MyItemDecoration());
    }

    private void getData() {
        // 임의의 데이터입니다.
        for (int i = 0; i < hospitals.size(); i++) {
            listTitle.add(hospitals.get(i).getHospital_name() +
                    "\n" + "진료과: " + hospitals.get(i).getDepartment() +
                    "\n" + "주소: " + hospitals.get(i).getAddress());
        }

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservation_status().equals("null")) {
                listContent.add("예약 시간: " + reservations.get(i).getReservation_time() +
                        "\n" + "현재 병원이 확인중입니다.");
            } else if (reservations.get(i).getOffice_number().equals("null")) {
                listContent.add("예약 시간: " + reservations.get(i).getReservation_time() +
                        "\n" + "예약 상태: " + reservations.get(i).getReservation_status());
            } else {
                listContent.add("예약 시간: " + reservations.get(i).getReservation_time() +
                        "\n" + "예약 상태: " + reservations.get(i).getReservation_status() +
                        "\n" + "대기 시간: " + reservations.get(i).getReservation_estimated_time() +
                        "\n" + "진료실 번호: " + reservations.get(i).getOffice_number());
            }
        }


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            // 각 값이 들어간 data를 adapter에 추가
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려주기
        adapter.notifyDataSetChanged();
    }

    private void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void run() {
        try {
            if (ThreadFlag == true) {
                JSONObject JsonReservation = new JSONObject();

                JsonReservation.put("user_id", id);  //기존에 받아온 아이디를 푸시

                // http client 객체
                HttpClient http = new DefaultHttpClient();

                //주소설정
                HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/getReservations");
                // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/getReservations");

                // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
                StringEntity params = new StringEntity(JsonReservation.toString(), HTTP.UTF_8);

                params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/JSON"));

                // post 방식으로 전달할 데이터 설정
                httpPost.setEntity(params);

                //서버에서 중복된 아이디 찾기, excute는 한번만 하자
                HttpResponse response = http.execute(httpPost);

                String body = EntityUtils.toString(response.getEntity());

                JSONObject obj = new JSONObject(body);

                JSONArray jArray = (JSONArray) obj.get("sendData");

                for (int i = 0; i < jArray.length(); i++) {
                    // json배열.getJSONObject(인덱스)
                    JSONObject row = jArray.getJSONObject(i);

                    Reservation reservation = new Reservation();
                    Hospital hoss = new Hospital();

                    hoss.setHospital_name(row.getString("hospital_name"));                                  //병원명
                    hoss.setAddress(row.getString("address"));                                              //주소
                    hoss.setDepartment(row.getString("department"));                                        //진료과
                    reservation.setReservation_number(row.getInt("reservation_number"));                    //예약 번호
                    reservation.setOffice_number(row.getString("office_number"));                           //진료실번호
                    reservation.setReservation_time(row.getString("reservation_time"));                     //예약시간
                    reservation.setReservation_status(row.getString("reservation_status"));                 //예약승인여부
                    reservation.setReservation_estimated_time(row.getString("reservation_estimated_time")); //대기 시간

                    reservations.add(reservation);
                    hospitals.add(hoss);
                }

                handler.sendEmptyMessage(0);
            } else {
                JSONObject JsonReservation = new JSONObject();

                JsonReservation.put("reservation_number", reservations.get(pos).getReservation_number());  //기존에 받아온 아이디를 푸시

                // http client 객체
                HttpClient http = new DefaultHttpClient();

                //주소설정
                HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/deleteReservation");
                // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/deleteReservation");

                // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
                StringEntity params = new StringEntity(JsonReservation.toString(), HTTP.UTF_8);

                params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/JSON"));

                // post 방식으로 전달할 데이터 설정
                httpPost.setEntity(params);

                //서버에서 중복된 아이디 찾기, excute는 한번만 하자
                http.execute(httpPost);

                listTitle.clear();
                listContent.clear();
                hospitals.clear();
                reservations.clear();
                ThreadFlag = true;

                refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {

        @SneakyThrows
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            init();
            getData();
        }
    };
}