package com.pro.pcmappnew.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface JoinSpreadsheetWebService {
    @POST("1FAIpQLSfoQJirZJPAMw6wSIgZrLS_juI2DKw0wPTsWcTmFVC9p3K-bQ/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @retrofit2.http.Field("entry.2005620554") String name,
            @retrofit2.http.Field("entry.1956732355") String idnumber,
            @retrofit2.http.Field("entry.1045781291") String email,
            @retrofit2.http.Field("entry.1166974658") String phonenumber,
            @retrofit2.http.Field("entry.1065046570") String address,
            @Field("entry.839337160_month") String birthdate
    );
}
