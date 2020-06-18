package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.example.mycanister.model.Hospital;
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

public class ListActivity1 extends AppCompatActivity implements Runnable {
    
    private String clinic;
    private String id;
    private String name;
    private RecyclerAdapter adapter;
    private List<Hospital> hospitals = new ArrayList<>();
    private List<Hospital> hospitals2 = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();
    private List<String> listContent = new ArrayList<>();
    private List<String> listIndex = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        clinic = intent.getStringExtra("clinic");
        Thread thread = new Thread(this);
        thread.start();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //클릭했을때 어떤 놈이 나올지 생각해보자
                //병원 아이디를 꼭 가져와야함, 병원명 등등
                //병원아이디 사용자아이디 예약시간(내가 설정)
                //리스트액티비티 -> 병원아이디, 사용자 아이디, 예약시간(내가 설정) -> 최종 예약확인창
                Intent intent;
                intent = new Intent(getApplicationContext(), Consultation.class);
                intent.putExtra("hospital", hospitals2.get(position));
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerAdapter.MyItemDecoration());
    }

    private void getData() {
        // 임의의 데이터입니다.
        for(int i=0; i<hospitals.size(); i++){
            if(hospitals.get(i).getDepartment().equals(clinic)){
                listTitle.add(hospitals.get(i).getHospital_name());
                listContent.add("주소: "+hospitals.get(i).getAddress()+"\n"+
                        "영업시간: " +hospitals.get(i).getHours());
                listIndex.add(i+"");
                hospitals2.add(hospitals.get(i));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, HomeActivity2.class);
        intent.putExtra("id", id);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    /*액티비티가 정지될 때 삭제하기 -> 스크린에서 액티비티가 사라지면 삭제하기*/
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void run() {
        try {
            JSONObject JsonList = new JSONObject();

            // http client 객체
            HttpClient http = new DefaultHttpClient();

            //주소설정
            HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/getHospitals");
            // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/getHospitals");

            // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
            StringEntity params = new StringEntity(JsonList.toString(), HTTP.UTF_8);

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
                Hospital hoss = new Hospital();
                hoss.setHospital_id(row.getString("hospital_id"));
                hoss.setHospital_name(row.getString("hospital_name"));
                hoss.setAddress(row.getString("address"));
                hoss.setDepartment(row.getString("department"));
                hoss.setHours(row.getString("hours"));

                // ArrayList에 add
                hospitals.add(hoss);
            }

            handler.sendEmptyMessage(0);

        }catch (Exception e){
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