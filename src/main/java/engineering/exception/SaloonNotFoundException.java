package engineering.exception;

public class SaloonNotFoundException extends Exception {

    SaloonNotFoundException(){
        super();
    }

    SaloonNotFoundException(String message){
        super(message);
    }

}
