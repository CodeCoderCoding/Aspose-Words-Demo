package com.supremepole.aspose.words.comment;

import com.aspose.words.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 读取HTML（word不做额外的处理，将word转换为的html）
 * 不做任何处理，转换为word输出，查看是否能还原评论
 */
public class B2HtmlToWordWithComments {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\comment";
        String htmlFilePath = currentDirectory + "\\b1output.html";

        try {
            // 使用HtmlLoadOptions来处理HTML中的图像
            HtmlLoadOptions loadOptions = new HtmlLoadOptions();
            loadOptions.setLoadFormat(LoadFormat.HTML);
            Document doc = new Document(htmlFilePath, loadOptions);
            // 保存为Word文档
            String outputFilePath = currentDirectory + "\\b2output.docx";
            doc.save(outputFilePath, SaveFormat.DOCX);

            System.out.println("HTML转换为Word文档成功！");
        } catch (Exception e) {
            System.out.println("转换过程中发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
