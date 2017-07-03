package com.prefect.pi;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Pi extends UnicastRemoteObject
    implements PiRemote {

    public double getPi() throws RemoteException {
        return Math.PI;
    }

    public Pi() throws RemoteException {
    }

    public static void main(String[] arguments) {
        System.setSecurityManager(new
            RMISecurityManager());
        try {
            Pi p = new Pi();
            Naming.bind("//MainComputer:1010/Pi", p);
        } catch (Exception e) {
            System.out.println("Error -- " +
                e.toString());
                e.printStackTrace();
        }
    }
}
