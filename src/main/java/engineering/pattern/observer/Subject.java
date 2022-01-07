package engineering.pattern.observer;

import java.util.ArrayList;

public abstract class Subject {

    private ArrayList<Observer> observerArrayList ;

    public Subject() {
        observerArrayList = new ArrayList<>() ;
    }

    public void attach(Observer newObserver) {
        observerArrayList.add(newObserver) ;
    }

    public void detach(Observer removeObserver) {
        observerArrayList.remove(removeObserver) ;
    }

    protected void notifyObservers() {
        System.out.println("Notify");
        for (Observer observer : observerArrayList) {
            observer.update();
        }
    }


}
