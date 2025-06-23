
import java.util.ArrayList;
import exeptions.*;
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
        E mediana = push(root, cl);
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

    }

    
