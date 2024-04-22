package com.supremepole.aspose.words.comment;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;

import java.nio.charset.StandardCharsets;

/**
 * 将WORD转换为HTML，不做任何额外的处理
 */
public class B1WordToHtmlWithComments {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\comment";
        String inputFilePath = currentDirectory + "\\b1input.docx";
        String outputFilePath = currentDirectory + "\\b1output.html";

        try {
            // 加载Word文档
            Document doc = new Document(inputFilePath);

            // saveOptions
            HtmlSaveOptions saveOptions=new HtmlSaveOptions();
            saveOptions.setSaveFormat(SaveFormat.HTML);
            saveOptions.setEncoding(StandardCharsets.UTF_8);

            // 以HTML文档形式保存
            doc.save(outputFilePath, saveOptions);

            System.out.println("转换成功。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
