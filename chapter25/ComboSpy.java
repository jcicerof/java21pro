import java.awt.*;
import javax.swing.*;
import javax.accessibility.*;

public class ComboSpy implements Runnable {
    Thread runner;
    JComboBox profession;

    ComboSpy(JComboBox pro) {
        profession = pro;
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        AccessibleContext ac =
            profession.getAccessibleContext();
        System.out.println("\nName: " +
            ac.getAccessibleName());
        System.out.println("Description: " +
            ac.getAccessibleDescription());
        System.out.println("Role: " +
            ac.getAccessibleRole());
        while (runner != null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { }
            AccessibleAction aa =
                ac.getAccessibleAction();
            int count = aa.getAccessibleActionCount();
            for (int i = 0; i < count; i++)
                System.out.println("ActionDescription: " +
                    aa.getAccessibleActionDescription(i));
            try {
                aa.doAccessibleAction(0);
            } catch (IllegalComponentStateException e) { }
        }
    }
}
