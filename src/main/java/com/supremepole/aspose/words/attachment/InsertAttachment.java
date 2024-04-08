package com.supremepole.aspose.words.attachment;

import com.aspose.words.*;
import java.io.*;

public class InsertAttachment {

    public static void main(String[] args) {
        try {
            // 创建一个新的Word文档或者加载一个现有的文档
            Document doc = new Document();

            // 创建一个段落，以便将OLE对象插入其中
            DocumentBuilder builder = new DocumentBuilder(doc);
            builder.writeln("Attachment:");

            // 获取当前工作目录的路径
            String currentDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\attachment";

            // 指定要插入的附件文件的路径
            String attachmentPath = currentDirectory + File.separator + "attachment.docx";

            // 插入OLE对象
            // isLinked设置为false用于插入附件到word文档中，而不是链接的形式
            // asIcon设置为true用于在word文档中显示附件的图标
            builder.insertOleObject(attachmentPath, false, true, null);

            // 保存文档
            doc.save(currentDirectory + File.separator + "fullDoc.docx");

            System.out.println("附件添加成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}