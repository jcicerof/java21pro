import javax.swing.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.awt.GridLayout;
import java.io.File;

public class MultiMidiApplet extends javax.swing.JApplet {
    public void init() {
        JPanel pane = new JPanel();
        String[] midiFile = new String[10];
        for (int i = 1; i < 11; i++) {
            String inFile = getParameter("file" + i);
            if (inFile != null)
                midiFile[i-1] = inFile;
        }
        MultiMidiPlayer midi = new MultiMidiPlayer(midiFile);
        pane.add(midi);
        setContentPane(pane);
    }
}

class MultiMidiPlayer extends JPanel implements Runnable, ActionListener {
    Thread runner;
    JButton play = new JButton("Play");
    JButton stop = new JButton("Stop");
    JLabel message = new JLabel();
    JComboBox tempoBox = new JComboBox();
    float tempo = 1.0F;
    Sequence currentSound;
    Sequencer player;
    String[] songFile;
    int songToPlay;

    MultiMidiPlayer(String[] songs) {
        super();
        songFile = songs;
        play.addActionListener(this);
        stop.setEnabled(false);
        stop.addActionListener(this);
        for (float i = 0.25F; i < 7F; i += 0.25F)
            tempoBox.addItem("" + i);
        tempoBox.setSelectedItem("1.0");
        tempoBox.setEnabled(false);
        tempoBox.addActionListener(this);
        setLayout(new GridLayout(2, 1));
        add(message);
        JPanel buttons = new JPanel();
        JLabel tempoLabel = new JLabel("Tempo: ");
        buttons.add(play);
        buttons.add(stop);
        buttons.add(tempoLabel);
        buttons.add(tempoBox);
        add(buttons);
        if (songFile.length == 0) {
            play.setEnabled(false);
        }
     }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JButton) {
            if (evt.getSource() == play)
                play();
            else
                stop();
        } else {
            String item = (String)tempoBox.getSelectedItem();
            try {
                tempo = Float.parseFloat(item);
                player.setTempoFactor(tempo);
                message.setText("Playing " + songFile[songToPlay] + " at "
                    + tempo + " tempo");
            } catch (NumberFormatException ex) {
                message.setText(ex.toString());
            }
        }
    }

    void play() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
            play.setEnabled(false);
            stop.setEnabled(true);
            tempoBox.setEnabled(true);
        }
    }

    void stop() {
        if (runner != null) {
            runner = null;
            stop.setEnabled(false);
            play.setEnabled(true);
            tempoBox.setEnabled(false);
        }
    }

    public void run() {
        try {
            player = MidiSystem.getSequencer();
        } catch (Exception ex) {
            message.setText(ex.toString());
        }
        Thread thisThread = Thread.currentThread();
        while (runner == thisThread) {
            for (songToPlay = 0; songToPlay < songFile.length; songToPlay++) {
                if (songFile[songToPlay] != null) {
                    try {
                        File song = new File(songFile[songToPlay]);
                        currentSound = MidiSystem.getSequence(song);
                        player.open();
                        player.setSequence(currentSound);
                        player.setTempoFactor(tempo);
                        player.start();
                        message.setText("Playing " + songFile[songToPlay] + " at "
                            + tempo + " tempo");
                        while (player.isRunning() && runner != null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) { }
                        }
                        message.setText("");
                        player.close();
                    } catch (Exception ex) {
                        message.setText(ex.toString());
                        break;
                    }
                }
            }
        }
    }
}
