package com.supremepole.aspose.words;

import com.aspose.words.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class HelloWorldAsposeWords {
    public static void main(String[] args) throws Exception {
        // 创建一个新的 Document 对象
        Document doc = new Document();

        // 创建一个 DocumentBuilder 对象，用于向文档中添加内容
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 写入 "Hello World" 文本
        builder.writeln("Hello World");

        Comment comment = new Comment(doc, "Awais Hafeez", "AH", new Date());
        builder.getCurrentParagraph().appendChild(comment);
        comment.getParagraphs().add(new Paragraph(doc));
        comment.getFirstParagraph().getRuns().add(new Run(doc, "Comment text."));

        // 获取当前工作目录的路径
        String currentDirectory = System.getProperty("user.dir");

        // 构造完整的文件路径
        String filePath = Paths.get(currentDirectory, "HelloWorld.docx").toString();

        // 保存文档到磁盘
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            doc.save(out, SaveFormat.DOCX);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出成功消息，包括文件保存的路径
        System.out.println("Document saved successfully at: " + filePath);
    }
}
