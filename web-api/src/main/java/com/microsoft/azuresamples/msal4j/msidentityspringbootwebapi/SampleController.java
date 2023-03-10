// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azuresamples.msal4j.msidentityspringbootwebapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.shaded.json.JSONObject;

@RestController
@CrossOrigin
public class SampleController {

    @GetMapping("/api/date")
    @ResponseBody
    //@PreAuthorize("hasAuthority('SCOPE_access_as_user')")
    public String date(/**BearerTokenAuthentication bearerTokenAuth*/) {
        /** 
        //uncomment the parameter in the function params above and the line below to get access to the principal.
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) bearerTokenAuth.getPrincipal();
        // You can then access attributes of the principal, e.g., attributes (claims), the raw tokenValue, and authorities.
        // For example:
        principal.getAttribute("scp");
        */
        return new DateResponse().toString();
    }
    @PreAuthorize("hasAuthority('SCOPE_access_as_user')")
    @GetMapping("api/hello")
    public String hello() {
        return "hello azure!";
    }

    
    @GetMapping("api/courses/developer")
	public List<Course> getAllDeveloperCourses()   
    {  
        return List.of(
            new Course("Programming", "NET, Java, Python, Javascipt"),
            new Course("Dev-method", "Agile, Waterfall"),
            new Course("Test-tool", "JUnit5, Mocha, PyTest")
        );
    }  

    @GetMapping("api/courses/developer/{id}")
	public Course getDeveloperCourse()   
    {  
        return new Course("Programming", "NET, Java, Python, Javascipt");
    }  

        
    @GetMapping("api/courses/sre")
	public List<Course> getAllSRECourses()   
    {  
        return List.of(
            new Course("Infrastructure-Code", "Terrafrom, Ansible, Cheft, Puppet"),
            new Course("Cloud-Compute", "AWS, Azure, GCP"),
            new Course("Unix-Admin", "Bash, Perl")
        );
    } 

    @GetMapping("api/courses/sre/{id}")
	public Course getSRECourse()   
    {  
        return new Course("Infrastructure-Code", "Terrafrom, Ansible, Cheft, Puppet");
    }  

    private class DateResponse {
        private String humanReadable;
        private String timeStamp;

        public DateResponse() {
            Date now = new Date();
            this.humanReadable = now.toString();
            this.timeStamp = Long.toString(now.getTime());
        }

        public String toString() {
            return String.format("{\"humanReadable\": \"%s\", \"timeStamp\": \"%s\"}", humanReadable, timeStamp);
        }
    }
    /* @GetMapping("api/token")
	public String getToken()   
    {  
        return new Course("Topic1", "Burger");
    } */
    @GetMapping("api/token")
    private String getUserInfoFromGraph(String accessToken) throws Exception {
        // Microsoft Graph user endpoint
        URL url = new URL("https://graph.microsoft.com/v1.0/me");
    
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    
        // Set the appropriate header fields in the request header.
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Accept", "application/json");
    
        String response = HttpClientHelper.getResponseStringFromConn(conn);
    
        int responseCode = conn.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException(response);
        }
    
        // JSONObject responseObject = HttpClientHelper.processResponse(responseCode, response);
        // return responseObject.toString();
        return ("401");
    }
}
