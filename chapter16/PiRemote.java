package com.prefect.pi;

import java.rmi.*;

interface PiRemote extends Remote {
    double getPi() throws RemoteException;
}
