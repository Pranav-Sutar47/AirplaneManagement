package Airplane;

import java.util.Scanner;

public class Luggage extends Passengers{
    Scanner sc;
    void getLuggage(){
        sc=new Scanner(System.in);
        System.out.print("Enter your flight ID:");
        int flightId=sc.nextInt();
        System.out.print("Enter your Passenger ID:");
        int passengerId=sc.nextInt();

        try{
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("SELECT `PassengerName`,`TotalP` FROM `passenger` WHERE `Id`=? AND `flightId`=?");
            c.ps.setInt(1,passengerId);
            c.ps.setInt(2,flightId);
            c.rs=c.ps.executeQuery();
            while (c.rs.next()){
                System.out.println("!!! Each Passenger can carray only 2 bags !!!");
                System.out.println("Total Passengers are:"+c.rs.getInt("TotalP"));
                System.out.println("You can carry Total "+(c.rs.getInt("TotalP")*2));
                byte b=0;
                do{
                    System.out.print("Enter your Total Bags:");
                    int totalB=sc.nextInt();
                    if(totalB<=(c.rs.getInt("TotalP")*2)){
                        b=1;
                        c.ps=c.con.prepareStatement("INSERT INTO `luggage` VALUES (DEFAULT,?,?,?)");
                        c.ps.setInt(1,flightId);
                        c.ps.setInt(2,passengerId);
                        c.ps.setInt(3,totalB);
                        int t=c.ps.executeUpdate();
                        if(t>0){
                            c.ps=c.con.prepareStatement("SELECT `LuggageId` FROM `luggage` WHERE `flightId`=? AND `Id`=?");
                            c.ps.setInt(1,flightId);
                            c.ps.setInt(2,passengerId);
                            c.rs=c.ps.executeQuery();
                            while (c.rs.next()){
                                c.ps=c.con.prepareStatement("UPDATE `passenger` SET `LuggageId`=? WHERE `flightId`=? AND `Id`=?");
                                c.ps.setInt(1,c.rs.getInt("LuggageId"));
                                c.ps.setInt(2,flightId);
                                c.ps.setInt(3,passengerId);
                                int temp=c.ps.executeUpdate();
                                if(temp>0)
                                    System.out.println("Your Luggage Id is:"+c.rs.getInt("LuggageId"));
                                else
                                    System.out.println("Error!!!");
                            }
                        }
                        else
                            System.out.println("Outer Error");
                        c.closeConnection();
                    }
                    else
                        System.out.println("!!! Your bags are more than your Luggage capcity !!!");
                }while (b==0);


            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    void getLuggageDetails(){
        sc=new Scanner(System.in);
        System.out.println("************ LUGGAGE DETAILS ***********");
        System.out.print("Enter Flight ID:");
        int flightId=sc.nextInt();
        int count=0;
        try{
            Connectivity c=new Connectivity();
            c.createConnection();

            c.ps=c.con.prepareStatement("SELECT `Luggage` FROM `luggage` WHERE `flightId`=?");
            c.ps.setInt(1,flightId);
            c.rs=c.ps.executeQuery();

            while (c.rs.next()){
                count+=c.rs.getInt("Luggage");
            }

            if(count>0)
                System.out.println("Total Luggage is:"+count);
            else
                System.out.println("No Luggage found");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
