package org.examen.examenmap.utils.observers;


import org.examen.examenmap.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E event);
}

