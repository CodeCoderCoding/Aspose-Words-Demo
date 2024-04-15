package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Remove3 {
    public static void main(String[] args) {
        String html = "<html><body>" +
                "<div>Before</div>" +
                "<span><a href=\"_cmnt1\">Link 1</a></span>" +
                "<div>Between</div>" +
                "<span><a href=\"_cmnt2\">Link 2</a></span>" +
                "<div>After</div>" +
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
                // 获取匹配元素前的一个元素
                Element previousElement = element.parent().previousElementSibling();

                // 在匹配元素前的一个元素前后包裹<span id="commentedStart"></span>和<span id="commentedEnd"></span>
                Element commentedStart = new Element("span").attr("id", "commentedStart");
                Element commentedEnd = new Element("span").attr("id", "commentedEnd");
                previousElement.after(commentedEnd);
                previousElement.before(commentedStart);
            }
        }

        // 打印更新后的HTML
        System.out.println(doc.html());
    }
}
