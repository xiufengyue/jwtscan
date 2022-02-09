package com.su.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File_Utils {



    public static String lines_read(String fileName){

        File file = new File(fileName);
        String filevalue="";
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                filevalue+=tempString+"\r\n";
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return filevalue;
    }

    public static List<String> lines_read_array(String fileName){

        List<String> texts = new ArrayList<>();
        File file = new File(fileName);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                texts.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return texts;
    }

    public static InputStream get_FileInputStream(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File(path));
        return is;
    }





}
