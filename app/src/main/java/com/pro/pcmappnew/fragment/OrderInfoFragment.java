package com.pro.pcmappnew.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pro.pcmappnew.R;

public class OrderInfoFragment extends Fragment {


    public OrderInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_info,
                container, false);


        return rootView;

    }

}