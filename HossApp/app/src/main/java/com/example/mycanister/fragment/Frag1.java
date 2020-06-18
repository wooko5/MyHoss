package com.example.mycanister.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycanister.R;

public class Frag1 extends Fragment {
    private View view;
    String id;
    String name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        //Bundle bundle = getArguments();
        //id = bundle.getString("id");
        //name = bundle.getString("name");
        return view;
    }
}
