package org.examen.examenmap.service;

import javafx.collections.ObservableList;
import org.examen.examenmap.domain.Offer;
import org.examen.examenmap.repository.database.OfferDatabaseRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service {
    private final OfferDatabaseRepo offerRepo;

    public Service(OfferDatabaseRepo offerRepo) {
        this.offerRepo = offerRepo;
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
}
