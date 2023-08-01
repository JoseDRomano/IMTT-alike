import model.Vehicle;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
/*
        dataSource.queryInsurances().forEach(System.out::println);
        dataSource.renewInsurance(new Date(2023 - 1900, 7 - 1, 1),
                new Date(2030 - 1900, 5 - 1, 1), 3, "OK Seguros", 123);
        System.out.println("-------------------------------------------");
        dataSource.queryInsurances().forEach(System.out::println);

//        System.out.println("-------------------------------------------");
//        dataSource.queryVehicles().forEach(System.out::println);
       *//* System.out.println(dataSource.getVehicle("AS-8F-BB"));
        System.out.println("-------------------------------------------");
        dataSource.updateVehicleColor( "Purple", "AS-8F-BB");
        System.out.println(dataSource.getVehicle("AS-8F-BB"));*/

////        dataSource.queryVehicles().forEach(System.out::println);
//        dataSource.queryInsurances().forEach(System.out::println);
////        dataSource.deleteVehicle("AS-8F-BB", 6675568);
//        dataSource.deleteInsurance(123);
//        System.out.println("-------------------------------------------");
////        dataSource.queryVehicles().forEach(System.out::println);
//        dataSource.queryInsurances().forEach(System.out::println);

        dataSource.payTicket(1234556778, "13-24-GD", new Date(2023 - 1900, 8 - 1, 22), 240.00);
        dataSource.close();

    }
}
