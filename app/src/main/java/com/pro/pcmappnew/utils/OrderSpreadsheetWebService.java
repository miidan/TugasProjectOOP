package com.pro.pcmappnew.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Susi on 10/6/2018.
 */

public interface OrderSpreadsheetWebService {
    @POST("1FAIpQLSehLDL7bQ0LPKK866Tm_GtQW1bSGs05mPEXaxAWqVLCGMpTxA/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.2005620554") String name,
            @Field("entry.1045781291") String email,
            @Field("entry.1166974658") String phonenumber,
            @Field("entry.836844223") String ordertype,
            @Field("entry.839337160") String orderdesc
    );
}

