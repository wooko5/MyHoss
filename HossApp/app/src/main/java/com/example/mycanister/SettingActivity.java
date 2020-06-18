package com.example.mycanister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.mycanister.recycler.Data;
import com.example.mycanister.recycler.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private String id;
    private String name;
    private RecyclerAdapter adapter_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");

        init();    //초기화
        getData(); // 데이터 불러오기
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recycler_setting);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter_setting = new RecyclerAdapter();
        recyclerView.setAdapter(adapter_setting);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("오재욱(팀원)", "한조일(팀원)", "김강현(팀장)",
                "방성민(팀원)");
        List<String> listContent = Arrays.asList(
                "wooko5@hansung.ac.kr         Frontend",
                "joilhans@hansung.ac.kr         Backend",
                "khkim@gmail.com                   Backend",
                "smbang@naver.com                Frontend and Director"
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.ic_face_24dp,
                R.drawable.ic_adb_black_24dp,
                R.drawable.ic_people_24dp,
                R.drawable.ic_person_24dp
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가
            adapter_setting.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려주기
        adapter_setting.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, HomeActivity2.class);
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
}