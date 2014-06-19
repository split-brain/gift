package gift;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Image {

    private BufferedImage image;

    private Image(BufferedImage bufferedImage) {
        image = bufferedImage;
    }

    public static Image getImageFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = javax.imageio.ImageIO.read(fis);
        fis.close();
        return new Image(image);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void scale(int width, int height) {
        BufferedImage resized = new BufferedImage(width, height, image.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
        image = resized;
        g.dispose();
    }

    public boolean hasCorrectType() {
        return (image.getType() == BufferedImage.TYPE_3BYTE_BGR);
    }

    public void correct(int width, int height) {
        BufferedImage temp =
            new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = temp.createGraphics();
        g.drawImage(image, 0, 0, null);
        image = temp;
    }

    public byte[] getData() {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }
}
