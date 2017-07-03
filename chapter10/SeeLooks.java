import javax.swing.*;

public class SeeLooks {
    public static void main(String[] arguments) {
        UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < laf.length; i++) {
            System.out.println("Name: " + laf[i].getName());
            System.out.println("Class name: " + laf[i].getClassName() + "\n");
        }
    }
}
