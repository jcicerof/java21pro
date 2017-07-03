import javax.swing.*;
import javax.sound.midi.*;
import java.awt.GridLayout;
import java.io.File;

public class PlayMidi extends JFrame {
    
    PlayMidi(String song) {
        super("Play MIDI Files");
        setSize(180, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MidiPanel midi = new MidiPanel(song);
        JPanel pane = new JPanel();
        pane.add(midi);
        setContentPane(pane);
        show();
    }

    public static void main(String[] arguments) {
        if (arguments.length != 1) {
            System.out.println("Usage: java PlayMidi filename");
        } else {
            PlayMidi pm = new PlayMidi(arguments[0]);
        }
    }
}

class MidiPanel extends JPanel implements Runnable {
    Thread runner;
    JProgressBar progress = new JProgressBar();
    Sequence currentSound;
    Sequencer player;
    String songFile;

    MidiPanel(String song) {
        super();
        songFile = song;
        JLabel label = new JLabel("Playing file ...");
        setLayout(new GridLayout(2, 1));
        add(label);
        add(progress);
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        try {
            File file = new File(songFile);
            currentSound = MidiSystem.getSequence(file);
            player = MidiSystem.getSequencer();
            player.open();
            player.setSequence(currentSound);
            progress.setMinimum(0);
            progress.setMaximum((int)player.getMicrosecondLength());
            player.start();
            while (player.isRunning()) {
                progress.setValue((int)player.getMicrosecondPosition());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
            }
            progress.setValue((int)player.getMicrosecondPosition());
            player.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
