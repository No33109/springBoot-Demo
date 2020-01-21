package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {


    /**
     * test witch try-with-resource
     * @param fileName
     */
    public void readFile(String fileName) {
        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader))
        {
            String s = "";

            while ((s=bufferedReader.readLine())!= null){
                System.out.println(s);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
