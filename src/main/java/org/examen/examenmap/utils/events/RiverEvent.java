package org.examen.examenmap.utils.events;

import org.examen.examenmap.domain.River;

public class RiverEvent implements Event {
    private ChangeEventType type;
    private River river;

    public RiverEvent(ChangeEventType type, River river) {
        this.type = type;
        this.river = river;
    }

    public ChangeEventType getType() {
        return type;
    }

    public River getOrder() {
        return river;
    }
}
