package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C1ReplyCommentAdd {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\comment";
        String htmlFilePath = currentDirectory + "\\b1output.html";

        // 解析HTML
        Document doc = Jsoup.parse(htmlFilePath);

        // 使用正则表达式匹配样式中的值
        Pattern pattern = Pattern.compile("-aw-comment-end:(_cmntref\\d+)");
        Elements spans = doc.select("span[style*=aw-comment-end]");

        for (Element span : spans) {
            String style = span.attr("style");
            Matcher matcher = pattern.matcher(style);

            if (matcher.find()) {
                String value = matcher.group(1);
                String name = span.previousElementSibling().select("a[name]").attr("name");

                if (!name.equals(value)) {
                    Element newSpan = Jsoup.parseBodyFragment("<span><a name=\"" + value + "\"></a></span>");
                    span.before(newSpan);
                }
            }
        }

        // 输出修改后的HTML
        System.out.println(doc.html());
    }
}
