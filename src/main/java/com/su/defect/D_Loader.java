package com.su.defect;

import com.su.analysis.Http_req_pars;
import com.su.analysis.Jwt_pars;
import com.su.defect.brute_force.Jwt_blast_processing1;
import com.su.defect.brute_force.Key_enum;
import com.su.defect.config_defect.Algorithm_rs25;
import com.su.defect.config_defect.Config_head_None;
import com.su.defect.config_defect.F_config_defect;
import com.su.defect.config_defect.JWT_miss_exp;
import com.su.defect.vulnerability.F_vulnerability;
import com.su.defect.vulnerability.None_token;
import com.su.defect.vulnerability.Null_encryption;
import com.su.util.File_Utils;
import com.su.util.Socket_http_utility;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Semaphore;

public class D_Loader {

    List<F_vulnerability> vulnerabilities = new ArrayList<>();
    List<F_config_defect> f_config_defects= new ArrayList<>();

    private Integer thead_num=30;

    public Integer getThead_num() {
        return thead_num;
    }

    public void setThead_num(Integer thead_num) {
        this.thead_num = thead_num;
    }

    public String getDictionarie_file() {
        return dictionarie_file;
    }

    public void setDictionarie_file(String dictionarie_file) {
        this.dictionarie_file = dictionarie_file;
    }

    private  String dictionarie_file="";

    public D_Loader() {
        F_vulnerability none_token = new None_token();
        F_vulnerability  null_encryption =new Null_encryption();
        vulnerabilities.add(none_token);
        vulnerabilities.add(null_encryption);

        F_config_defect config_None =new Config_head_None();
        F_config_defect jwt_miss_exp =new JWT_miss_exp();
        F_config_defect algorithm_rs25 =new Algorithm_rs25();
        f_config_defects.add(config_None);
        f_config_defects.add(jwt_miss_exp);
        f_config_defects.add(algorithm_rs25);

    }

    public void jwt_brute_jorce(String dictionarie_path, Integer thead_num, Jwt_pars jwt_pars){

        Key_enum key_enum = new Key_enum(dictionarie_path,thead_num,jwt_pars);
        key_enum.execute_();
    }


    public void c_dloder(Jwt_pars jwt_pars){
        System.out.println("-------------------扫描jwt配置中可能存在的问题------------------");
        for (F_config_defect f_config_defect:f_config_defects){
            f_config_defect.setJwt_pars(jwt_pars);
            Boolean res=f_config_defect.scan();
            if (res){
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("存在配置危险 : "+f_config_defect.getV_name());
                System.out.println("风险等级 : "+f_config_defect.getRisk_level());
                System.out.println("存在配置危险描述 : "+ f_config_defect.getDescribe());
                System.out.println("细节描述 ： "+ f_config_defect.getDetails());

                System.out.println();
            }
        }
        System.out.println("-------------------------------------------------------------");

    }

    public void v_loder( Http_req_pars http_req_pars) throws IOException {

        http_req_pars.host_pars();
        System.out.println("-------------------发送请求探测jwt漏洞---------------------");
        //针对默认端口情况下的判断
        if (http_req_pars.getPort()==0){
            http_req_pars.setPort(443);
            Boolean t_res = Socket_http_utility.if_is_https(http_req_pars);
            if (t_res){

                http_req_pars.setIs_ssl(true);
            }else {

                http_req_pars.setIs_ssl(false);
                http_req_pars.setPort(80);
            }
        }else {
            Boolean t_res =false;
            t_res = Socket_http_utility.if_is_https(http_req_pars);
            if (t_res){
                http_req_pars.setIs_ssl(true);
            }else {
                http_req_pars.setIs_ssl(false);
            }
        }

        for (F_vulnerability vulnerability:vulnerabilities){
            vulnerability.setHttp_req_pars(http_req_pars);
            Boolean res=vulnerability.scan();
            if (res){

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("存在漏洞 : "+vulnerability.getV_name());
                System.out.println("风险等级 : "+vulnerability.getRisk_level());
                System.out.println("漏洞描述 : "+ vulnerability.getDescribe());
                System.out.println("细节描述 ： "+ vulnerability.getDetails());
                System.out.println();
            }
        }
        Jwt_pars jwt_pars= http_req_pars.getJwt_pars();
        System.out.println("-------------------------------------------------------------");
    }
    public void b_loader(Jwt_pars jwt_pars){
        if (dictionarie_file.equals("")){return;}
        System.out.println("--------------------------当前爆破jwt密钥------------------------");
        Jwt_blast_processing1.s = new Semaphore(thead_num);
        if (dictionarie_file!=""){
            jwt_brute_jorce(dictionarie_file,thead_num,jwt_pars);
        }
    }

    public void jwt_de_info(Jwt_pars jwt_pars){
        System.out.println("JWT 信息:::");
        System.out.println(jwt_pars.getJwt_text());
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] headbytes = decoder.decodeBuffer(jwt_pars.getHead());
            byte[] payloadytes = decoder.decodeBuffer(jwt_pars.getPayload());
            System.out.println("JWT 信息解密:::");
            System.out.println("jwt HEADER  : "+new String(headbytes));
            System.out.println("jwt PAYLOAD : "+new String(payloadytes));
        } catch (IOException e) {
            System.out.println("JWT 解密失败....");
            System.exit(0);
            e.printStackTrace();
        }



    }
}
