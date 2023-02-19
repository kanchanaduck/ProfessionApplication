package com.microsoft.azuresamples.msal4j.msidentityspringbootwebapp;
import lombok.Getter;

import java.util.Date;

@Getter

public class StateData {
    private String nonce;
    private Date expirationDate;

    StateData(String nonce, Date expirationDate) {
        this.nonce = nonce;
        this.expirationDate = expirationDate;
    }
}
