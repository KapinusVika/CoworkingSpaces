package laba.bo;

import java.util.Objects;

public class Reservation {
    private static int nextReservationId = 1;
    private final int reservationId;
    private final CoworkingSpace space;
    private final String customerName;
    private final String date;
    private final String startTime;
    private final String endTime;

    public Reservation(CoworkingSpace space, String customerName, String date, String startTime, String endTime) {
        this.reservationId = nextReservationId++;
        this.space = space;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getReservationId() {
        return reservationId;
    }

    public CoworkingSpace getSpace() {
        return space;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && Objects.equals(space, that.space) && Objects.equals(customerName, that.customerName) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, space, customerName, date, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Space: " + space.getType() + ", Date: " + date +
                ", Time: " + startTime + " - " + endTime + ", Customer: " + customerName;
    }
}