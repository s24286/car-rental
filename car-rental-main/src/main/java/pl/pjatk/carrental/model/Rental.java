package pl.pjatk.carrental.model;

public class Rental {
    private final User user;
    private final Car car;

    public Rental(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return user + " " + car;
    }
}
