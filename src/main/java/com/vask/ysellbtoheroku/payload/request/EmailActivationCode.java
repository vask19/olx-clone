package com.vask.ysellbtoheroku.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailActivationCode {
    private String value;




    public Integer toInt(){
        if (!value.isEmpty()){
            return Integer.parseInt(value);
        }
        return -1;
    }
}
