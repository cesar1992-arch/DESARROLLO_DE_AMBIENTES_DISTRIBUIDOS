package cliente;

import interfaces.ServicioDistribuido;
import modelo.Estadisticas;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {

    public static void main(String[] args) {
        try {
            // 1. Conectarse al registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // 2. Buscar el servicio remoto
            ServicioDistribuido servicio = (ServicioDistribuido) registry.lookup("ServicioDistribuido");
            System.out.println("[Cliente] Conectado exitosamente al servidor remoto.");

            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 4) {
                System.out.println("\n--- MENÚ DE OPERACIONES REMOTAS ---");
                System.out.println("1. Calcular Estadísticas Matemáticas");
                System.out.println("2. Validar Documento (DNI/CUIT)");
                System.out.println("3. Filtrar Textos");
                System.out.println("4. Salir");
                System.out.print("Elija una opción: ");

                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese números separados por coma (ej: 10,20,30): ");
                        String[] partes = scanner.nextLine().split(",");
                        double[] numeros = new double[partes.length];
                        for (int i = 0; i < partes.length; i++) {
                            numeros[i] = Double.parseDouble(partes[i].trim());
                        }
                        Estadisticas res = servicio.calcularEstadisticas(numeros);
                        System.out.println("Resultado del servidor -> " + res.toString());
                        break;

                    case 2:
                        System.out.print("Ingrese el documento a validar: ");
                        String doc = scanner.nextLine();
                        boolean esValido = servicio.validarDocumento(doc);
                        System.out.println("Resultado del servidor -> " + (esValido ? "VÁLIDO" : "INVÁLIDO"));
                        break;

                    case 3:
                        System.out.print("Ingrese los textos separados por coma (ej: Hola,mundo,Java): ");
                        String[] textos = scanner.nextLine().split(",");
                        System.out.print("Ingrese el patrón a buscar: ");
                        String patron = scanner.nextLine();
                        String[] filtrados = servicio.filtrarTextos(textos, patron);

                        System.out.print("Resultado del servidor -> Textos encontrados: ");
                        if (filtrados.length == 0) {
                            System.out.println("Ninguno");
                        } else {
                            for (String f : filtrados) {
                                System.out.print("[" + f.trim() + "] ");
                            }
                            System.out.println();
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo del cliente...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("[Cliente] Error de conexión: " + e.getMessage());
            System.err.println("Asegúrese de que el ServidorRMI esté ejecutándose.");
        }
    }
}