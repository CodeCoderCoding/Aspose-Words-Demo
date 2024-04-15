package com.supremepole.aspose.words.comment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Pattern;

public class AddColor {
    public static void main(String[] args) {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <meta http-equiv=\"Content-Style-Type\" content=\"text/css\"/>\n" +
                "    <meta name=\"generator\" content=\"Aspose.Words for Java 24.1.0\"/>\n" +
                "    <title></title></head>\n" +
                "<body style=\"text-align:justify; widows:0; orphans:0; font-family:等线; font-size:10.5pt\">\n" +
                "<div>\n" +
                "    <div style=\"-aw-headerfooter-type:header-primary; clear:both\">\n" +
                "        <p style=\"margin-top:0pt; margin-bottom:0pt\">\n" +
                "            <span\n" +
                "                style=\"height:0pt; text-align:left; display:block; position:absolute; z-index:-65537\">\n" +
                "                <img\n" +
                "                src=\"output.001.png\" width=\"554\" height=\"301\" alt=\"\"\n" +
                "                style=\"margin-top:260.5pt; -aw-left-pos:0pt; -aw-rel-hpos:margin; -aw-rel-vpos:margin; -aw-top-pos:0pt; -aw-wrap-type:none; position:absolute\"/>\n" +
                "            </span>\n" +
                "            <span\n" +
                "                style=\"-aw-import:ignore\">&#xa0;\n" +
                "            </span>\n" +
                "        </p>\n" +
                "    </div>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\"><span>Paragraph1</span></p>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\"><span>Paragraph2</span></p>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\"><span style=\"-aw-import:ignore\">&#xa0;</span></p>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\"><span style=\"-aw-import:ignore\">&#xa0;</span></p>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\">\n" +
                "        <a name=\"_cmntref1\"><img src=\"output.002.png\" width=\"122\" height=\"78\" alt=\"\" style=\"-aw-left-pos:0pt; -aw-rel-hpos:column; -aw-rel-vpos:paragraph; -aw-top-pos:0pt; -aw-wrap-type:inline\"/>\n" +
                "        </a>\n" +
                "    </p>\n" +
                "    <p style=\"margin-top:0pt; margin-bottom:0pt\">\n" +
                "        <span style=\"font-family:'Times New Roman'\">Test</span>\n" +
                "        <span style=\"-aw-comment-end:_cmntref1\">\n" +
                "        </span>\n" +
                "        <a href=\"#_cmnt1\" style=\"text-decoration:none\">\n" +
                "            <span>[nk1]</span>\n" +
                "        </a>\n" +
                "    </p>\n" +
                "    <div style=\"-aw-headerfooter-type:footer-primary; clear:both\"><p\n" +
                "            style=\"margin-top:0pt; margin-bottom:0pt; font-size:12pt\"><span style=\"font-weight:bold; color:#ff0000\">Created with an evaluation copy of Aspose.Words. To discover the full versions of our APIs please visit: https://products.aspose.com/words/</span>\n" +
                "    </p></div>\n" +
                "</div>\n" +
                "<hr style=\"width:33%; height:1px; text-align:left\"/>\n" +
                "<div id=\"_cmnt1\"\n" +
                "     style=\"-aw-comment-author:'neil king'; -aw-comment-datetime:'2024-04-15T18:33:00'; -aw-comment-initial:'nk'\"><p\n" +
                "        style=\"margin-top:0pt; margin-bottom:0pt; text-align:left\"><a href=\"#_cmntref1\"\n" +
                "                                                                      style=\"text-decoration:none\"><span>[nk1]</span></a><span>comment</span>\n" +
                "</p></div>\n" +
                "</body>\n" +
                "</html>";

        Document document = Jsoup.parse(html);
        Elements elements = document.select("[name~=^_cmntref\\d+]");

        for (Element element : elements) {
            String commentRef = element.attr("name");
            String regex = "-aw-comment-end:" + commentRef;
            Pattern pattern = Pattern.compile(regex);
            Elements targetElements = document.select("span[style~=" + regex + "]");

            for (Element targetElement : targetElements) {
                targetElement.attr("style", "background-color: red");
            }
        }

        String modifiedHtml = document.html();
        System.out.println(modifiedHtml);
    }
}
