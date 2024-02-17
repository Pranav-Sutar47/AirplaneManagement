package Airplane;

import java.util.Scanner;

public class User {
    void userPanel(){
        Scanner sc=new Scanner(System.in);
        int ch=0;

        do{
            System.out.println("*********** USER PANEL ***********");
            System.out.println("1.Book Flight");
            System.out.println("2.Search Flight");
            System.out.println("3.View Flight");
            System.out.println("4.View Booking");
            System.out.println("5.Exit");
            System.out.print("Choose Option:");
            ch=sc.nextInt();
            Passengers p=new Passengers();
            FlightDetails f=new FlightDetails();
            switch (ch){
                case 1: p.getDetails();
                        break;
                case 2: f.searchFlight();
                        break;
                case 3: f.get();
                        break;
                case 4: p.viewBooking();
                        break;
                case 5: break;
                default:
                    System.out.println("!!! Invalid option !!!");
            }
        }while (ch!=5);

    }
}
