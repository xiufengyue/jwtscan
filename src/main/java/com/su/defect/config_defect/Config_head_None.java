package com.su.defect.config_defect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Config_head_None extends F_config_defect{

    @Override
    public Boolean scan() {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(getJwt_pars().getHead());
            JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
            String alg =jsonObject.getString("alg");
            if (!alg.equalsIgnoreCase("none")){ return false; }

            setDescribe("当前jwt配置中jwt head中的密钥类型为 none");
            setV_name("jwt none密钥");
            setRisk_level("高");
            setDetails(new String(bytes)+"可访问以有资源");
            return true;

        } catch (IOException e) {
            return false;
        }


    }
}
