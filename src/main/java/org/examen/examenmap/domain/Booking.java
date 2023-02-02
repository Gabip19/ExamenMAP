package org.examen.examenmap.domain;

import java.util.UUID;

public class Booking extends Entity<UUID> {
    private UUID offerId;
    private String clientName;
    private String address;
    private int personNum;

    public Booking(UUID offerId, String clientName, String address, int personNum) {
        this.offerId = offerId;
        this.clientName = clientName;
        this.address = address;
        this.personNum = personNum;
        setId(UUID.randomUUID());
    }

    public Booking(UUID id, UUID offerId, String clientName, String address, int personNum) {
        this.offerId = offerId;
        this.clientName = clientName;
        this.address = address;
        this.personNum = personNum;
        setId(id);
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }
}
