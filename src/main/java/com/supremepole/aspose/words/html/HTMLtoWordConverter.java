package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTMLtoWordConverter {
    public static void main(String[] args) {
        // HTML文件路径
        String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
        String htmlFilePath = currentDirectory + "\\input.html";

        try {
            // 使用HtmlLoadOptions来处理HTML中的图像
            HtmlLoadOptions loadOptions = new HtmlLoadOptions();
            loadOptions.setLoadFormat(com.aspose.words.LoadFormat.HTML);
            loadOptions.setResourceLoadingCallback(new HandleResourceLoading());
            // 设置进度回调
//            loadOptions.setProgressCallback();

            // 加载HTML文件到Aspose.Words Document对象
            Document doc = new Document(htmlFilePath, loadOptions);


            // 遍历文档中的所有图片
            NodeCollection shapes = doc.getChildNodes(NodeType.SHAPE, true);
            for (Shape shape : (Iterable<Shape>) shapes) {
                if (shape.hasImage()) {
                    if("abc2".equals(shape.getAlternativeText())){
                        DocumentBuilder builder = new DocumentBuilder(doc);
                        builder.moveTo(shape.getParentNode());
                        // 指定要插入的附件文件的路径
                        String attachmentPath = currentDirectory + File.separator + "attachment.docx";
                        builder.insertOleObject(attachmentPath, false, true, null);
                        shape.getParentNode().removeChild(shape);
                    }
                }
            }


            // 保存为Word文档
            String outputFilePath = currentDirectory + "\\outputHtmlDoc.docx";
            doc.save(outputFilePath, SaveFormat.DOCX);

            System.out.println("HTML转换为Word文档成功！");
        } catch (Exception e) {
            System.out.println("转换过程中发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }


    private static class ProgressHandler implements IDocumentLoadingCallback {

        @Override
        public void notify(DocumentLoadingArgs documentLoadingArgs) {

        }
    }


    // 自定义资源加载回调类
    private static class HandleResourceLoading implements IResourceLoadingCallback {
        @Override
        public int resourceLoading(ResourceLoadingArgs args) throws Exception {
            // 处理HTML中的图像资源加载
            if (args.getResourceType() == ResourceType.IMAGE) {
                // 获取图像文件路径
                String imageFilePath = args.getOriginalUri();
                System.out.println(imageFilePath);
                // 获取图像文件名
                String imageName = getImageFileName(imageFilePath);

                String currentDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\html";
                String fileName = "attachment.docx";
                String filePath = currentDirectory + "\\" + fileName;

                // 读取文件内容
                byte[] fileData = Files.readAllBytes(Paths.get(filePath));
                // 设置文件内容为数据
                args.setData(fileData);

                // 返回资源加载完成
                return ResourceLoadingAction.USER_PROVIDED;
            }

            // 其他资源类型按默认方式加载
            return ResourceLoadingAction.DEFAULT;
        }

        // 获取图像文件名
        private String getImageFileName(String imageFilePath) {
            // 获取最后一个斜杠的索引
            int lastIndex = imageFilePath.lastIndexOf("\\");
            if (lastIndex == -1) {
                lastIndex = imageFilePath.lastIndexOf("/");
            }

            // 提取图像文件名
            if (lastIndex != -1) {
                return imageFilePath.substring(lastIndex + 1);
            }

            return imageFilePath;
        }
    }
}
