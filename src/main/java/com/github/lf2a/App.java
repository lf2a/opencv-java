package com.github.lf2a;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Vision.enableDebug("/media/luiz/Dados/repo/opencv-java/src/main/resources/res.jpg");

        String linuxSource = "/media/luiz/Dados/repo/opencv-java/src/main/resources/example.png";
        String linuxTemplate = "/media/luiz/Dados/repo/opencv-java/src/main/resources/example-0.png";

        Position position = Vision.find(linuxSource, linuxTemplate);

        System.out.println(position);
    }
}
