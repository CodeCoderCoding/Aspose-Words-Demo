package com.supremepole.aspose.words.attachment;

import com.aspose.words.*;

import java.nio.file.Paths;

public class WordParsingExample {
    public static void main(String[] args) throws Exception {
        // 获取当前工作目录的路径
        String currentDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\attachment";
        // 构造完整的文件路径
        String filePath = Paths.get(currentDirectory, "fullDocWithImg.docx").toString();
        // 加载要解析的 Word 文档
        Document doc = new Document(filePath);

        // 遍历文档中的所有图片
        NodeCollection shapes = doc.getChildNodes(NodeType.SHAPE, true);
        for (Shape shape : (Iterable<Shape>) shapes) {
            if (shape.hasImage()) {
                ImageData imageData=shape.getImageData();
                shape.getName();
                shape.getAlternativeText();
                System.out.println(imageData.getTitle());
                if (shape.getOleFormat() != null) {
                    // 处理附件占位图标
                    System.out.println("附件占位图标路径: " + shape.getOleFormat().getIconCaption());
                    imageData.getSourceFullName();

                }else {
                    // 处理普通图片
                    System.out.println("普通图片路径: " + shape.getImageData().getSourceFullName());
                    imageData.getTitle();
                }
            }
        }
    }
}