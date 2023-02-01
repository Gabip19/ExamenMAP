package org.examen.examenmap.utils;

public interface Observable {

    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}
