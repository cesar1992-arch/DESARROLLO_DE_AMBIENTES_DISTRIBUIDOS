import java.util.Random;

public class ActorSensor implements Runnable {

    private final ActorProcesador procesador;

    public ActorSensor(ActorProcesador procesador) {
        this.procesador = procesador;
    }

    @Override
    public void run() {

        System.out.println("Enviando 500 mensajes...");

        Random random = new Random();

        for (int i = 0; i < 500; i++) {

            double valor = 20 + random.nextDouble() * 15;

            procesador.enviar(
                    new LecturaMensaje(valor)
            );
        }
    }
}
