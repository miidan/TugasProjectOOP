package com.pro.pcmappnew.listener;

import com.pro.pcmappnew.utils.Cart;
import com.pro.pcmappnew.utils.Item;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<Cart> cartList);
    void onCartLoadFailed(String message);
}
