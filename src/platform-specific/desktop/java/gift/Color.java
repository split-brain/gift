package gift;

public class Color {
    java.awt.Color color;
    
    public Color(int r, int g, int b) {
        color = new java.awt.Color(r, g, b);
    }

    public int getRed() {
        return color.getRed();
    }
    
    public int getGreen() {
        return color.getGreen();
    }

    public int getBlue() {
        return color.getBlue();
    }
}
