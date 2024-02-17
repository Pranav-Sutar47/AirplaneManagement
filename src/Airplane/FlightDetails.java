package Airplane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;

public class FlightDetails {
    Scanner sc;
    void setDetails(){
        int id;
        sc=new Scanner(System.in);
        System.out.println("****** FLIGHT DETAILS ******");
        System.out.print("Enter Flight ID:");
        id=sc.nextInt();
        System.out.print("Enter Flight Name:");
        String nm=sc.next();
        System.out.print("Enter Capacity of Flight:");
        int capacity=sc.nextInt();
        System.out.print("Enter Source City:");
        String startCity=sc.next();
        System.out.print("Enter Destination City:");
        String destinationCity=sc.next();
        System.out.print("Enter Price:");
        int price=sc.nextInt();
        byte flag=0;
        int m,d;
        do{
            System.out.print("Enter Starting Date (Month):");
            m=sc.nextInt();
            if(m==0||m>12)
                flag=0;
            else{
                LocalDate date=LocalDate.now();
                int t=date.getMonthValue();
                if(m<t)
                    flag=0;
                else
                    flag=1;
            }
            if(flag==0)
                System.out.println("Enter valid Month!!");
        }while (flag==0);
        flag=0;
        do{
            System.out.print("Enter Starting Date (Date):");
            d=sc.nextInt();
            if(d==0||d>31)
                flag=0;
            else{
                LocalDate date=LocalDate.now();
                int t=date.getDayOfMonth();
                if(d<t)
                    flag=0;
                else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12) {
                    if (d <= 31)
                        flag = 1;
                }
                else if(m==2 && d<30)
                    flag=1;
                else{
                    if(d<30)
                        flag=1;
                }
            }
            if(flag==0)
                System.out.println("Enter Valid Day !!!");
        }while (flag==0);
        LocalDate date=LocalDate.of(2024,m,d);
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startDate=date.format(df);
        startDate+=" ";
        System.out.print("Enter Starting Time:(HH:mm:ss)");
        startDate+=sc.next();
        /*
        String temp=sc.next();
        System.out.println(temp);
        int t=temp.indexOf(',');
        int y=Integer.parseInt(temp.substring(0,t));
        temp=temp.substring(0,t);
        System.out.println(temp);
        t=temp.indexOf(',');
        int m=Integer.parseInt(temp.substring(0,t));
        temp=temp.substring(0,t);
        System.out.println(temp);
        int d=Integer.parseInt(temp);
        //String d=sc.next();
        Date date=new Date(y,m,d);
        String startDate=date.toString();
        */
        String reachDate="";
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Reaching Date (yyyy-MM-DD) and Time (HH,MM,SS):");
            reachDate=bf.readLine();
        }catch(Exception e){

        }

        //System.out.print("Enter booking numbers (Seprate each number with ,):");
        //String booking=sc.next();

        Connectivity c=new Connectivity();
        try{
            c.createConnection();
            c.ps=c.con.prepareStatement("INSERT INTO `flight` VALUES(?,?,?,?,?,?,?,?,?)");
            c.ps.setInt(1,id);
            c.ps.setString(2,nm);
            c.ps.setInt(3,capacity);
            c.ps.setString(4,startDate);
            c.ps.setString(5,reachDate);
            c.ps.setString(6,startCity);
            c.ps.setString(7,destinationCity);
            c.ps.setInt(8,price);
            c.ps.setString(9,"");

            System.out.print("Are you sure to Enter Details (Y,N):");
            char ch=sc.next().charAt(0);
            int i=0;
            if(ch=='Y' || ch=='y')
                i=c.ps.executeUpdate();
            else
                System.out.println("Data not inserted !!!");
            if(i!=0)
                System.out.println("Data Inserted Successfully !!!");
            c.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
            c.closeConnection();
        }
    }

    void getDetails(){
        sc=new Scanner(System.in);
        System.out.print("Enter filght Id:");
        int id=sc.nextInt();
        try {
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("SELECT * FROM `flight` WHERE `flightId`=?");
            c.ps.setInt(1,id);
            c.rs=c.ps.executeQuery();
            System.out.println("********** Flight Details **********");
            System.out.println("ID\tName\tCapacity\tStarting Time\tReaching Time\t\tSource City\t\tDestination City\tPrice\tBookings");
            while (c.rs.next())
                System.out.println(c.rs.getInt(1)+"\t"+c.rs.getString(2)+"\t\t"+c.rs.getInt(3)+"\t"+c.rs.getString(4)+"\t"+c.rs.getString(5)+"\t"+c.rs.getString(6)+"\t\t"+c.rs.getString(7)+"\t\t"+c.rs.getInt(8)+"\t"+c.rs.getString(9));
            c.closeConnection();
        }catch (Exception e){
            e.getMessage();
        }

    }

    void get(){
        try {
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("SELECT * FROM `flight`");
            c.rs=c.ps.executeQuery();
            System.out.println("***** Flight Details *****");
            System.out.println("ID\tName\tCapacity\tStarting Time\tReaching Time\t\tSource City\t\tDestination City\tPrice\tBookings");
            while (c.rs.next())
                System.out.println(c.rs.getInt(1)+"\t"+c.rs.getString(2)+"\t\t"+c.rs.getInt(3)+"\t"+c.rs.getString(4)+"\t"+c.rs.getString(5)+"\t"+c.rs.getString(6)+"\t\t"+c.rs.getString(7)+"\t\t"+c.rs.getInt(8)+"\t"+c.rs.getString(9));
            c.closeConnection();
        }catch (Exception e){

        }
    }

    void searchFlight(){
        sc=new Scanner(System.in);
        int ch;
        do{
            System.out.println("************ FLIGHT DETAILS ************");
            System.out.println("1.Search Flights From Source To Destination");
            System.out.println("2.Search Flights With Name");
            System.out.println("3.Search Flights With Id");
            System.out.println("4.Exit");
            System.out.print("Choose option:");
            ch=sc.nextInt();
            Connectivity c=new Connectivity();
            c.createConnection();
            try{
                switch (ch){
                    case 1: System.out.print("Enter Source city:");
                            String source=sc.next();
                            System.out.print("Enter Destination city:");
                            String destination=sc.next();
                            c.ps=c.con.prepareStatement("SELECT * FROM `flight` WHERE `Source`=? AND `Destination`=?");
                            c.ps.setString(1,source);
                            c.ps.setString(2,destination);
                            c.rs=c.ps.executeQuery();
                            byte b=0;
                            while (c.rs.next()){
                                b++;
                            }
                            System.out.println(b);
                            if(b>0){
                                System.out.println("ID\tName\tCapacity\tStarting Time\tReaching Time\t\tSource City\t\tDestination City\tPrice\tBookings");
                                c.rs=c.ps.executeQuery();
                                while (c.rs.next()){
                                    System.out.println(c.rs.getInt(1)+"\t"+c.rs.getString(2)+"\t\t"+c.rs.getInt(3)+"\t"+c.rs.getString(4)+"\t"+c.rs.getString(5)+"\t"+c.rs.getString(6)+"\t\t"+c.rs.getString(7)+"\t\t"+c.rs.getInt(8)+"\t"+c.rs.getString(9));
                                }
                            }else
                                System.out.println("!!!! No Flight Found !!!!");
                            break;
                    case 2: System.out.print("Enter Flight Name:");
                            String name=sc.next();
                            c.ps=c.con.prepareStatement("SELECT * FROM `flight` WHERE `flightName`=?");
                            c.ps.setString(1,name);
                            c.rs=c.ps.executeQuery();
                            b=0;
                            while (c.rs.next()){
                                b++;
                            }

                            if(b>0){
                                c.rs=c.ps.executeQuery();
                                System.out.println("ID\tName\tCapacity\tStarting Time\tReaching Time\t\tSource City\t\tDestination City\tPrice\tBookings");
                                while (c.rs.next()){
                                    System.out.println(c.rs.getInt(1)+"\t"+c.rs.getString(2)+"\t\t"+c.rs.getInt(3)+"\t"+c.rs.getString(4)+"\t"+c.rs.getString(5)+"\t"+c.rs.getString(6)+"\t\t"+c.rs.getString(7)+"\t\t"+c.rs.getInt(8)+"\t"+c.rs.getString(9));
                                }
                            }else
                                System.out.println("!!! No Flight Found !!!");
                            break;
                    case 3: System.out.print("Enter Flight Id:");
                            int id=sc.nextInt();
                            c.ps=c.con.prepareStatement("SELECT * FROM `flight` WHERE `flightId`=?");
                            c.ps.setInt(1,id);
                            c.rs=c.ps.executeQuery();
                            b=0;
                            while (c.rs.next()){
                                b++;
                            }
                            if(b>0){
                                c.rs=c.ps.executeQuery();
                                System.out.println("ID\tName\tCapacity\tStarting Time\tReaching Time\t\tSource City\t\tDestination City\tPrice\tBookings");
                                while (c.rs.next()){
                                    System.out.println(c.rs.getInt(1)+"\t"+c.rs.getString(2)+"\t\t"+c.rs.getInt(3)+"\t"+c.rs.getString(4)+"\t"+c.rs.getString(5)+"\t"+c.rs.getString(6)+"\t\t"+c.rs.getString(7)+"\t\t"+c.rs.getInt(8)+"\t"+c.rs.getString(9));
                                }
                            }else
                                System.out.println("!!! No Flight Found !!!");
                            break;
                    case 4: break;
                    default:
                        System.out.println("Invalid Choice !!!!");
                }
                c.closeConnection();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }while(ch!=4);
    }

    void flightBooking(){
        sc=new Scanner(System.in);
        System.out.print("Enter flight id:");
        int id=sc.nextInt();
        try{
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("SELECT `Booking`,`Capacity` FROM `flight` WHERE `flightId`=?");
            c.ps.setInt(1,id);
            byte b=0;
            c.rs=c.ps.executeQuery();
            while (c.rs.next())
                b++;
            if(b>0){
                c.rs=c.ps.executeQuery();
                String s="";
                int cap=0;
                while (c.rs.next()){
                    s=c.rs.getString("Booking");
                    cap=c.rs.getInt("Capacity");
                }
                int book[]=new int[cap];
                if(s.length()>0){
                    String bo[]=s.split(",");
                    for(int i=0;i<bo.length;i++)
                        book[i]=Integer.parseInt(bo[i].trim());
                    //Array Sorting
                    for(int i=0;i<bo.length;i++){
                        for(int j=i+1;j<bo.length-1;j++){
                            if(book[i]>book[j]){
                                int temp=book[i];
                                book[i]=book[j];
                                book[j]=temp;
                            }
                        }
                    }
                }
                int m=1,bookSeats=0;
                System.out.println("Non Booked Seats are as follws:");
                System.out.println("**************** SEAT MATRIX ****************");
                for(int k=0;k<cap/4;k++){
                    for(int l=0;l<4;l++){
                        int t=0;
                        for(int j=0;j<book.length;j++){
                            //seat is booked
                            if(m==book[j]){
                                t=1;
                                bookSeats++;
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
                System.out.println("***********************************************");
                System.out.println("Total Booked Seats are:"+bookSeats);
                System.out.println("Unbooked Seats are:"+(cap-bookSeats));

            }else
                System.out.println("!!! Flight Not Found !!!");
            c.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

/*
INSERT INTO `flight` (`flightId`, `flightName`, `Capacity`, `StartingTime`, `ReachingTime`, `Source`, `Destination`, `Price`, `Booking`) VALUES
('1', 'Express', '40', '2024-01-16 16:05:55', '2024-01-17 16:05:55', 'Kolhapur', 'Pune', '500', '');
 */