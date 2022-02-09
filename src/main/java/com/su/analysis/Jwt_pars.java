package com.su.analysis;

public class Jwt_pars {

    private String head;
    private String payload;
    private String secret;
    private String jwt_text;


    public Jwt_pars(String jwt_text) {
        this.jwt_text = jwt_text;
        String[] jwt_texts = jwt_text.split("\\.");

        if (jwt_texts.length!=3){ return; }
        this.head = jwt_texts[0];
        this.payload = jwt_texts[1];
        this.secret = jwt_texts[2];
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getJwt_text() {
        return jwt_text;
    }

    public void setJwt_text(String jwt_text) {
        this.jwt_text = jwt_text;
    }


}
