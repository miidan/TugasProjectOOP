package com.pro.pcmappnew;

import android.app.Notification;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pro.pcmappnew.adapter.MyItemAdapter;
import com.pro.pcmappnew.listener.CartLoadListener;
import com.pro.pcmappnew.listener.ItemLoadListener;
import com.pro.pcmappnew.utils.Cart;
import com.pro.pcmappnew.utils.Item;
import com.pro.pcmappnew.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketActivity extends AppCompatActivity implements CartLoadListener, ItemLoadListener {
    private RecyclerView recycler_item;
    @BindView(R.id.activity_market)
    RelativeLayout mainLayout;
    @BindView(R.id.badge)
    Notification badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;

    CartLoadListener cartLoadListener;
    ItemLoadListener itemLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        recycler_item = (RecyclerView)findViewById(R.id.recycler_item);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MarketActivity.this);
        init();
        loadItemFromFirebase();
    }

    private void loadItemFromFirebase() {
        List<Item> itemModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Item")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot itemSnapshot:snapshot.getChildren()){
                                Item item = itemSnapshot.getValue(Item.class);
                                item.setItemId(itemSnapshot.getKey());
                                itemModels.add(item);
                            }
                            itemLoadListener.onItemLoadSuccess(itemModels);

                        }
                        else itemLoadListener.onItemLoadFailed("Tidak bisa menemukan Item");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        itemLoadListener.onItemLoadFailed(error.getMessage());
                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        itemLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recycler_item.setLayoutManager(gridLayoutManager);
        recycler_item.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onCartLoadSuccess(List<Cart> cartList) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }

    @Override
    public void onItemLoadSuccess(List<Item> itemList) {
        MyItemAdapter adapter = new MyItemAdapter(this, itemList);
        recycler_item.setAdapter(adapter);
    }

    @Override
    public void onItemLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}

