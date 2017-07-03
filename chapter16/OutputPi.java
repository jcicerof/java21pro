package com.prefect.pi;

import java.rmi.*;
import java.rmi.registry.*;

public class OutputPi {
    public static void main(String[] arguments) {
        System.setSecurityManager(
            new RMISecurityManager());
        try {
            PiRemote pr =
                (PiRemote)Naming.lookup(
                    "//MainComputer:1010/Pi");
            for (int i = 0; i < 10; i++)
                System.out.println("Pi = " + pr.getPi());
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
            e.printStackTrace();
        }
    }
}
