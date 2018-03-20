import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class FiltrBlur {
    public static BufferedImage filtrBlur(BufferedImage image){

        BufferedImage imgBlur = new BufferedImage(image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics2D ig = imgBlur.createGraphics();
        ig.drawImage(image, 0, 0, null);

        BufferedImageOp filtr =	new ConvolveOp(new Kernel(3, 3, new float[] {
                .1111f,.1111f,.1111f,
                .1111f,.1111f,.1111f,
                .1111f,.1111f,.1111f,}));

        imgBlur = filtr.filter(image, null);

        return imgBlur;
    }
}
