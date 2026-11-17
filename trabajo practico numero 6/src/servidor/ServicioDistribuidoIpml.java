package servidor;

import interfaces.ServicioDistribuido;
import modelo.Estadisticas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class ServicioDistribuidoImpl extends UnicastRemoteObject implements ServicioDistribuido {

    // Constructor obligatorio de RMI
    protected ServicioDistribuidoImpl() throws RemoteException {
        super();
    }

    @Override
    public Estadisticas calcularEstadisticas(double[] numeros) throws RemoteException {
        System.out.println("[Servidor] Petición recibida: Calcular estadísticas.");

        if (numeros == null || numeros.length == 0) {
            return new Estadisticas(0, 0, 0, 0);
        }

        double suma = 0;
        double maximo = numeros[0];
        double minimo = numeros[0];

        for (double n : numeros) {
            suma += n;
            if (n > maximo) maximo = n;
            if (n < minimo) minimo = n;
        }

        double promedio = suma / numeros.length;
        double sumaVarianza = 0;

        for (double n : numeros) {
            sumaVarianza += Math.pow(n - promedio, 2);
        }

        double desviacionEstandar = Math.sqrt(sumaVarianza / numeros.length);

        return new Estadisticas(promedio, maximo, minimo, desviacionEstandar);
    }

    @Override
    public boolean validarDocumento(String documento) throws RemoteException {
        System.out.println("[Servidor] Petición recibida: Validar documento " + documento);

        if (documento == null || documento.trim().isEmpty()) return false;

        // Validación simple: 8 dígitos (DNI) u 11 dígitos (CUIT)
        String limpio = documento.replace("-", "").replace(" ", "").trim();
        return limpio.matches("\\d{8}") || limpio.matches("\\d{11}");
    }

    @Override
    public String[] filtrarTextos(String[] textos, String patron) throws RemoteException {
        System.out.println("[Servidor] Petición recibida: Filtrar textos con patrón '" + patron + "'");

        if (textos == null || patron == null) return new String[0];

        List<String> resultados = new ArrayList<>();
        for (String texto : textos) {
            if (texto != null && texto.toLowerCase().contains(patron.toLowerCase())) {
                resultados.add(texto);
            }
        }
        return resultados.toArray(new String[0]);
    }
}