import java.awt.*;

public class Picture extends java.applet.Applet {
    Image searchImage;
    
    public void init() {
        searchImage = getImage(getCodeBase(), "faulkner.jpg");
    }

    public void paint(Graphics screen) {
        screen.drawImage(searchImage, 0, 0, this);
    }
}
