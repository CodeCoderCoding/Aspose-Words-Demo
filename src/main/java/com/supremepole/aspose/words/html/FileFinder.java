package com.supremepole.aspose.words.html;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查找某个文件夹下不含后缀的对应的文件的全文件名
 */
public class FileFinder {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html\\images";

        Path folderToSearch = Paths.get(currentDirectory); // 替换为你的目录路径
        String fileNameToFind = "abc1"; // 替换为你要查找的文件名（不含后缀）

        try {
            List<Path> matchingFiles = Files.walk(folderToSearch)
                    .filter(Files::isRegularFile) // 过滤出普通文件
                    .filter(path -> path.getFileName().toString().startsWith(fileNameToFind)) // 过滤出文件名以指定字符串开头的文件
                    .collect(Collectors.toList()); // 收集结果到列表

            // 打印找到的文件路径
//            matchingFiles.forEach(System.out::println);

            System.out.println(matchingFiles.get(0).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
