package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class WordToHtmlConverter {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String inputFilePath = currentDirectory + "\\fullDocWithImg.docx";
        String outputFilePath = currentDirectory + "\\output.html";
        String imageDirectory = currentDirectory+ "\\images";

        try {
            // 加载Word文档
            Document doc = new Document(inputFilePath);

            // saveOptions
            HtmlSaveOptions saveOptions=new HtmlSaveOptions();
            saveOptions.setSaveFormat(SaveFormat.HTML);
            saveOptions.setEncoding(StandardCharsets.UTF_8);
            saveOptions.setExportImagesAsBase64(false);
            saveOptions.setExportOriginalUrlForLinkedImages(true);
            saveOptions.setImageSavingCallback(new ImageSavingCallback(Paths.get(imageDirectory).toAbsolutePath().toString()));
            saveOptions.setImagesFolder(Paths.get(imageDirectory).toAbsolutePath().toString());

            // 以HTML文档形式保存
            doc.save(outputFilePath, saveOptions);

            System.out.println("转换成功。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
