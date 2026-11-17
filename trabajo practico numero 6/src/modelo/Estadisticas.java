package modelo;

import java.io.Serializable;

public class Estadisticas implements Serializable {
    private static final long serialVersionUID = 1L;

    private double promedio;
    private double maximo;
    private double minimo;
    private double desviacionEstandar;

    public Estadisticas(double promedio, double maximo, double minimo, double desviacionEstandar) {
        this.promedio = promedio;
        this.maximo = maximo;
        this.minimo = minimo;
        this.desviacionEstandar = desviacionEstandar;
    }

    public double getPromedio() { return promedio; }
    public double getMaximo() { return maximo; }
    public double getMinimo() { return minimo; }
    public double getDesviacionEstandar() { return desviacionEstandar; }

    @Override
    public String toString() {
        return String.format("Promedio: %.2f | Máximo: %.2f | Mínimo: %.2f | Desviación Est.: %.2f",
                promedio, maximo, minimo, desviacionEstandar);
    }
}