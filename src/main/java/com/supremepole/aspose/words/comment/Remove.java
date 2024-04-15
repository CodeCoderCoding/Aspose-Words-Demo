package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Remove {
    public static void main(String[] args) {
        String html = "<html><body>" +
                "<div id=\"_cmnt1\">Comment 1</div>" +
                "<div id=\"_cmnt2\">Comment 2</div>" +
                "<div id=\"not_a_comment\">Not a comment</div>" +
                "</body></html>";
        System.out.println(html);
        Document doc = Jsoup.parse(html);
        // 使用正则表达式匹配模式：_cmnt\d+
        Pattern pattern = Pattern.compile("_cmnt\\d+");
        // 查找所有具有匹配模式的div元素
        Elements elements = doc.select("[id]");
        for (Element element : elements) {
            String id = element.attr("id");
            // 使用正则表达式进行匹配
            Matcher matcher = pattern.matcher(id);
            if (matcher.matches()) {
                // 移除匹配模式的div元素
                element.remove();
            }
        }
        // 打印更新后的HTML
        System.out.println(doc.html());
    }
}