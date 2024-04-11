package com.supremepole.aspose.words.html;

import com.aspose.words.IImageSavingCallback;
import com.aspose.words.ImageSavingArgs;
import com.aspose.words.Shape;

import java.io.FileOutputStream;

public class ChangeImageName implements IImageSavingCallback{

    private String path;

    public ChangeImageName(String path){
        this.path=path;
    }

    @Override
    public void imageSaving(ImageSavingArgs imageSavingArgs) throws Exception {
        System.out.println(imageSavingArgs.getImageFileName());
        Shape shape=(Shape) imageSavingArgs.getCurrentShape();
        imageSavingArgs.setImageFileName(shape.getAlternativeText()+".png");
        imageSavingArgs.setImageStream(new FileOutputStream(path.concat("/").concat(imageSavingArgs.getImageFileName())));
    }

}
