package com.supremepole.aspose.words.comment;

import com.aspose.words.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class AddComment {
    public static void main(String[] args) throws Exception {
        // 创建一个新的 Document 对象
        Document doc = new Document();

        // 创建一个 DocumentBuilder 对象，用于向文档中添加内容
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 写入 "Hello World" 文本
        builder.writeln("Hello World, Good Morning.");

        // 获取当前工作目录的路径
        String currentDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\comment";

        // 构造完整的文件路径
        String filePath = Paths.get(currentDirectory, "HelloWorldWithComment.docx").toString();

        // 使用 Aspose.Words 进行评论添加
        String wordToFind = "Good";

        FindReplaceOptions options = new FindReplaceOptions();
        options.setUseSubstitutions(true);
        doc.getRange().replace(wordToFind, "$0", options);

        Iterable<Run> runs = doc.getChildNodes(NodeType.RUN, true);

        for (Run run : runs) {
            if (run.getText().equals(wordToFind)) {
                Comment comment = new Comment(doc, "Author", "Good", new Date());
                comment.getParagraphs().add(new Paragraph(doc));
                comment.getFirstParagraph().appendChild(new Run(doc, "Comment text."));

                run.getParentNode().insertBefore(new CommentRangeStart(doc, comment.getId()), run);
                run.getParentNode().insertAfter(new CommentRangeEnd(doc, comment.getId()), run);
                run.getParentNode().insertAfter(comment, run);
            }
        }

        // 保存包含评论的文档到磁盘
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            doc.save(out, SaveFormat.DOCX);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Document with comment saved successfully at: " + filePath);
    }
}