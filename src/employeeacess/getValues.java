package employeeacess;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public interface getValues {



    default Date getDate(Scanner scan, Logger logger) {
        Date sqlDate = null;
        boolean validInput = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            String input = scan.nextLine().trim();
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                sqlDate = Date.valueOf(localDate);
                validInput = true;
            } catch (DateTimeParseException e) {
                logger.error("Invalid date input: " + input, e);
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return sqlDate;
    }

    default java.sql.Date getBirthDate(Scanner scan, Logger logger) {
        java.sql.Date bDate = null;
        boolean validInput = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            String input = scan.nextLine().trim();
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                LocalDate today = LocalDate.now();

                if (Period.between(localDate, today).getYears() >= 18) {
                    bDate = java.sql.Date.valueOf(localDate);
                    validInput = true;
                } else {
                    System.out.println("The customer must be at least 18 years old. Please enter a valid date.");
                }
            } catch (DateTimeParseException e) {
                logger.error("Invalid date input: " + input, e);
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return bDate;
    }

    default int getNIF(Scanner scan, Logger logger) {
        int nif = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                nif = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid NIF: " + s);
                System.out.println("Invalid NIF. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return nif;
    }


    default int getDriverLicense(Scanner scan, Logger logger) {
        int driverLicense = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{8}")) {
                driverLicense = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid driver license number: " + s);
                System.out.println("Invalid driver license number. Please enter a 8-digit number.");
            }
        } while (!validInput);

        return driverLicense;
    }


    default int getPolicy(Scanner scan, Logger logger) {
        int policy = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                policy = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid policy number: " + s);
                System.out.println("Invalid policy number. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return policy;
    }

    default String getPlate(Scanner scan, Logger logger) {
        String plate = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
                plate = s;
                validInput = true;
            } else {
                logger.error("Invalid plate: " + s);
                System.out.println("Invalid plate. Please enter a XX-XX-XX format.");
            }
        } while (!validInput);

        return plate;
    }

    default String getString(Scanner scan, Logger logger) {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[a-zA-Z0-9].*[a-zA-Z0-9])[\\w\\s]{2,}$|^[a-zA-Z0-9]+\\s[a-zA-Z0-9]+$")) {
                str = s;
                validInput = true;
            } else {
                logger.error("Invalid word: " + s);
                System.out.println("Invalid word. Please make sure there are no special letters");
            }
        } while (!validInput);

        return str;
    }


    default String getVIN(Scanner scan, Logger logger) {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^[A-Z0-9]{17}$")) {
                str = s;
                validInput = true;
            } else {
                logger.error("Invalid VIN: " + s);
                System.out.println("Invalid VIN. Please enter a 17 digit value.");
            }
        } while (!validInput);

        return str;
    }

    default double getDouble(Scanner scan, Logger logger) {
        double amount = 0.00;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^\\d+\\.\\d{2}$|^\\d+\\.00$")) {
                amount = Double.parseDouble(s);
                validInput = true;
            } else {
                logger.error("Invalid amount: " + s);
                System.out.println("Invalid amount. Please enter a value that ends with a decimal part or .00 .");
            }
        } while (!validInput);

        return amount;
    }

    default int printValues(Scanner scan, Logger logger, Object... vs) {
        boolean validInput = false;
        int decision = -1;
        int i = 1;
        System.out.println("The values entered are: ");
        for (Object o : vs) {
            System.out.println("Value " + i + ": " + o.toString());
            i++;
        }
        System.out.println("If values are correct, press Y(y) to continue, N(n) to cancel.");
        do {
            String s = scan.nextLine().trim();
            if (s.compareToIgnoreCase("Y") == 0) {
                System.out.println("Values confirmed.");
                decision = 1;
                validInput = true;
            } else if (s.compareToIgnoreCase("N") == 0) {
                System.out.println("Procedure cancelled.");
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                logger.warn("Invalid input received.");
            }
        } while (!validInput);

        return decision;
    }


    default String getPassword(Scanner scan, Logger logger) {
        String password = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]{2,})[a-zA-Z0-9!@#$%^&*]{6,10}$\n")) {
                password = s;
                validInput = true;
            } else {
                logger.error("Invalid amount: " + s);
                System.out.println("""
                        Invalid password. 
                        Please make sure the password contains at least:
                        - 1 uppercase letter
                        - 1 lowercase letter
                        - 1 number
                        - 2 special characters
                        - 1 to 10 characters""");
            }
        } while (!validInput);

        return password;
    }





}
