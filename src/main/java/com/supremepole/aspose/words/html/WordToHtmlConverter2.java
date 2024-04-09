package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordToHtmlConverter2 {
    public static void main(String[] args) throws Exception {

        // 加载 Word 文档
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String inputFilePath = currentDirectory + "\\input.docx";
        Document doc = new Document(inputFilePath);

        // 将 Word 文档保存为 HTML
        String outputFilePath = currentDirectory + "\\output2.html";
        try {
            doc.save(outputFilePath, SaveFormat.HTML);
            System.out.println("HTML 文件已生成：" + outputFilePath);

            // 处理附件
            processAttachments(doc, currentDirectory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processAttachments(Document doc, String outputDirectory) throws Exception {

        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        // 附件保存目录
        String attachmentsDirectory = outputDirectory + "\\attachments";
        File attachmentsDir = new File(attachmentsDirectory);
        attachmentsDir.mkdirs();

        // 遍历文档中的每个附件
        NodeCollection<Shape> shapes=doc.getChildNodes(NodeType.SHAPE, true);
        // 另存附件
        for (Shape shape : shapes) {
            OleFormat oleFormat = shape.getOleFormat();
            if(oleFormat!=null){
                if (oleFormat.isLink()) {
                    System.out.println(oleFormat.getSourceFullName());
                }else {

                    String attachmentFileName = "attachment" + shape.hashCode() + ".docx";
                    String attachmentFilePath = currentDirectory + File.separator + attachmentFileName;
                    String attachmentOutputFilePath = currentDirectory + File.separator + "attachment" + shape.hashCode() + ".docx";
                    oleFormat.save(attachmentOutputFilePath);
                    System.out.println("附件保存成功： " + attachmentOutputFilePath);

                    // 在 HTML 中创建附件链接
                    createAttachmentLink(attachmentFileName, attachmentFilePath);
                }
            }
        }
    }

    private static void createAttachmentLink(String attachmentFileName, String attachmentFilePath) {
        // 在生成的 HTML 中创建附件链接
        String attachmentLink = "<a href=\"" + attachmentFilePath + "\">" + attachmentFileName + "</a>";

        // 将 attachmentLink 插入到 HTML 的底部
        try {
            String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
            // 读取生成的 HTML 文件
            String outputFilePath = currentDirectory + "\\output2.html";
            String htmlContent = new String(Files.readAllBytes(Path.of(outputFilePath)));

            // 在 HTML 的底部添加附件链接
            htmlContent += "<br>" + attachmentLink;

            // 将更新后的 HTML 保存回文件
            Files.write(Path.of(outputFilePath), htmlContent.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}