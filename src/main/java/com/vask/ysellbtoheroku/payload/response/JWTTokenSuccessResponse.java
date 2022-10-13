package com.vask.ysellbtoheroku.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String token;

}
