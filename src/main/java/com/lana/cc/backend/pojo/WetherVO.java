package com.lana.cc.backend.pojo;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/20 21:43
 */
public class WetherVO {
    private String status;
    private String city;
    private String aqi;
    private String pm25;
    private String temp;
    private String weather;
    private String wind;
    private String weatherimg;
    private Tomorrow tomorrow;

    public static class Tomorrow{
        private String temp;
        private String weather;
        private String wind;
        private String weatherimg;
    }

}

