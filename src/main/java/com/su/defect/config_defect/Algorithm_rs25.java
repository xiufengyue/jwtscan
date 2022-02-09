package com.su.defect.config_defect;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

public class Algorithm_rs25 extends F_config_defect{
    @Override
    public Boolean scan() {

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(getJwt_pars().getHead());
            JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
            String alg =jsonObject.getString("alg");
            if (!alg.equalsIgnoreCase("RS256")){return false; }
            setDescribe("如果算法从rs256更改为hs256，后端代码将使用公钥作为密钥，然后使用hs256算法验证签名。由于攻击者有时可以获得公钥，因此攻击者可以将标头中的算法修改为hs256，然后使用RSA公钥对数据进行签名。");
            setV_name("jwt配置中jwt head中的密钥类型为 RS256,");
            setRisk_level("低");
            setDetails(new String(bytes)+"当前密钥类型为RS256");


        }catch (Exception e){
            return false;
        }

        return false;
    }
}
