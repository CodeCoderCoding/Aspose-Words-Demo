package com.supremepole.aspose.words.html;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParserExample2 {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String htmlFilePath = currentDirectory + "\\inputForParser.html";

        try {
            // 读取HTML文件
            BufferedReader reader = new BufferedReader(new FileReader(htmlFilePath));
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
            reader.close();

            // 使用正则表达式修改img标签的alt属性
            String modifiedHtmlContent = modifyImgAlt(htmlContent.toString(), currentDirectory);

            // 将修改后的HTML保存到新文件
            String modifiedHtmlFilePath = currentDirectory + "\\output2.html";
            Path outputHtmlFilePath = Paths.get(modifiedHtmlFilePath);
            Files.write(outputHtmlFilePath, modifiedHtmlContent.getBytes());

            System.out.println("HTML文件修改成功，修改后的文件路径：" + modifiedHtmlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String modifyImgAlt(String htmlContent, String currentDirectory) {
        // 匹配img标签的正则表达式
        String imgTagRegex = "<img\\s+[^>]*>";
        Pattern imgTagPattern = Pattern.compile(imgTagRegex);

        // 匹配src属性的正则表达式
        String srcAttrRegex = "src=\"([^\"]*)\"";
        Pattern srcAttrPattern = Pattern.compile(srcAttrRegex);

        // 替换img标签的alt属性
        Matcher imgTagMatcher = imgTagPattern.matcher(htmlContent);
        StringBuilder modifiedHtmlContent = new StringBuilder();
        int lastIndex = 0;
        while (imgTagMatcher.find()) {
            modifiedHtmlContent.append(htmlContent, lastIndex, imgTagMatcher.start());

            String imgTag = imgTagMatcher.group();
            Matcher srcAttrMatcher = srcAttrPattern.matcher(imgTag);
            if (srcAttrMatcher.find()) {
                String srcAttr = srcAttrMatcher.group(1);
                String fileName = getFileNameFromPath(srcAttr);
                String modifiedImgTag = imgTag.replaceFirst("alt=\"[^\"]*\"", "alt=\"" + fileName + "\"");
                modifiedHtmlContent.append(modifiedImgTag);
            } else {
                modifiedHtmlContent.append(imgTag);
            }

            lastIndex = imgTagMatcher.end();
        }
        modifiedHtmlContent.append(htmlContent.substring(lastIndex));

        return modifiedHtmlContent.toString();
    }

    private static String getFileNameFromPath(String path) {
        // 获取路径中的文件名（不包括扩展名）
        return new File(path).getName().replaceFirst("[.][^.]+$", "");
    }
}
