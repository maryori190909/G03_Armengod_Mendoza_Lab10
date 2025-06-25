package Ejercicio1.btre;

public class RegistroEstudiante implements Comparable<RegistroEstudiante>{
    private int cod;
    private String nombre;

    public RegistroEstudiante(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public int getCodigo() { return cod; }
    public String getNombre() { return nombre; }

    @Override
    public int compareTo(RegistroEstudiante otro) {
        return Integer.compare(this.cod, otro.cod);
    }

    @Override
    public String toString() {
        return cod + " - " + nombre;
    }
}
