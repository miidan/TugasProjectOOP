package com.pro.pcmappnew.listener;

import com.pro.pcmappnew.utils.Item;

import java.util.List;

public interface  ItemLoadListener {
    void onItemLoadSuccess(List<Item> itemList);
    void onItemLoadFailed(String message);
}

