package com.supremepole.aspose.words.attachment;

import com.aspose.words.*;
import java.io.*;
import java.nio.file.Paths;

public class SaveAttachment {

    public static void main(String[] args) {
        try {
            // 获取当前工作目录的路径
            String currentDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\attachment";
            // 构造完整的文件路径
            String filePath = Paths.get(currentDirectory, "fullDocSave.docx").toString();
            // 加载要解析的 Word 文档
            Document doc = new Document(filePath);
            NodeCollection<Shape> shapes=doc.getChildNodes(NodeType.SHAPE, true);
            // 另存附件
            for (Shape shape : shapes) {
                OleFormat oleFormat = shape.getOleFormat();
                if(oleFormat!=null){
                    if (oleFormat.isLink()) {
                        System.out.println(oleFormat.getSourceFullName());
                    }else {
                        String attachmentOutputFilePath = currentDirectory + File.separator + "attachment" + shape.hashCode() + ".docx";
                        oleFormat.save(attachmentOutputFilePath);
                        System.out.println("附件保存成功： " + attachmentOutputFilePath);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
