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
            System.out.println("3. Mostrar árbol");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
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
                        Integer encontrado = arbol.search(buscar);
                        System.out.println("Clave encontrada: " + encontrado);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Debe ingresar un número entero.");
                        sc.nextLine();
                    } catch (ItemNoFound | ExceptionIsEmpty e) {
                        System.out.println("Error: " + e.getMessage());
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
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 4);
        sc.close();
    }
}

