package com.supremepole.aspose.words.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTMLParserExample {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String htmlFilePath = currentDirectory + "\\inputForParser.html";

        try {
            // 读取HTML文件
            File inputHtmlFile = new File(htmlFilePath);
            Document doc = Jsoup.parse(inputHtmlFile, "UTF-8");

            // 获取所有的img标签
            Elements imgTags = doc.getElementsByTag("img");

            // 遍历img标签并修改alt属性
            for (Element imgTag : imgTags) {
                String src = imgTag.attr("src");
                String fileName = getFileNameFromPath(src);
                imgTag.attr("alt", fileName);
            }

            // 将修改后的HTML保存到新文件
            String modifiedHtmlFilePath = currentDirectory + "\\output.html";
            Path outputHtmlFilePath = Paths.get(modifiedHtmlFilePath);
            Files.write(outputHtmlFilePath, doc.html().getBytes());

            System.out.println("HTML文件修改成功，修改后的文件路径：" + modifiedHtmlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileNameFromPath(String path) {
        // 获取路径中的文件名（不包括扩展名）
        return new File(path).getName().replaceFirst("[.][^.]+$", "");
    }
}
