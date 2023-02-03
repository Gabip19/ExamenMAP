package org.examen.examenmap.service;

import org.examen.examenmap.domain.Client;
import org.examen.examenmap.domain.Hotel;
import org.examen.examenmap.domain.Location;
import org.examen.examenmap.domain.SpecialOffer;
import org.examen.examenmap.repository.database.ClientDatabaseRepo;
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
    private final ClientDatabaseRepo clientsRepo;

    public Service(LocationDatabaseRepo locationRepo, HotelDatabaseRepo hotelsRepo, SpecialOfferDatabaseRepo offersRepo, ClientDatabaseRepo clientsRepo) {
        this.locationRepo = locationRepo;
        this.hotelsRepo = hotelsRepo;
        this.offersRepo = offersRepo;
        this.clientsRepo = clientsRepo;

//        hotelsRepo.save(new Hotel(1d, 1d, "Hotel 111", 3, 20d, Hotel.HotelType.family));
//        offersRepo.save(new SpecialOffer(7d, 1d, LocalDate.parse("2023-03-02"), LocalDate.parse("2023-03-21"), 50));
//        clientsRepo.save(new Client(1L, "Client 1111", 10, 23, Client.ClientHobby.reading));
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

    public Client getClientWithId(Long id) {
        return clientsRepo.findOne(id);
    }

    public List<SpecialOffer> getOffersForClient(Client client) {
        return getAllSpecialOffers()
                .stream()
                .filter(offer ->
                        offer.getEndDate().isAfter(LocalDate.now()) &&
                        offer.getPercent() < client.getFidelityGrade()
                )
                .toList();
    }

    public Hotel getHotelWithId(Double hotelId) {
        return hotelsRepo.findOne(hotelId);
    }

    public Location getLocationWithId(Double locationId) {
        return locationRepo.findOne(locationId);
    }
}
