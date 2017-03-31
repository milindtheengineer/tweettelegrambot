/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 *
 * @author milind
 */
public interface TelegramApi {
    @POST("sendMessage")
    Call<SendMessageJSON> sendMessage(@Body SendMessageJSON body);
    
}

