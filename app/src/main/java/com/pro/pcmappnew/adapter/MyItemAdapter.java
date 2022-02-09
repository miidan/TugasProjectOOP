package com.pro.pcmappnew.adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.firebase.storage.StorageReference;
import com.pro.pcmappnew.R;
import com.pro.pcmappnew.utils.Item;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyItemViewHolder> {

    private Context context;
    private List<Item> itemList;

    public MyItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item_list,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        Glide.with(context)
                .load(itemList.get(position).getItemImage())
                .centerCrop()
                .into(holder.imageView);
        holder.textPrice.setText(new StringBuilder("Rp.").append(itemList.get(position).getItemPrice()));
        holder.textName.setText(new StringBuilder().append(itemList.get(position).getItemName()));
        holder.textCategory.setText(new StringBuilder().append(itemList.get(position).getItemCategory()));
        holder.textDesc.setText(new StringBuilder().append(itemList.get(position).getItemDesc()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.imageView)
        ImageView imageView;
    @BindView(R.id.txtItemName)
        TextView textName;
    @BindView(R.id.txtItemPrice)
        TextView textPrice;
    @BindView(R.id.txtItemDesc)
        TextView textDesc;
        @BindView(R.id.txtItemCategory)
        TextView textCategory;

    private Unbinder unbinder;

        public MyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder= ButterKnife.bind(this, itemView);
        }
    }
}
