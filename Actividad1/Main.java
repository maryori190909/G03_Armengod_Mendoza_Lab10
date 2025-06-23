package Actividad1;

import Actividad1.btree.BTree;
import Actividad1.exeptions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BTree<Integer> arbol = new BTree<>(4);
        int opcion;
        do {
            System.out.println("\n--- MENÚ ÁRBOL B ---");
            System.out.println("1. Insertar clave");
            System.out.println("2. Buscar clave");
            System.out.println("3. Mostrar árbol");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
        
    }

}
}
