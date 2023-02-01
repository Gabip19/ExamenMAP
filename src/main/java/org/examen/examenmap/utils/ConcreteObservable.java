package org.examen.examenmap.utils;

import java.util.HashSet;

public abstract class ConcreteObservable implements Observable {

    HashSet<Observer> observers = new HashSet<>();

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
