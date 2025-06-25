package Ejercicio1.exeptions;

public class ItemDuplicated extends Exception {
    public ItemDuplicated(String mens){
    super(mens);
    }
    public ItemDuplicated(){
    super("El elemento esta duplicado en la estructura actual");
    }
}
