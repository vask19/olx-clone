package com.vask.ysellbtoheroku.exception;

import lombok.Data;

@Data
public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(){
        super("Chat not found");
    }

    public ChatNotFoundException(String message) {
       super(message);
    }
}
