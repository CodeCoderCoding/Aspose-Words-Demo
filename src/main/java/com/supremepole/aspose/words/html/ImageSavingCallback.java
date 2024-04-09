package com.supremepole.aspose.words.html;

import com.aspose.words.*;

import java.io.FileOutputStream;
import java.util.UUID;

public class ImageSavingCallback implements IImageSavingCallback{

    private String path;

    public ImageSavingCallback(String path){
        this.path=path;
    }

    @Override
    public void imageSaving(ImageSavingArgs imageSavingArgs) throws Exception {
        Shape shape=(Shape) imageSavingArgs.getCurrentShape();
        System.out.println(shape.getImageData().getImageType());

        UUID uuid = UUID.randomUUID();
        String uuidString=uuid.toString();
        System.out.println(imageSavingArgs.getImageFileName());
        imageSavingArgs.setImageFileName(uuidString+".png");
        System.out.println(uuidString);
        imageSavingArgs.setImageStream(new FileOutputStream(path.concat("/").concat(imageSavingArgs.getImageFileName())));
    }

}
