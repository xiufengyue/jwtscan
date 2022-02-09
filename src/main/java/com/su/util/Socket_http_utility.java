package com.su.util;

import com.su.analysis.Http_req_pars;
import com.su.analysis.Http_res_pars;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;

public class Socket_http_utility {

    public static Boolean if_is_https(Http_req_pars http_req_pars) throws IOException {
        String host = http_req_pars.getHost();
        Integer port = http_req_pars.getPort();
        OutputStreamWriter streamWriter = null;
        BufferedWriter bufferedWriter = null;
        Socket socket = null;
        try {
            socket = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(host, port);
            streamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(streamWriter);
            bufferedWriter.write("POST / HTTP/1.1\r\n");
            bufferedWriter.flush();
            return true;
        }catch (SSLHandshakeException e){
            bufferedWriter.close();
            socket.close();
            return false;
        }catch (ConnectException e){
            return false;
        }catch (SSLException e){
            bufferedWriter.close();
            socket.close();
            return false;
        }
    }

    public static Http_res_pars send_s_http2(Http_req_pars http_req_pars) throws IOException {
        Socket socket;
        String host = http_req_pars.getHost();
        Integer port = http_req_pars.getPort();
        String http_= http_req_pars.getHttp_text();
        Boolean is_https = http_req_pars.getIs_ssl();
        if (is_https){
            socket = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(host, port);
        }else {
            socket = new Socket(host, port);
        }
        OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream(), "utf-8");

        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);

        String[] http_s=http_.split("\r\n");
        for(String h_line:http_s){
            if (h_line.contains("Accept-Encoding: ")){ continue;}
            bufferedWriter.write(h_line+"\r\n");
        }
        bufferedWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
        String res_text="";
        String line = null;
        while((line = bufferedReader.readLine())!= null)
        {
            res_text += line+"\r\n";
        }
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        Http_res_pars http_res_pars =new Http_res_pars();
        http_res_pars.res_pars(res_text);

        return http_res_pars;
    }

    public static void main(String[] args) {

    }

}
