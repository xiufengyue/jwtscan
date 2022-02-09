package com.su.defect.brute_force;

import com.su.analysis.Jwt_pars;

import com.su.util.File_Utils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Key_enum {


    private String dictionarie_path;

    private Integer thead_num;

    private Jwt_pars jwt_pars;

    public Key_enum(String dictionarie_path, Integer thead_num, Jwt_pars jwt_pars) {
        this.dictionarie_path = dictionarie_path;
        this.thead_num = thead_num;
        this.jwt_pars = jwt_pars;
    }

    public void execute_(){

        String jwt_e = jwt_pars.getHead()+"."+jwt_pars.getPayload();
        List<String> keys = File_Utils.lines_read_array(dictionarie_path);


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (String key: keys){
            JWT_blast jwt_blast =new JWT_blast(jwt_e,key,jwt_pars.getSecret());
            executorService.execute(new Jwt_blast_processing1(jwt_blast));
        }
        executorService.shutdown();

        try {
            while (true){
                if(executorService.isTerminated()){
                    break;
                }
                if (Jwt_blast_processing1.success){
                    break;
                }
            }

            if (Jwt_blast_processing1.success){
                System.out.println("Key cracked successfully");
                System.out.println("Key : "+Jwt_blast_processing1.sec);
            }else {
                System.out.println("Key cracking failed");
            }
        }catch (Exception e){

        }
    }
}
