package Ejercicio1.btre;
import java.util.ArrayList;

import Ejercicio1.exeptions.*;

public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;
    private static int contadorId = 0;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        try {
            up = false;
            E mediana = push(root, cl);
            if (up) {
                BNode<E> nuevo = new BNode<>(orden);
                nuevo.count = 1;
                nuevo.idNode = contadorId++;
                nuevo.keys.set(0, mediana);
                nuevo.childs.set(0, root);
                nuevo.childs.set(1, nDes);
                root = nuevo;
            }
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }

    private E push(BNode<E> actual, E cl) throws ItemDuplicated {
        int[] pos = new int[1];
        E mediana;
        if (actual == null) {
            up = true;
            nDes = null;
            return cl;
        }
        boolean found = actual.searchNode(cl, pos);
        if (found) {
            throw new ItemDuplicated("La clave " + cl + " ya existe en el arbol.");
        }
        mediana = push(actual.childs.get(pos[0]), cl);
        if (!up) return null;
        if (actual.nodeFull(orden - 1)) {
            mediana = divideNode(actual, mediana, pos[0]);
        } else {
            putNode(actual, mediana, nDes, pos[0]);
            up = false;
        }
        return mediana;
    }

    private void putNode(BNode<E> actual, E cl, BNode<E> rChild, int pos) {
        for (int i = actual.count - 1; i >= pos; i--) {
            actual.keys.set(i + 1, actual.keys.get(i));
            actual.childs.set(i + 2, actual.childs.get(i + 1));
        }
        actual.keys.set(pos, cl);
        actual.childs.set(pos + 1, rChild);
        actual.count++;
    }

    private E divideNode(BNode<E> actual, E cl, int k) {
        BNode<E> rChild = nDes;
        int mid = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nDes = new BNode<>(orden);
        nDes.idNode = contadorId++;
        for (int i = mid; i < orden - 1; i++) {
            nDes.keys.set(i - mid, actual.keys.get(i));
            nDes.childs.set(i - mid + 1, actual.childs.get(i + 1));
        }
        nDes.count = (orden - 1) - mid;
        actual.count = mid;
        if (k <= orden / 2) {
            putNode(actual, cl, rChild, k);
        } else {
            putNode(nDes, cl, rChild, k - mid);
        }
        E median = actual.keys.get(actual.count - 1);
        nDes.childs.set(0, actual.childs.get(actual.count));
        actual.count--;
        up = true;
        return median;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "El árbol B está vacío...\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-10s %-17s %-10s %-10s\n", "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
            writeTree(this.root, null, sb);
            return sb.toString();
        }
    }

    private void writeTree(BNode<E> actual, Integer idPadre, StringBuilder sb) {
        if (actual == null) return;

        StringBuilder claves = new StringBuilder("(");
        for (int i = 0; i < actual.count; i++) {
            claves.append(actual.keys.get(i));
            if (i < actual.count - 1) claves.append(", ");
        }
        claves.append(")");

        ArrayList<Integer> hijos = new ArrayList<>();
        for (int i = 0; i <= actual.count; i++) {
            BNode<E> hijo = actual.childs.get(i);
            if (hijo != null) hijos.add(hijo.idNode);
        }

        String padreStr = (idPadre == null) ? "--" : "[" + idPadre + "]";
        String hijosStr = hijos.isEmpty() ? "--" : hijos.toString();

        sb.append(String.format("%-10d %-17s %-10s %-10s\n",
            actual.idNode,
            claves.toString(),
            padreStr,
            hijosStr
        ));

        for (int i = 0; i <= actual.count; i++) {
            BNode<E> hijo = actual.childs.get(i);
            if (hijo != null) {
                writeTree(hijo, actual.idNode, sb);
            }
        }
    }

    public E search(E cl) throws ItemNoFound, ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El arbol esta vacio.");
        return searchRecursive(root, cl);
    }

    private E searchRecursive(BNode<E> node, E cl) throws ItemNoFound {
        if (node == null) throw new ItemNoFound("Clave no encontrada.");
        int i = 0;
        while (i < node.count && cl.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        if (i < node.count && cl.compareTo(node.keys.get(i)) == 0) {
            return node.keys.get(i);
        }
        return searchRecursive(node.childs.get(i), cl);
    }

    public boolean searchNode(E cl) {
        return searchNodeRecursive(root, cl);
    }

    private boolean searchNodeRecursive(BNode<E> node, E cl) {
        if (node == null) return false;
        int i = 0;
        while (i < node.count && cl.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        if (i < node.count && cl.compareTo(node.keys.get(i)) == 0) {
            System.out.println(cl + " se encuentra en el nodo " + node.idNode + " ,en la posicion " + i);
            return true;
        }
        return searchNodeRecursive(node.childs.get(i), cl);
    }

    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El arbol se encuentra vacio, no se puede eliminar.");
            return;
        }
        root = removeRecursive(root, cl);
        if (root != null && root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0);
        }
    }

    private BNode<E> removeRecursive(BNode<E> node, E cl) {
        int idx = 0;
        while (idx < node.count && cl.compareTo(node.keys.get(idx)) > 0) {
            idx++;
        }

        if (idx < node.count && cl.compareTo(node.keys.get(idx)) == 0) {
            if (node.childs.get(idx) == null) {
                for (int i = idx; i < node.count - 1; i++) {
                    node.keys.set(i, node.keys.get(i + 1));
                }
                node.keys.set(node.count - 1, null);
                node.count--;
            } else {
                BNode<E> pred = node.childs.get(idx);
                while (pred.childs.get(pred.count) != null) {
                    pred = pred.childs.get(pred.count);
                }
                E predecesor = pred.keys.get(pred.count - 1);
                node.keys.set(idx, predecesor);
                node.childs.set(idx, removeRecursive(node.childs.get(idx), predecesor));
            }
        } else if (node.childs.get(idx) != null) {
            node.childs.set(idx, removeRecursive(node.childs.get(idx), cl));
        }
        return node;
    }

    public String buscarNombre(int cod){
        RegistroEstudiante resultado = buscarNombreRecursivo(root, cod);
        return (resultado != null) ? resultado.getNombre() : "No fue encontrado";
    }

    private RegistroEstudiante buscarNombreRecursivo(BNode<E> nodo, int cod) {
        if (nodo == null) return null;
        for (int i = 0; i < nodo.count; i++) {
            RegistroEstudiante actual = (RegistroEstudiante) nodo.keys.get(i);
            if (actual.getCodigo() == cod) {
                return actual;
            } else if (cod < actual.getCodigo()) {
                return buscarNombreRecursivo(nodo.childs.get(i), cod);
            }
        }
        return buscarNombreRecursivo(nodo.childs.get(nodo.count), cod);
    }

    public void eliminarCodigo(int cod) {
        eliminarCodigoRecursivo(root, cod);
    }

    private void eliminarCodigoRecursivo(BNode<E> nodo, int cod) {
        if (nodo == null) return;

        for (int i = 0; i < nodo.count; i++) {
            RegistroEstudiante actual = (RegistroEstudiante) nodo.keys.get(i);
            if (actual.getCodigo() == cod) {
                for (int j = i; j < nodo.count - 1; j++) {
                    nodo.keys.set(j, nodo.keys.get(j + 1));
                }
                nodo.keys.set(nodo.count - 1, null);
                nodo.count--;
                System.out.println("El estudiante con codigo " + cod + " fue eliminado.");
                return;
            } else if (cod < actual.getCodigo()) {
                eliminarCodigoRecursivo(nodo.childs.get(i), cod);
                return;
            }
        }
        eliminarCodigoRecursivo(nodo.childs.get(nodo.count), cod);
    }

}

