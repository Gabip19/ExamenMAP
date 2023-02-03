package org.examen.examenmap.service;

import org.examen.examenmap.domain.Booking;
import org.examen.examenmap.domain.Offer;
import org.examen.examenmap.repository.database.BookingDatabaseRepo;
import org.examen.examenmap.repository.database.OfferDatabaseRepo;
import org.examen.examenmap.utils.events.BookingEvent;
import org.examen.examenmap.utils.events.ChangeEventType;
import org.examen.examenmap.utils.observers.ConcreteObservable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service extends ConcreteObservable<BookingEvent> {
    private final OfferDatabaseRepo offerRepo;
    private final BookingDatabaseRepo bookingRepo;

    public Service(OfferDatabaseRepo offerRepo, BookingDatabaseRepo bookingRepo) {
        this.offerRepo = offerRepo;
        this.bookingRepo = bookingRepo;
//        offerRepo.save(new Offer("Destinatie 111111", "Hotel 1", 10, 12f, 5));
    }

    public ArrayList<Offer> getAllOffers() {
        return new ArrayList<>((Collection<Offer>) offerRepo.findAll());
    }

    public List<Offer> searchOfferByDestination(String destinationSeq) {
        return getAllOffers()
                .stream()
                .filter(offer -> offer.getDestination().contains(destinationSeq))
                .toList();
    }

    public void bookDestination(Offer offer, String clientName, String address, int personNum) {
        Booking booking = new Booking(offer.getId(), clientName, address, personNum);
        bookingRepo.save(booking);

//        offer.setPersonNum(offer.getPersonNum() - personNum);
//        if (offer.getPersonNum() == 0) {
//            offerRepo.delete(offer.getId());
//        }
//        else {
            offerRepo.update(offer);
//        }

        notifyObservers(new BookingEvent(ChangeEventType.UPDATE, offer));
    }
}
