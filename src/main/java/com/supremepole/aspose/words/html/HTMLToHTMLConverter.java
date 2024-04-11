package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class HTMLToHTMLConverter {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String htmlFilePath = currentDirectory + "\\input.html";
        String imageDirectory = currentDirectory+ "\\images";
        try {
            // 使用HtmlLoadOptions来处理HTML中的图像
            HtmlLoadOptions loadOptions = new HtmlLoadOptions();
            loadOptions.setLoadFormat(com.aspose.words.LoadFormat.HTML);
            Document doc = new Document(htmlFilePath, loadOptions);
            // saveOptions
            HtmlSaveOptions saveOptions=new HtmlSaveOptions();
            saveOptions.setSaveFormat(SaveFormat.HTML);
            saveOptions.setEncoding(StandardCharsets.UTF_8);
            saveOptions.setExportImagesAsBase64(false);
            saveOptions.setExportOriginalUrlForLinkedImages(true);
            saveOptions.setImageSavingCallback(new ChangeImageName(Paths.get(imageDirectory).toAbsolutePath().toString()));
            String outputFilePath = currentDirectory + "\\outputHtmlDoc.html";
            doc.save(outputFilePath, saveOptions);
            System.out.println("HTML转换为Word文档成功！");
        } catch (Exception e) {
            System.out.println("转换过程中发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
