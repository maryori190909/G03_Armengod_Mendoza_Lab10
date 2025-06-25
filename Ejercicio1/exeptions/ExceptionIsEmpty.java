package Ejercicio1.exeptions;
public class ExceptionIsEmpty extends Exception{

    public ExceptionIsEmpty(String mens){
    super(mens);
    }
    public ExceptionIsEmpty(){
    super("La estructura se encuentra vacia");
    }


}
