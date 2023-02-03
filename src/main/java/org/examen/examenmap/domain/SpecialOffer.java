package org.examen.examenmap.domain;

import java.time.LocalDate;
import java.util.Date;

public class SpecialOffer extends Entity<Double> {
    private Double hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int percent;

    public SpecialOffer(Double id, Double hotelId, LocalDate startDate, LocalDate endDate, int percent) {
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percent = percent;
        setId(id);
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
