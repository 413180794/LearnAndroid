package com.example.learnandroid.slideconflict;

public class HelloWorld {
    private static native String hello(String input);

    static {
        System.loadLibrary("mylib");
    }
    public void he() {
        hello("dsfsdf");
    }

    public static void main(String[] args) {
        String output = HelloWorld.hello("TigerInYourDream");
        System.out.println(output);
    }
}
