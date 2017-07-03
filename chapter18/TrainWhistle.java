import java.applet.*;
import java.net.*;

public class TrainWhistle {
    public static void main(String[] arguments) {
        try {
            URL bgSoundFile = new URL("file:train.wav");
            AudioClip bgSound = Applet.newAudioClip(bgSoundFile);
            URL beepFile = new URL("file:whistle.wav");
            AudioClip beep = Applet.newAudioClip(beepFile);
            if (bgSound != null)
                bgSound.loop();
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(9000);
                    if (beep != null)
                        beep.play();
                } catch (InterruptedException e) { }
            }
        } catch (MalformedURLException mue) {
            System.out.println("Bad URL: " + mue.getMessage());
        }
    }
}
