import java.util.ArrayList;
import exeptions.*;

public class BNode<E extends Comparable<E>>{

    protected ArrayList<E> keys;
    protected ArrayList <BNode<E>> childs;
    protected int count;
    protected int idNode;
}
