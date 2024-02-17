package Airplane;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
////        Connectivity c=new Connectivity();
////        c.createConnection();
////        c.closeConnection();
//        FlightDetails f=new FlightDetails();
//        //f.setDetails();
//        //f.getDetails();
//        //f.searchFlight();
//        f.flightBooking();
//        //Passengers p=new Passengers();
//        //p.getDetails();
//        //f.getLuggageDetails();
////        try{
////            Employee e=new Employee();
////            //e.setEmployee();
////            e.getEmployee();
////        }catch(Exception e)
////        {
////
////        }
        int ch=0;
        do{
            System.out.println("************** FLIGHT BOOKING **************");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.print("Choose Operation:");
            ch=sc.nextInt();

            switch (ch){
                case 1: Admin a=new Admin();
                        a.panel();
                        break;
                case 2: User u=new User();
                        u.userPanel();
                        break;
                case 3: System.exit(0);
                default:
                    System.out.println("!!!! Invalid Option !!!!");
            }

        }while (ch!=3);
    }
}