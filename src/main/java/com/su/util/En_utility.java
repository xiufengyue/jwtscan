package com.su.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSigner;
import io.jsonwebtoken.impl.crypto.JwtSigner;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class En_utility {
    public static String en_hs256(String text,String key){
        Key KEY = new SecretKeySpec(key.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
        JwtSigner signer = new DefaultJwtSigner(SignatureAlgorithm.HS256, KEY);
        String base64UrlSignature = signer.sign(text);
        return base64UrlSignature;
    }
}
