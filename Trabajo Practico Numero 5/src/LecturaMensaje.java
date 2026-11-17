public class LecturaMensaje implements Mensaje {

    private final double valor;

    public LecturaMensaje(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}