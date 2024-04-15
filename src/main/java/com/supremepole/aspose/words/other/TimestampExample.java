package com.supremepole.aspose.words.other;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampExample {
    public static void main(String[] args) {
        String dateTimeString = "2024-04-15T17:51:00";

        // 定义日期时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            // 解析字符串为 java.util.Date 对象
            java.util.Date date = formatter.parse(dateTimeString);

            // 转换为 java.sql.Timestamp 对象
            Timestamp timestamp = new Timestamp(date.getTime());
            System.out.println("Timestamp: " + timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
