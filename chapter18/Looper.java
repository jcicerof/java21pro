import java.awt.*;
import java.applet.AudioClip;

public class Looper extends javax.swing.JApplet implements Runnable {
    AudioClip bgSound;
    AudioClip beep;
    Thread runner;

    public void init() {
        bgSound = getAudioClip(getCodeBase(),"train.wav");
        beep = getAudioClip(getCodeBase(), "whistle.wav");
    }

    public void start() {
         if (runner == null) {
             runner = new Thread(this);
             runner.start();
         }
    }

    public void stop() {
        if (runner != null) {
            if (bgSound != null)
                bgSound.stop();
            runner = null;
        }
    }

    public void run() {
        if (bgSound != null)
            bgSound.loop();
        Thread thisThread = Thread.currentThread();
        while (runner == thisThread) {
            try {
                Thread.sleep(9000);
                if (beep != null)
                    beep.play();
            } catch (InterruptedException e) { }
        }
   }

    public void paint(Graphics screen) {
        Graphics2D screen2D = (Graphics2D)screen;
        screen2D.drawString("Playing Sounds ...", 10, 10);
    }
}
