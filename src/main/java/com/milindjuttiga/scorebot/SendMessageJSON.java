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
public class SendMessageJSON {

    private int chat_id;
    private String text;
    private InlineKeyboardMarkup reply_markup;

    public SendMessageJSON(int chat_id, String text, InlineKeyboardMarkup reply_markup) {
        this.chat_id = chat_id;
        this.text = text;
        this.reply_markup = reply_markup;
    }

    

}