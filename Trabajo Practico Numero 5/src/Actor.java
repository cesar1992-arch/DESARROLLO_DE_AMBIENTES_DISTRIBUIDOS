import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable {

    protected BlockingQueue<Mensaje> mailbox =
            new LinkedBlockingQueue<>();

    public void enviar(Mensaje mensaje) {

        try {
            mailbox.put(mensaje);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void procesarMensaje(Mensaje mensaje);

    @Override
    public void run() {

        while (true) {

            try {

                Mensaje mensaje = mailbox.take();

                procesarMensaje(mensaje);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}