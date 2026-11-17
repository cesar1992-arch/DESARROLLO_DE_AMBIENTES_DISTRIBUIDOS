package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public static void main(String[] args) {
        try {
            // 1. Iniciar el registro de nombres en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("[Servidor] Registro RMI iniciado en el puerto 1099.");

            // 2. Instanciar la implementación del servicio
            ServicioDistribuidoImpl servicio = new ServicioDistribuidoImpl();

            // 3. Publicar el servicio bajo un nombre
            registry.rebind("ServicioDistribuido", servicio);
            System.out.println("[Servidor] Servicio 'ServicioDistribuido' publicado y listo para recibir clientes...");

        } catch (Exception e) {
            System.err.println("[Servidor] Error al iniciar el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
