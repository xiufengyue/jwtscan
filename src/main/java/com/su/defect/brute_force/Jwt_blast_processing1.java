package com.su.defect.brute_force;

import java.util.concurrent.Semaphore;

public class Jwt_blast_processing1 implements Runnable{

    //当前并发线程数
    public static Semaphore s;


    public Jwt_blast_processing1(JWT_blast jwt_blast) {
        this.jwt_blast = jwt_blast;
    }

    private JWT_blast jwt_blast;

    //判断是否成功
    private Boolean j_result=false;

    public static String sec="";

    public static Boolean success=false;

    @Override
    public void run() {

        try {
            s.acquire();
            Boolean res = jwt_blast.Encryption_comparison();
            if (res) {
                sec = jwt_blast.getKey();
                success = true;
            }
            s.release();
        }catch (Exception e){
            s.release();
        }

    }

    public Boolean getJ_result() {
        return j_result;
    }

    public void setJ_result(Boolean j_result) {
        this.j_result = j_result;
    }
}
