package model;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observers = new ArrayList<>();


    //MODIFIES:this
    //EFFECTS: adds an Observer to the list of observers owned by this
    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    //EFFECTS: notify each observer to update
    public void notify(int before, int after) {
        for (Observer observer : observers) {
            observer.update(before, after);
        }
    }
}
