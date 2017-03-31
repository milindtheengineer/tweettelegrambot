/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;

import java.util.ArrayList;

/**
 *
 * @author milind
 */
public class InlineKeyboardMarkup {
   InlineKeyboardButton[][] inline_keyboard;

    public InlineKeyboardMarkup() {
        this.inline_keyboard = new InlineKeyboardButton[1][1];
    }
   


    public void addKeyboardButton(InlineKeyboardButton button) {
        inline_keyboard[0][0] = button;// add object to that ArrayList
    }
    
}

