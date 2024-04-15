package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Remove2 {
    public static void main(String[] args) {
        String html = "<html><body>" +
                "<span><a href=\"_cmnt1\">Link 1</a></span>" +
                "<span><a href=\"_cmnt2\">Link 2</a></span>" +
                "<span><a href=\"not_a_comment\">Not a comment</a></span>" +
                "</body></html>";

        Document doc = Jsoup.parse(html);

        // 使用正则表达式匹配模式：_cmnt\d+
        Pattern pattern = Pattern.compile("_cmnt\\d+");

        // 查找所有的<span><a href=""></a></span>元素
        Elements elements = doc.select("span > a[href]");

        for (Element element : elements) {
            String href = element.attr("href");

            // 使用正则表达式进行匹配
            Matcher matcher = pattern.matcher(href);
            if (matcher.matches()) {
                // 移除匹配模式的<span><a href=""></a></span>元素
                element.parent().remove();
            }
        }

        // 打印更新后的HTML
        System.out.println(doc.html());
    }
}
