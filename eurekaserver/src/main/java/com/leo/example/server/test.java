package com.leo.example.server;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        int i=0;
        while (true){
            ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;

            try {
                // 序列化
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(new ArrayList<String>());
                byte[] bytes = baos.toByteArray();
                System.out.println(i++);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                String info= "序列化失败";
            }

        }

    }
}
