package gift;

import java.io.IOException;
import java.nio.ByteBuffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Image {

    private Bitmap image;

    private Image(Bitmap image) {
        this.image = image;       
    }                               

    public static Image getImageFromFile(String fileName) throws IOException {
        return new Image(BitmapFactory.decodeFile(fileName));
    }                               

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void scale(int width, int height) {
        image = Bitmap.createScaledBitmap(image, width, height, false);
    }

    public boolean hasCorrectType() {
        return false;
    }

    public void correct(int width, int height) {
        scale(width, height);
    }

    public byte[] getData() {

        if (image.getConfig() != Bitmap.Config.ARGB_8888) {
            image = image.copy(Bitmap.Config.ARGB_8888, true);
        }

        int bytes = image.getByteCount();

        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        image.copyPixelsToBuffer(buffer);

        // Converting from ARGB_8888 to 3BYTE_BGR that is requred by AnimatedGifEncoder
        byte[] temp = buffer.array();

        byte[] pixels = new byte[(temp.length / 4) * 3];

        
        for (int i = 0; i < (temp.length / 4); i++) {
            pixels[i * 3] = temp[i * 4 + 3];     // B
            pixels[i * 3 + 1] = temp[i * 4 + 2]; // G
            pixels[i * 3 + 2] = temp[i * 4 + 1]; // R
        }

        return pixels;
    }
}
