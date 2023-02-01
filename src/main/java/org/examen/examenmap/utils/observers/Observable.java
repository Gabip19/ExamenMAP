package org.examen.examenmap.utils.observers;

import org.examen.examenmap.utils.events.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> observer);
    void removeObserver(Observer<E> observer);
    void notifyObservers(E event);
}
