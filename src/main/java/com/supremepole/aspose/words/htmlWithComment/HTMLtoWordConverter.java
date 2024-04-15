package com.supremepole.aspose.words.htmlWithComment;

import com.aspose.words.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTMLtoWordConverter {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\htmlWithComment";
        String htmlFilePath = currentDirectory + "\\output.html";

        try {
            // 使用HtmlLoadOptions来处理HTML中的图像
            HtmlLoadOptions loadOptions = new HtmlLoadOptions();
            loadOptions.setLoadFormat(LoadFormat.HTML);
            // 加载HTML文件到Aspose.Words Document对象
            Document doc = new Document(htmlFilePath, loadOptions);

            // 保存为Word文档
            String outputFilePath = currentDirectory + "\\outputHtmlDoc.docx";
            doc.save(outputFilePath, SaveFormat.DOCX);

            System.out.println("HTML转换为Word文档成功！");
        } catch (Exception e) {
            System.out.println("转换过程中发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
