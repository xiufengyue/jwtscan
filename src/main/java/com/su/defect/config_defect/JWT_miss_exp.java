package com.su.defect.config_defect;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class JWT_miss_exp extends F_config_defect{
    @Override
    public Boolean scan() {

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(getJwt_pars().getPayload());
            JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
            if (jsonObject.containsKey("exp")){ return false; }

            setV_name("JWT中未设置过期时间");
            setRisk_level("中");
            setDescribe("当前JWT中未设置过期时间");
            setDetails(new String(bytes));
            return true;

        }catch (Exception e){
            return false;
        }


    }

    public static void main(String[] args) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        JSONObject jsonObject = JSONObject.parseObject("{\"typ\":\"JWT\",\"alg\":\"HS256\"}");
       if (jsonObject.containsKey("typ")){
           System.out.println("1111111111111111111");
       }

    }
}
