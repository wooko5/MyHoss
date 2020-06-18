package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycanister.fragment.Frag4;
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
import lombok.SneakyThrows;

public class LoginActivity extends AppCompatActivity implements Runnable {

    User user = new User();
    JSONObject obj;
    private String message;
    private EditText editText_id;
    private EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_password = (EditText) findViewById(R.id.editText_password);
    }

    public void LoginOnClick(View v){
        Intent intent;
            switch (v.getId()) {
                case R.id.button_login:
                    Thread thread = new Thread(this);
                    thread.start();
                    break;
                case R.id.button_join:
                    intent = new Intent(this, JoinActivity.class);
                    startActivity(intent);
                    break;
            }
    }

    @Override
    public void run() {
        try {
            JSONObject JsonUser = new JSONObject();

            JsonUser.put("user_id", editText_id.getText().toString());
            JsonUser.put("user_pw", editText_password.getText().toString());

            // http client 객체
            HttpClient http = new DefaultHttpClient();

            //주소설정
            HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/login");
            // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/login");

            // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
            StringEntity params = new StringEntity(JsonUser.toString(), HTTP.UTF_8);

            params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/JSON"));

            // post 방식으로 전달할 데이터 설정
            httpPost.setEntity(params);

            //서버에서 중복된 아이디 찾기, excute는 한번만 하자
            HttpResponse response = http.execute(httpPost);

            String body = EntityUtils.toString(response.getEntity());

            obj = new JSONObject(body);

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
            message = obj.getString("message");

            if (message.equals("로그인 실패")) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            } else if (message.equals("로그인 성공")) {
                user.setUser_name(obj.getString("user_name"));
                user.setUser_birth(obj.getString("user_birth"));
                user.setUser_address(obj.getString("user_address"));
                user.setUser_Phone(obj.getString("user_Phone"));
                user.setUser_email(obj.getString("user_email"));



                Intent intent = new Intent(getApplicationContext(), HomeActivity2.class);  //홈화면으로 바꿀생각
                String id = editText_id.getText().toString();                             //유저의 아이디
                String name = user.getUser_name();
                intent.putExtra("id", id);                       //id 보내기
                intent.putExtra("name", name);                   //이름 보내기
                startActivity(intent);
            }
        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, HomeActivity2.class);
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
* public void run() {
        try {
            JSONObject jsonObj = new JSONObject();

            jsonObj.put("user_id","admin2");
            jsonObj.put("user_pw","adminpw");

            HttpClient http = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://13.125.82.92:8080/test2.0/jsonLogin");

            StringEntity stringEntity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/JSON"));

            httpPost.setEntity(stringEntity);
            HttpResponse response = http.execute(httpPost);

            String body = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(body);

            handler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
* */