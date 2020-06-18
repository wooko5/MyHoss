package com.example.mycanister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private TextView text_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*데이터 수신*/
        //text_id = (TextView)findViewById(R.id.text_id);
        //Intent intent = getIntent();
        //String id = intent.getStringExtra("id");
        //text_id.setText(id+"님 환영합니다");
        /*데이터 수신*/

    }

    /*액티비티가 정지될 때 삭제하기 -> 스크린에서 액티비티가 사라지면 삭제하기*/
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

}
