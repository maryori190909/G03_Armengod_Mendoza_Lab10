
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
    }
