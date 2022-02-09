package com.su.analysis;


import com.su.util.Regu;

import java.util.regex.Matcher;

public class Http_req_pars {

    private String http_text;

    private Boolean is_ssl;

    public void setHttp_text(String http_text) {
        this.http_text = http_text;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private Integer port;

    private String host;

    public String getHttp_text() {
        return http_text;
    }

    public Jwt_pars getJwt_pars() {
        return jwt_pars;
    }

    private Jwt_pars jwt_pars;

    public Http_req_pars(String http_text) {
        this.http_text=http_text;
    }

    public void jwt_pars() {
        this.http_text=http_text;
        String jwt_re1="(eyJ[A-Za-z0-9_-]*\\.[A-Za-z0-9._-]*)";
        Matcher head_re=Regu.search(jwt_re1,http_text);
        if (head_re!=null){
            jwt_pars = new Jwt_pars(head_re.group(1));
        }else {
            String jwt_re2="(eyJ[A-Za-z0-9_\\/+-]*\\.[A-Za-z0-9._\\/+-]*)";
            Matcher head_re2=Regu.search(jwt_re1,http_text);
            if (head_re2!=null){
                jwt_pars = new Jwt_pars(head_re2.group(1));
            }else {
                System.out.println("未匹配到jwt");
            }
        }
    }

    public void host_pars() {
        String re = "Host:\\s(.*)";
        Matcher host_re=Regu.search(re,http_text);
        String[] h_p  =host_re.group(1).replace(" ","").split(":");
        if (h_p.length != 2){
            setHost(h_p[0]);
            setPort(0);
        }else {
            setHost(h_p[0]);
            setPort(Integer.parseInt(h_p[1]));
        }
    }

    public Boolean getIs_ssl() {
        return is_ssl;
    }

    public void setIs_ssl(Boolean is_ssl) {
        this.is_ssl = is_ssl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


}
