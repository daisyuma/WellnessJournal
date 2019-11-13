package model;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notify(int before, int after) {
        for (Observer observer : observers) {
            observer.update(before,after);
        }
    }
}
