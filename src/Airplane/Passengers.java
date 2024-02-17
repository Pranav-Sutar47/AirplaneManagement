package Airplane;

import java.sql.Types;
import java.util.Arrays;
import java.util.Scanner;

public class Passengers {
    Scanner sc;
    public String name;
    int id;
    void getDetails(){
        sc=new Scanner(System.in);

        System.out.print("Enter name:");
        name=sc.next();
        System.out.print("Enter Age:");
        int age=sc.nextInt();
        System.out.print("Enter Gender:");
        String gender=sc.next();
        byte bool=0;
        String mn="";
        do{
            System.out.print("Enter Mobile Number:");
            mn=sc.next();
            if(mn.length()!=10)
                bool=0;
            else{
                Boolean lean=mn.matches("\\d+");
                if(lean==true)
                    bool=1;
            }
            if(bool==0)
                System.out.println("Invalid Mobile Number!!!");
        }while(bool==0);

        bool=0;
        String adno="";
        do{
            System.out.print("Enter Adhar Number:");
            adno=sc.next();
            if(adno.length()!=12)
                bool=0;
            else{
                Boolean lean=mn.matches("\\d+");
                if(lean==true)
                    bool=1;
            }
            if(bool==0)
                System.out.println("Invalid Adhar Number!!!");
        }while (bool==0);

        System.out.print("Do you want to check Flight Details:(Y|N)");
        char c=sc.next().charAt(0);
        if(c=='y'||c=='Y'){
            FlightDetails fo=new FlightDetails();
            fo.get();
            System.out.print("Do you want to book ticket ?(Y|N):");
            char s=sc.next().charAt(0);

            if(s=='Y'||s=='y'){


                System.out.print("Enter source city:");
                String source=sc.next();
                System.out.print("Enter destination city:");
                String destination=sc.next();
                try {
                    Connectivity co = new Connectivity();
                    co.createConnection();
                    co.ps = co.con.prepareStatement("SELECT * FROM `flight` WHERE `Source`=? AND `Destination`=?");
                    co.ps.setString(1, source);
                    co.ps.setString(2, destination);
                    co.rs = co.ps.executeQuery();

                    int count=0;
                    int flightId=0;
                    int price = 0,cap=0;
                    String startTime=null,reachTime = null;
                    String booking="";
                    while (co.rs.next()){
                        count++;
                        flightId=co.rs.getInt("flightId");
                        price=co.rs.getInt("Price");
                        startTime=co.rs.getString("StartingTime");
                        reachTime=co.rs.getString("ReachingTime");
                        booking=co.rs.getString("Booking");
                        //System.out.println(booking);
                        //System.out.println(booking.length());
                        cap=co.rs.getInt("Capacity");
                        //System.out.println("Hello");
                    }
                    if(count>0){
                        //System.out.println("Bye");
                        int[] bookArray=new int[cap];
                        if (booking.length()==0) {
                            for(int y=0;y<cap;y++)
                                bookArray[y]=0;
                        }else{
                            String[] book=booking.split(",");
                            for(int i=0;i<book.length;i++)
                                bookArray[i]=Integer.parseInt(book[i].trim());
                            //array sorting
                            for(int i=0;i<bookArray.length;i++){
                                for(int j=1;j<bookArray.length;j++){
                                    if(bookArray[i]>=bookArray[j]){
                                        int temp=bookArray[i];
                                        bookArray[i]=bookArray[j];
                                        bookArray[j]=temp;
                                    }
                                }
                            }
                            //array printing
                            //for(int i=0;i<bookArray.length;i++)
                            //    System.out.println(bookArray[i]+"\t");
                        }

                        //entering more passengers
                        byte b=0;
                        int male = 0,f = 0;
                        int bookSeats=0;
                        //total booked seats
                        for(int j=0;j<bookArray.length;j++){
                            if(bookArray[j]!=0)
                                bookSeats++;
                        }
                        System.out.println("Total availabe seats are:"+(cap-bookSeats));

                        System.out.print("Enter how many passengers including you:");
                        int passenger=sc.nextInt();

                        if(passenger>(cap-bookSeats)){
                            System.out.println("Total seats available are:"+(cap-bookSeats));
                            if((cap-bookSeats)==0)
                                System.out.println("No seats are avaiable !!!");

                            System.out.print("Enter 9 to Exit:");
                            int g=sc.nextInt();
                            if(g==9)
                                System.exit(0);
                            else
                                b=0;
                        }
                        else{
                            do{
                                System.out.print("Enter how many male:");
                                male=sc.nextInt();
                                System.out.print("Enter how many female:");
                                f=sc.nextInt();
                                b=1;
                                if(!(passenger==(male+f))){
                                    b=0;
                                    System.out.println("Enter valid passengers");
                                }
                            }while (b==0);
                        }


                        //non-booked seats printing
                        int m=1;
                        for(int k=0;k<cap/4;k++){
                            for(int l=0;l<4;l++){
                                int t=0;
                                for(int j=0;j<bookArray.length;j++){
                                    //seat is booked
                                    if(m==bookArray[j]){
                                        t=1;
                                        //nonBookSeats++;
                                    }
                                }
                                //for non booked seat
                                if(t==0)
                                    System.out.print(m+"\t");
                                else
                                    System.out.print("\t");//for booked seats
                                m++;
                            }
                            System.out.println();
                        }

                        //System.out.println("Enter "+(male+f)+" seat numbers:");
                        int []a=new int[male+f];

                        for(int i=0;i<(male+f);i++){
                            System.out.print("Enter "+(i+1)+" seat number:");
                            int temp=sc.nextInt();
                            a[i]=temp;
                            bookArray[temp-1]=temp;
                        }

//                       String temp= Arrays.toString(bookArray);
//                       booking=temp.substring(1,temp.length()-1);
                        booking="";
                        for(int i=0;i<bookArray.length;i++){
                            if(bookArray[i]!=0)
                                booking+=bookArray[i]+",";
                        }

                        int p=price*(male+f);
                        System.out.println("Total price is:"+p);

                        System.out.println("Are you sure to book the tickets:(Y|N)");
                        char r=sc.next().charAt(0);
                        if(r=='Y'||r=='y'){
                            co.ps=co.con.prepareStatement("INSERT INTO `passenger` VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            co.ps.setString(2,name);
                            co.ps.setInt(1,flightId);
                            co.ps.setInt(3,age);
                            co.ps.setString(4,gender);
                            co.ps.setString(5,source);
                            co.ps.setString(6,destination);
                            co.ps.setString(7,startTime);
                            co.ps.setString(8,reachTime);
                            co.ps.setInt(9,(male+f));
                            co.ps.setInt(10,p);
                            co.ps.setString(11,mn);
                            co.ps.setString(12,adno);
                            String fbooking="";
                            for(int o=0;o<a.length;o++)
                                fbooking+=a[o]+",";
                            co.ps.setString(13,fbooking);
                            co.ps.setNull(14, Types.INTEGER);
                            int e=co.ps.executeUpdate();
                            if(e>0){
                                co.ps=co.con.prepareStatement("UPDATE `flight` SET `Booking`=? WHERE `flightId`=?");
                                co.ps.setString(1,booking);
                                co.ps.setInt(2,flightId);
                                co.ps.executeUpdate();

                                co.ps=co.con.prepareStatement("SELECT `Id` from `passenger` WHERE `PassengerName`=? AND `MobileNo`=?");
                                co.ps.setString(1,name);
                                co.ps.setString(2,mn);
                                co.rs=co.ps.executeQuery();
                                while(co.rs.next())
                                    id=co.rs.getInt("Id");
                                System.out.println("Your Ticket Booked Successfully !!!!");
                                System.out.println("Thank you for choosing us!!!");
                                System.out.println("Your Passenger Id is:"+id);
                                System.out.println("Your Flight Id is:"+flightId);
                                System.out.println("Your sit numbers are:"+Arrays.toString(a));
                                System.out.println("It is very Important !!!!");

                                b=0;
                                do{
                                    System.out.print("Do you have luggage:(Y|N)");
                                    char ch=sc.next().charAt(0);
                                    if(ch=='y' || ch=='Y'){
                                        b=1;
                                        Luggage l=new Luggage();
                                        l.getLuggage();
                                    }
                                    else if(ch=='n' || ch=='N'){
                                        b=1;
                                        break;
                                    }
                                    else
                                        System.out.println("Please select valid option!!!");
                                }while (b==0);
                            }
                            else
                                System.out.println("Error while entering data!!!!");
                        }
                    }
                    else
                        System.out.println("Sorry no flight scheduled at that route !!!");
                    co.closeConnection();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
        /*
                 */
//        System.out.println("Do you have Luggage:(Y|N)");
//        char ch=sc.next().charAt(0);
//        if(ch=='Y'||ch=='y'){
//
//        }
    }

    void viewBooking(){
        sc=new Scanner(System.in);
        System.out.println("*********** FLIGHT BOOKING ***********");
        System.out.print("Enter Passenger Id:");
        int id=sc.nextInt();
        try{
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("SELECT * FROM `passenger` WHERE `Id`=?");
            c.ps.setInt(1,id);
            byte b=0;
            c.rs=c.ps.executeQuery();
            while (c.rs.next())
                b++;
            if(b>0){
                System.out.println("Id\tFlightId\tName\tAge\tGender\tSource\tDestination\tStart Time\t\t\tReach Time\t\t\tTotal Passenger\t\tPrice\t\tMobile No\t\tAdhar No\t\tSeat No\t\tLuggage Id");
                c.rs=c.ps.executeQuery();
                while (c.rs.next())
                    System.out.println(c.rs.getInt("Id")+"\t"+c.rs.getInt("flightId")+"\t\t\t"+c.rs.getString("PassengerName")+"\t"+c.rs.getInt("Age")+"\t"+c.rs.getString("Gender")+"\t"+c.rs.getString("Source")+"\t"+c.rs.getString("Destination")+"\t"+c.rs.getString("StartTime")+"\t"+c.rs.getString("ReachTime")+"\t"+c.rs.getInt("TotalP")+"\t\t\t"+c.rs.getInt("Price")+"\t"+c.rs.getString("MobileNo")+"\t"+c.rs.getString("AdharNo")+"\t"+c.rs.getString("SeatNos")+"\t"+c.rs.getInt("LuggageId"));
            }else
                System.out.println("No booking found !!!!");



        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
