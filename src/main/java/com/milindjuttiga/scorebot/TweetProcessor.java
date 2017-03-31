/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;

import static com.milindjuttiga.scorebot.Main.akeys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author milind
 */
public class TweetProcessor implements Runnable {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    TelegramApi apiService
            = retrofit.create(TelegramApi.class);
    
    

    public static final String BASE_URL = String.format
        ("https://api.telegram.org/bot%s/", akeys.getTelegramBotAccessToken());


    public void run() {
        try {
            startTweetProcessing();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startTweetProcessing() throws TwitterException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(akeys.getTwitterkeys().getConsumerKey())
                .setOAuthConsumerSecret(akeys.getTwitterkeys().getConsumerSecret())
                .setOAuthAccessToken(akeys.getTwitterkeys().getAccessToken())
                .setOAuthAccessTokenSecret(akeys.getTwitterkeys().getAccessTokenSecret());
        TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                for(int user:Main.databaseHelper.retrieveUsers()) {
                    sendTweetToBot(status.getText(), user);
                }
                System.out.println(status.getText()); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice sdn) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onException(Exception excptn) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        FilterQuery tweetFilterQuery = new FilterQuery(); // See 
        tweetFilterQuery.follow(148705581);
        twitterStream.addListener(listener);
        twitterStream.filter(tweetFilterQuery);
    }

    public void sendTweetToBot(String text, int user_id) {
        InlineKeyboardButton keyboard =  new InlineKeyboardButton("okay!", "great!");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.addKeyboardButton(keyboard);
        SendMessageJSON user = new SendMessageJSON(user_id, text, markup);
        Call<SendMessageJSON> call = apiService.sendMessage(user);
        call.enqueue(new Callback<SendMessageJSON>() {

            @Override
            public void onFailure(Call<SendMessageJSON> call, Throwable t) {
                System.out.println("Failure");
            }

            @Override
            public void onResponse(Call<SendMessageJSON> call, Response<SendMessageJSON> rspns) {
                System.out.println(rspns); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }
}
