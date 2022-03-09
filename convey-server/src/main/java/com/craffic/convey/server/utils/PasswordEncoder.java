package com.craffic.convey.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        System.out.println(encode("Craffic"));
    }

    public static String encode(String plain){
        return new BCryptPasswordEncoder().encode(plain);
    }
}
