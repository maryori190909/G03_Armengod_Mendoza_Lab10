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



}
