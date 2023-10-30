package com.toni.demo;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Date;

public class MD5 {

    public static String getMD5(String input) {
        try {
            Date date = new Date();
            LocalDate today = LocalDate.now();
            System.out.println(today);

            input = input + today;

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            return String.format("%032x", new java.math.BigInteger(1, hash));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}