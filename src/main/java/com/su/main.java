package com.su;

import com.su.analysis.Http_req_pars;
import com.su.analysis.Jwt_pars;
import com.su.defect.D_Loader;
import com.su.defect.brute_force.Key_enum;
import com.su.util.File_Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class main {
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }
        String file_path="";
        String jwt="";
        String dictionarie_file="";
        Integer thead_num=30;

        int argIndex = 0;
        while (argIndex < args.length) {
            String arg = args[argIndex];
            if (arg.equals("-r")) {
                file_path = args[argIndex+1];
            }else if (arg.equals("-j")){
                jwt = args[argIndex+1];
            }else if (arg.equals("-d")){
                dictionarie_file = args[argIndex+1];
            }else if (arg.equals("-t")){
                thead_num = Integer.parseInt(args[argIndex+1]);
            }
            argIndex++;
        }

        D_Loader d_loader= new D_Loader();
        d_loader.setDictionarie_file(dictionarie_file);
        d_loader.setThead_num(thead_num);

        if (jwt!=""){
            Jwt_pars jwt_pars=new Jwt_pars(jwt);
            d_loader.jwt_de_info(jwt_pars);
            d_loader.c_dloder(jwt_pars);
            d_loader.b_loader(jwt_pars);

        }else if (file_path!=""){

            String text = File_Utils.lines_read(file_path);
            Http_req_pars http_req_pars =new Http_req_pars(text);
            http_req_pars.jwt_pars();
            Jwt_pars jwt_pars=http_req_pars.getJwt_pars();

            d_loader.jwt_de_info(jwt_pars);
            d_loader.v_loder(http_req_pars);
            d_loader.c_dloder(jwt_pars);
            d_loader.b_loader(jwt_pars);

        }

    }

    private static void printUsage() {
        System.out.println("-r : specifies the path of the complete HTTP request package to be scanned, - r will send a request to verify the security problem\n" +
                "Example:\n" +
                "GET /user/userinfo HTTP/1.1\n" +
                "Host: 127.0.0.1:8081\n" +
                "Cookie: token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjoxMDAwMSwicm4iOiJBUEg2OEpSTXdURWFIN29TZEc2eURwa1c3SXA4bkY5TiJ9.u3xuzKmqKBgcPbTHBk3RitWc25sXfmJCP4ekeZQPKo0\n" +
                "\n" +
                "-j : complete JWT information\n" +
                "\n" +
                "-d : specifies the JWT secret key explosion dictionary. If it is not specified, brute force cracking will not be performed\n" +
                "\n" +
                "-t JWT the number of threads for key blasting is 30 by default"+
                "\n" +
                "Two scanning modes are provided\n" +
                "Example:\n" +
                "java - jar JWTSCAN.jar -r Original_request.txt -d Key_dictionary.txt\n" +
                "java - jar JWTSCAN.jar jjwt-1.0-SNAPSHOT.jar -j eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjoxMDAwMSwicm4iOiJBUEg2OEpSTXdURWFIN29TZEc2eURwa1c3SXA4bkY5TiJ9.u3xuzKmqKBgcPbTHBk3RitWc25sXfmJCP4ekeZQPKo0 -d Key_dictionary.txt");
    }

}
