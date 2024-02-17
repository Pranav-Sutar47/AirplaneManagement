package Airplane;

import java.io.IOException;
import java.util.Scanner;

public class Admin {
    void panel()throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("*********** ADMIN LOGIN ***********");
        System.out.print("Enter Username:");
        String uname=sc.next();
        System.out.print("Enter Password:");
        String psw=sc.next();
        if(uname.equals("Admin") && psw.equals("Admin")){
            int ch;
            do{
                System.out.println("*********** ADMIN LOGIN ***********");
                System.out.println("1.Add Flights");
                System.out.println("2.View All Flights");
                System.out.println("3.Search Flights");
                System.out.println("4.View Flight");
                System.out.println("5.View Flight Booking");
                System.out.println("6.Total Luggage");
                System.out.println("7.Add Employee");
                System.out.println("8.View Employee");
                System.out.println("9.Exit");
                System.out.print("Choose option:");
                ch=sc.nextInt();
                FlightDetails f=new FlightDetails();
                Employee e=new Employee();
                Luggage l=new Luggage();
                switch (ch){
                    case 1: f.setDetails();
                            break;
                    case 2: f.get();
                            break;
                    case 3: f.searchFlight();
                            break;
                    case 4: f.getDetails();
                            break;
                    case 5: f.flightBooking();
                            break;
                    case 6: l.getLuggageDetails();
                            break;
                    case 7: e.setEmployee();
                            break;
                    case 8: e.getEmployee();
                            break;
                    case 9: break;
                    default: System.out.println("Invalid Choice !!!");
                }
            }while (ch!=9);
        }else
            System.out.println("!!! Invalid Credintial !!!");
    }
}
