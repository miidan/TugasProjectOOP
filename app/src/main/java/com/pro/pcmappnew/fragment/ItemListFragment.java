package com.pro.pcmappnew.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pro.pcmappnew.MarketActivity;
import com.pro.pcmappnew.R;

public class ItemListFragment extends Fragment {
    public ItemListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_item_list, container, false);
        //openActivity();
    }

    private void openActivity() {
        Intent intent = new Intent(ItemListFragment.this.getActivity(), MarketActivity.class);
        startActivity(intent);
    }
}
