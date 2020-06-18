package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycanister.fragment.Frag1;
import com.example.mycanister.fragment.Frag2;
import com.example.mycanister.fragment.Frag3;
import com.example.mycanister.fragment.Frag4;
import com.example.mycanister.model.Reservation;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FinalActivity extends AppCompatActivity implements Runnable{

    Reservation reservation = new Reservation();
    private TextView text_title;          //리스트액티비티 에서 가져옴
    private TextView text_reserveDate;    //컨설테이션 액티비티에서 가져옴
    private TextView text_name;           //컨설테이션 액티비티에서 가져옴
    private TextView text_symptom;        //컨설테이션 액티비티에서 가져옴
    private TextView text_clinic;
    private TextView text_address;
    private TextView text_hours;
    private ProgressBar progressBarFinal;
    private String message;
    private String id;
    private Frag1 frag1 = new Frag1();
    private Frag2 frag2 = new Frag2();
    private Frag3 frag3 = new Frag3();
    private Frag4 frag4 = new Frag4();
    String title;
    String name;
    String clinic;
    String address;
    String hours;
    String reserveDate;
    String symptom;
    String hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        progressBarFinal = (ProgressBar) findViewById(R.id.progressBar_final);
        text_title = (TextView)findViewById(R.id.text_title);
        text_reserveDate = (TextView)findViewById(R.id.text_reserveDate);
        text_name = (TextView)findViewById(R.id.text_name);
        text_symptom = (TextView)findViewById(R.id.text_symptom);
        text_clinic = (TextView)findViewById(R.id.text_clinic);
        text_address = (TextView)findViewById(R.id.text_address);
        text_hours = (TextView)findViewById(R.id.text_hours);

        /*데이터수신*/
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");       //병원명
        name = intent.getStringExtra("name");         //예약자명
        clinic = intent.getStringExtra("clinic");     //진료과
        address = intent.getStringExtra("address");   //주소
        hours = intent.getStringExtra("hours");       //대기 시간
        reserveDate = intent.getStringExtra("reserveDate");  //예약일시
        symptom = intent.getStringExtra("symptom");      //증상
        hospital_id = intent.getStringExtra("hospital_id");      //병원id
        /*데이터수신*/

        text_title.setText(title);
        text_name.setText("예약자: "+name);
        text_clinic.setText(clinic+"입니다");
        text_reserveDate.setText("예약일시: "+reserveDate);
        text_symptom.setText("증상: "+symptom);
        text_address.setText(address);
        text_hours.setText(hours);
    }

    public void FinalOnClick(View v) {
        if(reserveDate != null){
            Intent intent;
            progressBarFinal.setProgress(progressBarFinal.getProgress() + 25);

            /*백그라운드 스래드 생성*/
            Thread thread = new Thread(this);
            thread.start();
            /*백그라운드 스래드 생성*/

            Bundle bundle = new Bundle();
            bundle.putString("id",id);
            bundle.putString("name", name);
            frag1.setArguments(bundle);
            frag2.setArguments(bundle);
            frag3.setArguments(bundle);
            frag4.setArguments(bundle);

            Toast.makeText(this, "예약이 완료되었습니다", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, HomeActivity2.class);
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            startActivity(intent);
        }
        else Toast.makeText(this, "예약 날짜를 확인해주세요", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, Consultation.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
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
            JSONObject JsonUser = new JSONObject();

            // post 방식으로 전달할 값들을 postData 객체에 집어 넣는다.
            JsonUser.put("user_id", id);
            JsonUser.put("hospital_id", hospital_id);
            JsonUser.put("reservation_time", reserveDate);

            // http client 객체
            HttpClient http = new DefaultHttpClient();

            //주소설정
            HttpPost httpPost = new HttpPost("http://52.78.117.54:8080/test2.0/android/addReservation");
            // HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/test2/android/addReservation");

            // url encoding이 필요한 값들(한글, 특수문자) : 한글은 인코딩안해주면 깨짐으로 인코딩을 한다. 고쳐봐야함
            StringEntity params = new StringEntity(JsonUser.toString(), HTTP.UTF_8);

            params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/JSON"));

            // post 방식으로 전달할 데이터 설정
            httpPost.setEntity(params);

            http.execute(httpPost);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

    };
}
