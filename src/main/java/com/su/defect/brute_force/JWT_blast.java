package com.su.defect.brute_force;

import com.su.analysis.Jwt_pars;
import com.su.util.En_utility;

public class JWT_blast{

    private String text;

    public String getKey() {
        return key;
    }

    private String key;
    private String secret;

    public JWT_blast(String text, String key, String secret) {
        this.text = text;
        this.key = key;
        this.secret = secret;
    }

    public Boolean Encryption_comparison(){
        String t_secret=En_utility.en_hs256(this.text,this.key);
        if (!t_secret.equals(this.secret)){
            return false;
        }
            return true;
    }




}
