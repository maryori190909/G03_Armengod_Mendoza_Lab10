package Ejercicio1;
import Ejercicio1.btre.BTree;
import Ejercicio1.btre.RegistroEstudiante;

public class Main {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucía"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan"));

        System.out.println("Buscar 115: " + arbol.buscarNombre(115));
        System.out.println("Buscar 132: " + arbol.buscarNombre(132));
        System.out.println("Buscar 999: " + arbol.buscarNombre(999));

        arbol.eliminarCodigo(101);

        arbol.insert(new RegistroEstudiante(106, "Sara"));

        System.out.println("Buscar 106: " + arbol.buscarNombre(106));
    }


}
