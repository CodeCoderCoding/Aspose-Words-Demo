package com.supremepole.aspose.words.comment;

import com.aspose.words.*;

import java.nio.file.Paths;

public class CommentParser {
    public static void main(String[] args) throws Exception {
        // 获取当前工作目录的路径
        String currentDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\comment";

        // 构造完整的文件路径
        String filePath = Paths.get(currentDirectory, "HelloWorld.docx").toString();

        // 加载要解析的 Word 文档
        Document doc = new Document(filePath);

        // 遍历文档中的所有注释
        NodeCollection comments = doc.getChildNodes(NodeType.COMMENT, true);
        for (Comment comment : (Iterable<Comment>) comments) {
            System.out.println("Author: " + comment.getAuthor());
            System.out.println("Initial: " + comment.getInitial());
            System.out.println("Text: " + comment.getText());
            System.out.println("Date: " + comment.getDateTime());

            // 获取注释范围内的文本内容
            StringBuilder sb = new StringBuilder();
            Node currentNode = comment.getPreviousSibling();
            while(currentNode.getNodeType()!=NodeType.COMMENT_RANGE_START){
                if (currentNode.getNodeType() == NodeType.RUN) {
                    Run run = (Run) currentNode;
                    sb.insert(0, run.getText());
                } else if (currentNode.getNodeType() == NodeType.PARAGRAPH) {
                    Paragraph para = (Paragraph) currentNode;
                    for (int i = para.getRuns().getCount() - 1; i >= 0; i--) {
                        Run run = para.getRuns().get(i);
                        sb.insert(0, run.getText());
                    }
                }
                currentNode=currentNode.getPreviousSibling();
            }
            System.out.println("Range Text: " + sb.toString());

        }
    }
}
