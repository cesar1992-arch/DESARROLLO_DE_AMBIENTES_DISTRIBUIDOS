public class ActorProcesador extends Actor {

    private int contador = 0;
    private double suma = 0;

    @Override
    protected void procesarMensaje(Mensaje mensaje) {

        if (mensaje instanceof LecturaMensaje lectura) {

            contador++;

            suma += lectura.getValor();

            double promedio = suma / contador;

            System.out.println(
                    "Mensaje #" + contador +
                            " | Valor: " + lectura.getValor() +
                            " | Promedio: " + promedio
            );
        }
    }

    public int getContador() {
        return contador;
    }

    public double getPromedio() {

        if (contador == 0) {
            return 0;
        }

        return suma / contador;
    }
}