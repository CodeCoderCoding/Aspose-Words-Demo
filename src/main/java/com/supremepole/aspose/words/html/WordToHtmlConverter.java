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

            // 获取文档中的所有图片
            NodeCollection<Shape> shapes = doc.getChildNodes(NodeType.SHAPE, true);
            // 遍历所有图片
            for (Shape shape : shapes) {
                if (shape.hasImage()) {
                    if (shape.getOleFormat() != null) {
                        // 获取图片对象
                        ImageData image = shape.getImageData();
                        
                    }
                }
            }

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
