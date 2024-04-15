package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AddColor2 {
    public static void main(String[] args) {
        String html = "<html>...</html>"; // 这里替换为你的HTML字符串

        // 解析HTML
        Document doc = Jsoup.parse(html);

        // 选择需要修改背景色的元素
        Elements elements = doc.select("a[name=cmntref1]");

        // 遍历找到的元素并修改背景色
        for (Element element : elements) {
            // 修改当前元素的背景色
            element.attr("style", appendToStyle(element.attr("style"), "background-color: red;"));

            // 修改当前元素包裹的子元素的背景色
            Elements childElements = element.children();
            for (Element childElement : childElements) {
                childElement.attr("style", appendToStyle(childElement.attr("style"), "background-color: red;"));
            }
        }

        // 输出修改后的HTML
        String modifiedHtml = doc.html();
        System.out.println(modifiedHtml);
    }

    // 将新的样式添加到原有的样式中
    private static String appendToStyle(String originalStyle, String newStyle) {
        if (originalStyle.isEmpty()) {
            return newStyle;
        } else {
            return originalStyle + " " + newStyle;
        }
    }
}
