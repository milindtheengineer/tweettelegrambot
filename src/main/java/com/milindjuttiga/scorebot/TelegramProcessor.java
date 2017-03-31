/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;
import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author milind
 */
public class TelegramProcessor {
    public void startRun() {
        port(5000);
        post("/mybot", (req, res) -> {
            Gson gson = new GsonBuilder().create();
            TelegramUserMessage tum = gson.fromJson(req.body(), TelegramUserMessage.class);
            res.type("application/json");
            if(!Main.databaseHelper.retrieveUsers().contains(tum.getMessage().getChat().getId())) {
                Main.databaseHelper.addUser(tum.getMessage().getChat().getId());
                String outp = String.format("{\"method\": \"sendMessage\","
                    + " \"text\":\"you got in!\", \"chat_id\":\"%d\"}", tum.getMessage().getChat().getId());
            return outp;
            }
            String outp = String.format("{\"method\": \"sendMessage\","
                    + " \"text\":\"you are already in!\", \"chat_id\":\"%d\"}", tum.getMessage().getChat().getId());
            return outp;
        });
    }
    
    
}
