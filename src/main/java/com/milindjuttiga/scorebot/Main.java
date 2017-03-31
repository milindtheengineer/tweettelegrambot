/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;

/**
 *
 * @author milind
 */
public class Main {
    public static final AuthorizationKeys akeys = new AuthorizationKeys();
    public static DatabaseHelper databaseHelper = new DatabaseHelper();
    public static void main (String[] args) {
        TelegramProcessor telegramProcessor = new TelegramProcessor();
        telegramProcessor.startRun();
        TweetProcessor tweetProcessor = new TweetProcessor();
        Thread th = new Thread(tweetProcessor);
        th.start();
    }
}
