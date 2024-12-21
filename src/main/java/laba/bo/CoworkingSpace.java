package laba.bo;

import java.util.Objects;

public class CoworkingSpace {

    private final int id;
    private final String type;
    private final double price;
    private boolean isAvailable;

    public CoworkingSpace(int id, String type, double price, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoworkingSpace that = (CoworkingSpace) o;
        return id == that.id && Double.compare(
                price, that.price) == 0 && isAvailable == that.isAvailable && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, isAvailable);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + type + ", Price: $" + price + ", Available: " + isAvailable;
    }
}
