package com.tst.service;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.File;
import java.util.Objects;

public class FileService {
    static String[] skipFormat = {"jar","bat"};

    public static Boolean rename(File file) {
        // 创建 File 对象表示原文件
        String fileName = file.getName();

        String fileFormatName = getFormatName(fileName);
        for (int i = 0; i < skipFormat.length; i++) {
            if (Objects.equals(skipFormat[i], fileFormatName)) {
                return true;
            }
        }

        // 获取字符串的第一个字符
        char firstChar = fileName.charAt(0);

        // 判断是否需要修改
        if (Character.isLetter(firstChar)) {
            if (fileName.length() > 1) {
                char secondLetter = fileName.charAt(1);
                if (secondLetter == '-') {
                    return true;
                }
            }
        }
        String startLetter;
        if ((firstChar >= 'A' && firstChar <= 'Z') || (firstChar >= 'a' && firstChar <= 'z')) {
            startLetter = String.valueOf(firstChar).toUpperCase();
        } else if (isChineseCharacter(firstChar)) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(firstChar);
            startLetter = String.valueOf(pinyinArray[0].charAt(0)).toUpperCase();
        } else {
            return true;
        }

        String newFileName = startLetter + "-" + fileName;

        // 获取原文件所在的目录
        String parentDirectory = file.getParent();

        // 创建 File 对象表示新文件名
        File newFile = new File(parentDirectory, newFileName);

        // 使用 renameTo 方法进行重命名
        boolean success = file.renameTo(newFile);

        return success;
    }

    private static boolean isChineseCharacter(char c) {
        // 中文字符的Unicode范围：\u4e00 到 \u9fa5
        return (c >= '\u4e00' && c <= '\u9fa5');
    }

    private static String getFormatName(String fileName) {
        // 获取文件格式（扩展名）
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            String fileFormat = fileName.substring(dotIndex + 1);
            return fileFormat;
        } else {
            return "";
        }
    }

}
