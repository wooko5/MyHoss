package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycanister.model.Hospital;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class Consultation extends AppCompatActivity{

    private TextView consultation_title;
    private TextView consultation_clinic;
    private TextView consultation_name;
    private TextView consultation_address;
    private TextView consultation_hours;
    private EditText consultation_reserveDate;
    private EditText consultation_symptom;
    private Hospital hospital;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        hospital = (Hospital) intent.getSerializableExtra("hospital");


        /*Consultation 함수*/
        consultation_title = (TextView)findViewById(R.id.consultation_title);
        consultation_clinic = (TextView)findViewById(R.id.consultation_clinic);
        consultation_name = (TextView)findViewById(R.id.consultation_name);
        consultation_address = (TextView)findViewById(R.id.consultation_address);
        consultation_hours = (TextView)findViewById(R.id.consultation_hours);
        consultation_reserveDate = (EditText)findViewById(R.id.consultation_reserveDate);
        consultation_symptom = (EditText)findViewById(R.id.consultation_symptom);
        /*Consultation 함수*/

        consultation_title.setText(hospital.getHospital_name());
        consultation_clinic.setText("진료과: "+hospital.getDepartment());
        consultation_hours.setText("운영시간: "+hospital.getHours());
        consultation_address.setText("병원주소: "+hospital.getAddress());
        consultation_name.setText("예약자: "+name);

        this.findViewById(R.id.consultation_reserveBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent; //인텐트생성
                intent = new Intent(getApplicationContext(), FinalActivity.class);

                String title = consultation_title.getText().toString();
                String clinic = consultation_clinic.getText().toString();
                String hours = consultation_hours.getText().toString();
                String address = consultation_address.getText().toString();
                String reserveDate = consultation_reserveDate.getText().toString();
                String symptom = consultation_symptom.getText().toString();

                if(!reserveDate.equals("")){
                    intent.putExtra("title", title);
                    intent.putExtra("clinic", clinic);
                    intent.putExtra("hours", hours);
                    intent.putExtra("address", address);
                    intent.putExtra("name", name);
                    intent.putExtra("reserveDate", reserveDate);
                    intent.putExtra("symptom", symptom);
                    intent.putExtra("id",id);
                    intent.putExtra("hospital_id",hospital.getHospital_id());

                    startActivity(intent);
                }
                else Toast.makeText(getApplication(), "예약 날짜를 확인해주세요", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /* 백버튼 누를때 이전 화면으로 인텐트넘어가기 */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, HomeActivity2.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
