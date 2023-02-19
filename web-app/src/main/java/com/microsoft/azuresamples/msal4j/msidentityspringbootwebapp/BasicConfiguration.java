package com.microsoft.azuresamples.msal4j.msidentityspringbootwebapp;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("aad")
class BasicConfiguration {
    String clientId;
    @Getter(AccessLevel.NONE) String authority="";
    String redirectUriSignin;
    String redirectUriGraphUsers;
    String secretKey;

    String getAuthority(){
        if (!authority.endsWith("/")) {
            authority += "/";
        }
        return authority;
    }
}
