package com.example.mycanister.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mycanister.R;
import com.example.mycanister.model.Clinic;
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

public class Frag3 extends Fragment implements Runnable {

    private RecyclerAdapter adapter;
    private List<String> listTitle = new ArrayList<>();
    private List<String> listContent = new ArrayList<>();
    private View myinfoView;
    private String id;
    private String name;
    private List<String> hospital_names = new ArrayList<>();
    private List<String> doctor_names = new ArrayList<>();
    private List<Clinic> clinics = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myinfoView = inflater.inflate(R.layout.activity_myinfo, container, false);
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        name = bundle.getString("name");
        Thread thread = new Thread(this);
        thread.start();
        return myinfoView;
    }

    private void init() {

        RecyclerView recyclerView = myinfoView.findViewById(R.id.myinfo_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerAdapter.MyItemDecoration());
    }

    private void getData() {
        // 임의의 데이터입니다.
        for (int i = 0; i < hospital_names.size(); i++) {
            listTitle.add(hospital_names.get(i) + "\n"
                    + "담당의: " + doctor_names.get(i));
        }

        for (int i = 0; i < clinics.size(); i++) {
            if (clinics.get(i).getPrescription_drugname().equals("") || clinics.get(i).getPrescription_drugname().equals("null")) {
                listContent.add("진료 날짜: " + clinics.get(i).getClinic_date() +
                        "\n" + "진료 내용: " + clinics.get(i).getClinic_treatment());
            } else
                listContent.add("진료 날짜: " + clinics.get(i).getClinic_date() +
                        "\n" + "진료 내용: " + clinics.get(i).getClinic_treatment() +
                        "\n" + "처방약: " + clinics.get(i).getPrescription_drugname() +
                        "\n" + "복용법: " + clinics.get(i).getPrescription_dosage() +
                        "\n" + "주의사항: " + clinics.get(i).getPrescription_caution());
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

    @Override
    public void run() {
        try {
            JSONObject JsonReservation = new JSONObject();

            JsonReservation.put("user_id", id);  //기존에 받아온 아이디를 푸시

            // http client 객체
            HttpClient http = new DefaultHttpClient();

            //주소설정
            HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/getClinics");
            // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/getClinics");

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
                Clinic clinic = new Clinic();
                hospital_names.add(row.getString("hospital_name"));                      //병원이름만
                doctor_names.add(row.getString("doctor_name"));                          //의사이름만
                clinic.setClinic_date(row.getString("clinic_date"));                     //진료 일자
                clinic.setClinic_treatment(row.getString("clinic_treatment"));           //진료 내용
                clinic.setPrescription_drugname(row.getString("prescription_drugname")); //약 이름
                clinic.setPrescription_dosage(row.getString("prescription_dosage"));     //복용방법
                clinic.setPrescription_caution(row.getString("prescription_caution"));   //주의사항
                clinics.add(clinic);
            }
            handler.sendEmptyMessage(0);
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
