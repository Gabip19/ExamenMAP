package org.examen.examenmap.service;

import org.examen.examenmap.domain.Hotel;
import org.examen.examenmap.domain.Location;
import org.examen.examenmap.domain.SpecialOffer;
import org.examen.examenmap.repository.database.HotelDatabaseRepo;
import org.examen.examenmap.repository.database.LocationDatabaseRepo;
import org.examen.examenmap.repository.database.SpecialOfferDatabaseRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service {
    private final LocationDatabaseRepo locationRepo;
    private final HotelDatabaseRepo hotelsRepo;
    private final SpecialOfferDatabaseRepo offersRepo;

    public Service(LocationDatabaseRepo locationRepo, HotelDatabaseRepo hotelsRepo, SpecialOfferDatabaseRepo offersRepo) {
        this.locationRepo = locationRepo;
        this.hotelsRepo = hotelsRepo;
        this.offersRepo = offersRepo;

//        hotelsRepo.save(new Hotel(1d, 1d, "Hotel 111", 3, 20d, Hotel.HotelType.family));
//        offersRepo.save(new SpecialOffer(1d, 1d, LocalDate.parse("2012-05-18"), LocalDate.parse("2014-12-12"), 10));
    }

    public ArrayList<Location> getAllLocations() {
        return new ArrayList<>((Collection<Location>) locationRepo.findAll());
    }

    public ArrayList<Hotel> getAllHotels() {
        return new ArrayList<>((Collection<Hotel>) hotelsRepo.findAll());
    }

    public ArrayList<SpecialOffer> getAllSpecialOffers() {
        return new ArrayList<>((Collection<SpecialOffer>) offersRepo.findAll());
    }

    public List<Hotel> getAllHotelsFromLocation(Location location) {
        return getAllHotels()
                .stream()
                .filter(hotel -> hotel.getLocationId().equals(location.getId()))
                .toList();
    }

    public List<SpecialOffer> getHotelOffersFromPeriod(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(endDate)) {
            return getAllSpecialOffers()
                    .stream()
                    .filter(offer ->
                            offer.getHotelId().equals(hotel.getId()) &&
                            offer.getStartDate().isAfter(startDate) &&
                            offer.getEndDate().isBefore(endDate)
                    ).toList();
        }
        throw new RuntimeException("Invalid dates.\n");
    }
}
