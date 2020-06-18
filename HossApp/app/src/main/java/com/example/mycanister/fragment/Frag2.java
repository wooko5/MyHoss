package com.example.mycanister.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.mycanister.R;
import com.example.mycanister.recycler.Data;
import com.example.mycanister.recycler.RecyclerAdapter;

import java.util.Arrays;
import java.util.List;

/*리스트 프레그먼트*/
public class Frag2 extends Fragment {

    /*리스트가 한 개만 나옴 고쳐야함*/
    String id;
    String name;
    private View moonlightView;
    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        moonlightView = inflater.inflate(R.layout.activity_moonlight, container, false);
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        name = bundle.getString("name");
        init();
        getData();
        return moonlightView;
    }

    private void init() {
        RecyclerView recyclerView = moonlightView.findViewById(R.id.moonlight_listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter= new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerAdapter.MyItemDecoration());
    }


    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("소화병원(의료법인소화병원)", "연세곰돌이소아청소년과의원", "세곡달빛의원",
                "미즈아이프라자산부인과의원");
        List<String> listContent = Arrays.asList(
                "서울특별시 용산구 청파로 383 (서계동, 소화아동병원)         02-705-9000",
                "서울특별시 서초구 방배로 226, 넥센강남타워 3층 (방배동)       02-596-4585",
                "서울특별시 강남구 헌릉로569길 27, 2층 (세곡동, 2층일부(201-2호,202호))                   02-459-0119",
                "서울특별시 노원구 동일로 1669, 수락프라자 (상계동)               02-931-5224"
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.ic_local_hospital_black_24dp,
                R.drawable.ic_local_hospital_black_24dp,
                R.drawable.ic_local_hospital_black_24dp,
                R.drawable.ic_local_hospital_black_24dp
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려주기
        adapter.notifyDataSetChanged();
    }

}
