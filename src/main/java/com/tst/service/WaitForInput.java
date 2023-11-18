package com.tst.service;

import java.util.Scanner;

public class WaitForInput {
    public static void waitForInput(){
        // 创建 Scanner 对象来获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入
        System.out.println("键入以继续");

        // 读取用户输入
        String userInput = scanner.nextLine();

        // 关闭 Scanner 对象
        scanner.close();
    }
}
