package org.examen.examenmap.utils.events;


import org.examen.examenmap.domain.Offer;

public class BookingEvent implements Event {
    private final ChangeEventType type;
    private final Offer offer;

    public BookingEvent(ChangeEventType type, Offer offer) {
        this.type = type;
        this.offer = offer;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Offer getOffer() {
        return offer;
    }
}
