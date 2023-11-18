package com.tst;

import com.tst.service.FileService;
import com.tst.service.WaitForInput;

import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDirectory);
        File directory = new File(currentDirectory);

        // 检查目录是否存在
        if (directory.exists() && directory.isDirectory()) {
            // 调用遍历方法
            traverseDirectory(directory);
        } else {
            System.out.println("指定的路径不是一个有效的目录。");
        }

        WaitForInput.waitForInput();
    }

    // 递归遍历目录的方法
    private static void traverseDirectory(File directory) {
        // 获取目录中的文件和子目录
        File[] files = directory.listFiles();

        if (files != null) {
            // 遍历文件和子目录
            for (File file : files) {
                // 如果是子目录，递归调用遍历方法
                if (file.isDirectory()) {
                    traverseDirectory(file);
                }

                Boolean renameResult = FileService.rename(file);

                if (renameResult) {
//                    System.out.println("Success at : " + file.getAbsolutePath());
                } else {
                    System.out.println("Fail at : " + file.getAbsolutePath());
                }

            }
        }
    }
}
