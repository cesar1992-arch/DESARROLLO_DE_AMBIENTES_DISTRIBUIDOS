package interfaces;

import modelo.Estadisticas;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioDistribuido extends Remote {

    Estadisticas calcularEstadisticas(double[] numeros) throws RemoteException;

    boolean validarDocumento(String documento) throws RemoteException;

    String[] filtrarTextos(String[] textos, String patron) throws RemoteException;
}