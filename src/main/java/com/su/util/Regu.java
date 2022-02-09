package com.su.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regu {

    public static String search1(String reg,String str){
        Matcher matcher=search(reg,str);
        return matcher.group(1);
    }

    public static Matcher search(String reg,String str){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        if( matcher.find() ){
            return matcher;
        }else{
            //err
            System.out.println("未匹配正则");
            return null;
        }
    }


    public static List<String> searchall(String reg,String str){

        List<String> matcherList = new ArrayList<>();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            try {
                matcherList.add(matcher.group(1));//获取当前匹配的值
            }catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }

        }

        return matcherList;
    }
    public static String btch_replacement(String[] tagets,String rep,String str){

        for(String t:tagets){
            str=str.replace(t,rep);

        }
        return str;
    }

    public static void main(String[] args) {

        Matcher matcher=Regu.search("\\$\\{(.*?)\\}","${spring.version}");

        System.out.println( matcher.group(1));
    }

}
