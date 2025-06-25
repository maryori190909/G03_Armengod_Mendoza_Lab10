package Ejercicio1.exeptions;

public class ItemNoFound extends Exception{
    public ItemNoFound(String mens){
    super(mens);
    }
    public ItemNoFound(){
    super("El elemento no se encontro en la estructura");
    }
}