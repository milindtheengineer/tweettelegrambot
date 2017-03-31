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
public class InlineKeyboardButton {
    private String text;
    private String callback_data;

    public InlineKeyboardButton(String text, String callback_data) {
        this.text = text;
        this.callback_data = callback_data;
    }   
}

