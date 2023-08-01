package model;

import java.sql.Date;

public class Ticket {

    private int driver_license_number;
    private String plate;
    private Date date;
    private Date expiry_date;
    private double value;
    private String reason;
    private boolean isPaid = false;

    public boolean isPaid() {
        boolean b = isPaid;
        return b;
    }

    public boolean setPaid(int paid) {
        if(paid == 1) {
            isPaid = true;
            return true;
        }
        return false;
    }

    public boolean payTicket(double value) {
        if(isPaid == true) {
            System.out.println("Ticket already paid");
            return false;
        }
        if(value >= this.value) {
            setPaid(1);
            return true;
        }
        System.out.println("Value not enough to pay ticket");
        return false;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value <= 0 ? 0 : value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(int reason) {
        String s = switch (reason) {
            case 1 -> this.reason = "Speeding";
            case 2 -> this.reason = "Red light";
            case 3 -> this.reason = "Illegal parking";
            case 4 -> this.reason = "Reckless driving";
            case 5 -> this.reason = "Driving Under the Influence";
            default -> throw new IllegalStateException("No such reason");
        };
    }

    public int getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(int driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public String toString() {
        return "Ticket info: " + "\n" +
                "- driver_license_number: " + driver_license_number + "\n" +
                "- plate: " + plate + "\n + " +
                "- date: " + date + "\n" + "- expiry_date: " + expiry_date + "\n" + "- value: " + value + "\n"
                + "- reason: " + reason;
    }


}