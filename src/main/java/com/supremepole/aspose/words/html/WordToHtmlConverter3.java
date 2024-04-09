package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordToHtmlConverter3 {
    public static void main(String[] args) throws Exception {

        // 加载 Word 文档
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String inputFilePath = currentDirectory + "\\input.docx";
        Document doc = new Document(inputFilePath);

        // 将 Word 文档保存为 HTML
        String outputFilePath = currentDirectory + "\\output3.html";
        try {
            doc.save(outputFilePath, SaveFormat.HTML);
            System.out.println("HTML 文件已生成：" + outputFilePath);

            // 处理附件
            processAttachments(doc, outputFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processAttachments(Document doc, String outputFilePath) throws Exception {
        // 读取生成的 HTML 文件内容
        String htmlContent = new String(Files.readAllBytes(Path.of(outputFilePath)));

        // 遍历文档中的每个附件
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        NodeCollection<Shape> shapes = doc.getChildNodes(NodeType.SHAPE, true);
        for (Shape shape : shapes) {
            OleFormat oleFormat = shape.getOleFormat();
            if (oleFormat != null && !oleFormat.isLink()) {
                String attachmentFileName = "attachment" + shape.hashCode() + ".docx";
                String attachmentFilePath = currentDirectory + File.separator + attachmentFileName;
                String attachmentOutputFilePath = currentDirectory + File.separator + "attachment" + shape.hashCode() + ".docx";
                oleFormat.save(attachmentOutputFilePath);
                System.out.println("附件保存成功：" + attachmentOutputFilePath);

                // 获取附件图像在生成的 HTML 中的占位符标记
                String placeholder = generateAttachmentPlaceholder(shape);

                // 在生成的 HTML 中替换占位符标记为附件链接
                String attachmentLink = "<a href=\"" + attachmentFilePath + "\">" + attachmentFileName + "</a>";
                htmlContent = htmlContent.replace(placeholder, attachmentLink);
            }
        }

        // 将更新后的 HTML 保存回文件
        Files.write(Path.of(outputFilePath), htmlContent.getBytes());
    }

    private static String generateAttachmentPlaceholder(Shape shape) {
        // 获取附件图像在生成的 HTML 中的占位符标记
        String placeholder = "<!-- AttachmentPlaceholder_" + shape.hashCode() + " -->";
        return placeholder;
    }
}
