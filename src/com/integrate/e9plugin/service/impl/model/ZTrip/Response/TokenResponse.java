package com.integrate.e9plugin.service.impl.model.ZTrip.Response;

import lombok.Data;

@Data
public class TokenResponse {
    public String access_token;

    public String token_type;

    public Integer expires_in;
}