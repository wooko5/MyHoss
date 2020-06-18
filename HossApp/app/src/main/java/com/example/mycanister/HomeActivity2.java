package com.example.mycanister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.mycanister.fragment.Frag1;
import com.example.mycanister.fragment.Frag2;
import com.example.mycanister.fragment.Frag3;
import com.example.mycanister.fragment.Frag4;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity2 extends AppCompatActivity {

    /*로그인 함수*/
    TextView text_id;   //~님 환영합니다
    String id;
    String name;
    /*로그인 함수*/

    /* bottom navigation */
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private Frag1 frag1;                               //최초설정을 위한 홈 화면
    private Fragment selectedFragment;
    /* bottom navigation */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        /* bottom navigation  */
        frag1 = new Frag1();
        selectedFragment = null;
        bottomNavigationView = findViewById(R.id.bottomNavi_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.naviFrame_home, frag1).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        /* bottom navigation  */

        /*데이터 수신*/
        text_id = (TextView) findViewById(R.id.text_id);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        text_id.setText(name + "님 환영합니다");
        /*데이터 수신*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle bundle = new Bundle();
            switch (item.getItemId()) {

                case R.id.action_map_hospital:  //홈으로 고쳐야함
                    selectedFragment = new Frag1();
                    bundle.putString("id",id);
                    bundle.putString("name", name);
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_home:          //달빛병원으로 고쳐야함
                    selectedFragment = new Frag2();
                    bundle.putString("id",id);
                    bundle.putString("name", name);
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_map_pharmacy:  //마이메뉴로 바꿔야함
                    selectedFragment = new Frag3();
                    bundle.putString("id",id);
                    bundle.putString("name", name);
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_reserve:       //그대로
                    selectedFragment = new Frag4();
                    bundle.putString("id",id);
                    bundle.putString("name", name);
                    selectedFragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.naviFrame_home, selectedFragment).commit();
            return true;
        }
    };

    public void Home2OnClick(View v) {
        Intent intent;
        String clinic = null;

        switch (v.getId()){
            case R.id.imageButton_settings:
                intent = new Intent(this, SettingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                return;
            case R.id.imageButton_login:
                if(id!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("알림").setMessage("이미 로그인 상태입니다");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return;
                }

                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return;
            case R.id.publicHealth: //고쳐야할듯 보건소 나오도록!
                intent = new Intent(this, ListActivity1.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                return;
            case R.id.emergency:
                intent = new Intent(this, MapsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                return;
        }

        intent = new Intent(this, ListActivity1.class);

        switch (v.getId()) {
            case R.id.ibiinhugwa:
                clinic = "이비인후과";
                break;
            case R.id.jeonghyeong: //고쳐야할듯 정형외과만 나오도록!
                clinic = "정형외과";
                break;
            case R.id.naegwa: //고쳐야할듯 내과만 나오도록!
                clinic = "내과";
                break;
            case R.id.moonLight: //고쳐야할듯 달빛병원 나오도록!
                clinic = "달빛병원";
                break;
            case R.id.chigwa: //고쳐야할듯 치과만 나오도록!
                clinic = "치과";
                break;
            case R.id.binyogigwa: //고쳐야할듯 비뇨기과 나오도록!
                clinic = "비뇨기과";
                break;
            case R.id.jungsin: //고쳐야할듯 정신과 나오도록!
                clinic = "정신과";
                break;
            case R.id.psychology: //고쳐야할듯 정신과 나오도록!
                clinic = "성형외과";
                break;
            case R.id.soagwa: //고쳐야할듯 소아과 나오도록!
                clinic = "소아과";
                break;
            case R.id.pregnant: //고쳐야할듯 산부인과 나오도록!
                clinic = "산부인과";
                break;
            case R.id.ophthalmology: //고쳐야할듯 안과 나오도록!
                clinic = "안과";
                break;
        }
        intent.putExtra("id",id);
        intent.putExtra("name", name);
        intent.putExtra("clinic", clinic);
        startActivity(intent);
    }

}
