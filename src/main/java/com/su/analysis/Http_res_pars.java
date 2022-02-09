package com.su.analysis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Http_res_pars {

    private String status_code;
    private String body;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean res_pars(String text) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes()), Charset.forName("utf8")));
        String line;
        Boolean body_s =false;
        String body="";
        while((line =  br.readLine())!= null){
            if (!body_s){
                if (line.equals("")){
                    body_s = true;
                }
            }else {
                body += line;
            }
        }
        setBody(body);

        return null;
    }


}
