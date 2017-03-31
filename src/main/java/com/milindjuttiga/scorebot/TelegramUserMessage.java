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
public class TelegramUserMessage {
    private Message message;

    public Message getMessage() {
        return message;
    }
    public static class Message {

        public Chat getChat() {
            return chat;
        }
        Chat chat;
        
        public static class Chat {
            int id;

            public int getId() {
                return id;
            }
            
        }
    }
}