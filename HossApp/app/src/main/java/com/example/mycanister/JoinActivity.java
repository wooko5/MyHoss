package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mycanister.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity implements Runnable {

    User user = new User();
    private EditText ed_name;
    private EditText ed_birth;
    private EditText ed_phone;
    private EditText ed_id;
    private EditText ed_password1;
    private EditText ed_password2;  //ed_password1랑 비교해서
    private EditText ed_address;
    private EditText ed_email;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_birth = (EditText) findViewById(R.id.ed_birth);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_id = (EditText) findViewById(R.id.ed_id);
        ed_password1 = (EditText) findViewById(R.id.ed_password1);
        ed_password2 = (EditText) findViewById(R.id.ed_password2);
        ed_address = (EditText) findViewById(R.id.ed_address);
        ed_email = (EditText) findViewById(R.id.ed_email);

    }

    public void JoinOnClick(View v) {

        user.setUser_id(ed_id.getText().toString());
        user.setUser_pw(ed_password1.getText().toString());
        user.setUser_address(ed_address.getText().toString());
        user.setUser_birth(ed_birth.getText().toString());
        user.setUser_name(ed_name.getText().toString());
        user.setUser_email(ed_email.getText().toString());
        user.setUser_Phone(ed_phone.getText().toString());

        // 백그라운드 스래드 생성
        Thread thread = new Thread(this);
        thread.start();

        Intent intent;
        if (v.getId() == R.id.button_register) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void run() {
        try {
            JSONObject JsonUser = new JSONObject();

            // post 방식으로 전달할 값들을 postData 객체에 집어 넣는다.
            JsonUser.put("user_id", user.getUser_id());
            JsonUser.put("user_pw", user.getUser_pw());
            JsonUser.put("user_name", user.getUser_name());
            JsonUser.put("user_birth", user.getUser_birth());
            JsonUser.put("user_Phone", user.getUser_Phone());
            JsonUser.put("user_address", user.getUser_address());
            JsonUser.put("user_email", user.getUser_email());

            // http client 객체
            HttpClient http = new DefaultHttpClient();

            //주소설정
            HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/addUser");
            // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/addUser");

            // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
            StringEntity params = new StringEntity(JsonUser.toString(), HTTP.UTF_8);

            params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/JSON"));

            // post 방식으로 전달할 데이터 설정
            httpPost.setEntity(params);

            //서버에서 중복된 아이디 찾기, excute는 한번만 하자
            HttpResponse response = http.execute(httpPost);

            String body = EntityUtils.toString(response.getEntity());

            JSONObject obj = new JSONObject(body);

            message = obj.getString("message");

            //핸들러시동키
            handler.sendEmptyMessage(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

    };

    /*백버튼 클릭 시 할 효과 -> 로그인 화면으로 돌아간다*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /*액티비티가 정지될 때 삭제하기 -> 스크린에서 액티비티가 사라지면 삭제하기*/
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

}

/*
   서버에서 자료를 가져오기 위한 함수
 // 네트워크 작업임으로 백그라운드 스레드로 동작시키기 위함(의무)
   @Override
    public void run() {
        try {
            // httpclient-4.2.2.jar와 httpcore-4.2.2.jar를 mvnrepository에서 찾아 Files에 Download jars를 해 받음
            // app/libs에 추가
            // 이때, android상태에서는 libs가 안보임으로 project로 변경해서
            // lib에 받은 jar파일 2개를 넣어준다.

            // http client 객체
            HttpClient http = new DefaultHttpClient();
            // post 방식으로 전송하는 객체
            // HttpPost httpPost = new HttpPost("http://13.125.82.92:8080/test2.0/json.do");
            HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/json.do");
            // http클라이언트.execute(httppost객체) : 웬서버에 데이터를 전달
            // 결과(json)가 response로 넘어옴
            HttpResponse response = http.execute(httpPost);
            // body에 json 스트링이 넘어옴
            String body = EntityUtils.toString(response.getEntity());
            // string을 JSONObject로 변환
            JSONObject jsonObj = new JSONObject(body);
            // json객체.get("변수명")
            JSONArray jArray = (JSONArray) jsonObj.get("sendData");
            for (int i = 0; i < jArray.length(); i++) {
                // json배열.getJSONObject(인덱스)
                JSONObject row = jArray.getJSONObject(i);
                Reservation reservation = new Reservation();
                reservation.setUser_number(row.getString("user_number"));
                reservation.setHospital_id(row.getString("hospital_id"));
                reservation.setReservation_number(row.getString("reservation_number"));
                reservation.setReservation_time(row.getString("reservation_time"));
                reservation.setReservation_status(row.getString("reservation_status"));
                reservation.setReservation_estimated_time(row.getString("reservation_estimated_time"));
                reservation.setOffice_number(row.getString("office_number"));
                // ArrayList에 add
                reservations.add(reservation);
            }
           // 핸들러에게 메시지를 요청
            handler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 핸들러
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 어댑터 생성
            String[] str = new String[reservations.size()];
            for (int i = 0; i < str.length; i++) {
                Reservation reservation = reservations.get(i);
                str[i] = reservation.getReservation_number() + "(" + reservation.getReservation_status() + ")";
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, str);
            // adapter와 data 바인딩
           listView1.setAdapter(adapter);
       }

    };
*/