package Actividad1;
import Actividad1.btre.BTree;
import Actividad1.exeptions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree<Integer> arbol = new BTree<> (4);
        int opcion;
        do {
            System.out.println("\n--- MENÚ ÁRBOL B ---");
            System.out.println("1. Insertar clave");
            System.out.println("2. Buscar clave");
            System.out.println("3. Mostrar arbol");
            System.out.println("4. Eliminar clave");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

        switch (opcion) {
                case 1:
                    System.out.print("Ingrese la clave a insertar: ");
                    int clave = sc.nextInt();
                    arbol.insert(clave);
                    break;
                case 2:
                    System.out.print("Ingrese la clave a buscar: ");
                    try {
                        int buscar = sc.nextInt();
                        boolean encontrado = arbol.searchNode(buscar);
                        if (!encontrado) {
                            System.out.println("La clave no pudo encontrarse en el arbol.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida, debe ingresar un numero entero.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    try {
                        System.out.println(arbol.toString());
                    } catch (Exception e) {
                        System.out.println("Error al imprimir: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese la clave a eliminar: ");
                    try {
                        int eliminar = sc.nextInt();
                        arbol.remove(eliminar);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida, debe ingresar un numero entero.");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 5);
        sc.close();
    }
}

