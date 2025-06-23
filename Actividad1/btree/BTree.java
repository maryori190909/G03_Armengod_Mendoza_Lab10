
import java.util.ArrayList;
import Actividad1.exeptions.*;
public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
    this.orden = orden;
    this.root = null;
    }
    
    public boolean isEmpty() {
    return this.root == null;
    }
}

    public void insert(E cl) {
    try {
        up = false;
        E mediana = push( root , cl);
    if (up) {
        BNode<E> nuevo = new BNode<>(orden);
        nuevo.count = 1;
        nuevo.keys.set(0, mediana);
        nuevo.childs.set(0, root);
        nuevo.childs.set(1, nDes);
        root = nuevo;
    }
    } catch (ItemDuplicated e) {
    System.out.println(e.getMessage());
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

    private void putNode(BNode<E> actual, E cl, BNode<E> rChild, int pos)
    {
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
        String s = " ";
        if (isEmpty()) {
            s += "BTree esta vacio...\n";
        } else {
            s += "Id.Nodo / Claves Nodo / Id.Padre / Id.Hijos\n";
            s += writeTree(this.root, null);
        }
        return s;
    }
    
    private String writeTree(BNode<E> actual, Integer idPadre) {
        if (actual == null) return "";
            StringBuilder sb = new StringBuilder();
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
            sb.append(String.format("%-8d %-16s %-9s %-10s\n",
            actual.idNode,
            claves.toString(),
            idPadre == null ? " -" : "[" + idPadre + "]",
            hijos.isEmpty() ? " - " : hijos.toString() ));
        for (int i = 0; i <= actual.count; i++) {
            BNode<E> hijo = actual.childs.get(i);
        if (hijo != null) {
            sb.append(writeTree(hijo, actual.idNode));
        }
        }
            return sb.toString();
        }
}


    
