import java.util.ArrayList;
import exeptions.*;

public class BNode<E extends Comparable<E>>{

    protected ArrayList<E> keys;
    protected ArrayList <BNode<E>> childs;
    protected int count;
    protected int idNode;

    public BNode(int n) {
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count = 0;

        for (int i = 0; i < n - 1; i++){
            this.keys.add(null);}
        for (int i = 0; i < n; i++){
            this.childs.add(null);}
        }

    public boolean nodeFull(int n) {
        return count == n;
    }

    public boolean searchNode(E key, int[] posi){
        int i =0;
        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        posi[0] = i;
        if (i < count && key.compareTo(keys.get(i)) == 0) {
            return true;
        } else {
    return false;
}
}
    public boolean nodeEmpty() {
        return count == 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nodo ID: " + idNode + " , Claves: ");
    for (int i = 0; i < count; i++) {
        sb.append(keys.get(i)).append(" ");
    }
    return sb.toString();
    }
}

