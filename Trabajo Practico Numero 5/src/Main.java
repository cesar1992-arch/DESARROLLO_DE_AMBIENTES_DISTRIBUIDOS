public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("=== INICIO DEL TP5 ===");

        System.out.println("Creando ActorProcesador...");
        ActorProcesador procesador =
                new ActorProcesador();

        Thread hiloProcesador =
                new Thread(procesador);

        hiloProcesador.start();

        System.out.println("Creando ActorSensor...");
        ActorSensor sensor =
                new ActorSensor(procesador);

        Thread hiloSensor =
                new Thread(sensor);

        hiloSensor.start();

        hiloSensor.join();

        Thread.sleep(2000);

        System.out.println();
        System.out.println("===== RESULTADO FINAL =====");

        System.out.println(
                "Mensajes procesados: "
                        + procesador.getContador()
        );

        System.out.println(
                "Promedio final: "
                        + procesador.getPromedio()
        );

        System.exit(0);
    }
}