package com.supremepole.aspose.words.html;

import com.aspose.words.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class HTMLToWordConverter2 {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String htmlFilePath = currentDirectory + "\\input.html";
        String attachmentFilePath = currentDirectory + "\\attachment.docx"; // 特定的Word附件路径

        try {
            // 确保特定的Word附件存在
            if (!Files.exists(Paths.get(attachmentFilePath))) {
                throw new IOException("The specified attachment file does not exist.");
            }

            // 使用HtmlLoadOptions来处理HTML中的图像
            HtmlLoadOptions loadOptions = new HtmlLoadOptions();
            loadOptions.setLoadFormat(com.aspose.words.LoadFormat.HTML);
            loadOptions.setResourceLoadingCallback(new HandleResourceLoading(attachmentFilePath));

            // 加载HTML文件到Aspose.Words Document对象
            Document doc = new Document(htmlFilePath, loadOptions);

            // 保存为Word文档
            String outputFilePath = currentDirectory + "\\outputHtmlDoc.docx";
            doc.save(outputFilePath, SaveFormat.DOCX);

            System.out.println("HTML转换为Word文档成功！");
        } catch (Exception e) {
            System.out.println("转换过程中发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 自定义资源加载回调类
    private static class HandleResourceLoading implements IResourceLoadingCallback {
        private String attachmentFilePath;

        public HandleResourceLoading(String attachmentFilePath) {
            this.attachmentFilePath = attachmentFilePath;
        }

        @Override
        public int resourceLoading(ResourceLoadingArgs args) throws Exception {
            if (args.getResourceType() == ResourceType.IMAGE) {
                // 直接使用特定的Word附件替换图片
                byte[] fileData = Files.readAllBytes(Paths.get(attachmentFilePath));
                args.setData(fileData);
                return ResourceLoadingAction.USER_PROVIDED;
            }
            return ResourceLoadingAction.DEFAULT;
        }
    }
}
