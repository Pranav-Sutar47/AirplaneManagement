package Airplane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Employee{
    Scanner sc;

    void setEmployee()throws IOException {
        sc=new Scanner(System.in);
        System.out.println("**************** EMPLOYEE DETAILS ****************");
        System.out.print("Enter Employee Name:");
        String name=sc.next();
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Designation:");
        String deg=bf.readLine();
        System.out.print("Enter Department:");
        String dep=bf.readLine();
        System.out.print("Enter Salary:");
        float salary=sc.nextFloat();
        System.out.print("Enter your address:");
        String address=bf.readLine();
        byte b=0;
        int age=0;
        do{
            System.out.print("Enter your age:");
            age=sc.nextInt();
            if(age<70 && age>=20)
                b=1;
            else{
                System.out.println("Age must be greater than 20 and less than 70");
            }
        }while (b==0);
        b=0;
        String mn="";
        do{
            System.out.print("Enter your Mobile Number:");
            mn=sc.next();
            if(mn.length()!=10)
                b=0;
            else{
                boolean bool=mn.matches("\\d+");
                if(bool ==true)
                    b=1;
            }
        }while (b==0);
        try{
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("INSERT INTO `employee` VALUES(?,?,?,?,?,?,?,DEFAULT)");
            c.ps.setString(1,name);
            c.ps.setString(2,deg);
            c.ps.setString(3,dep);
            c.ps.setFloat(4,salary);
            c.ps.setString(5,address);
            c.ps.setInt(6,age);
            c.ps.setString(7,mn);
            int temp=c.ps.executeUpdate();
            if(temp>0){
                c.ps=c.con.prepareStatement("SELECT `Id` from `employee` WHERE `MobileNo`=?");
                c.ps.setString(1,mn);
                c.rs=c.ps.executeQuery();
                while (c.rs.next())
                    System.out.println("Your Employee Id is:"+c.rs.getInt("Id"));
            }else{
                System.out.println("Error !!!");
            }
            c.closeConnection();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    void getEmployee(){
        sc=new Scanner(System.in);
        int ch=0;
        do{
            System.out.println("********* EMPLOYEE DETAILS *********");
            System.out.println("1.All Employee Details");
            System.out.println("2.Airplane Employee Details");
            System.out.println("3.Airport Employee Details");
            System.out.println("4.Specific Employee Details");
            System.out.println("5.Exit");
            System.out.print("Choose any one option:");
            ch=sc.nextInt();

            try{
                Connectivity c=new Connectivity();
                c.createConnection();
                switch (ch){
                    case 1: c.ps=c.con.prepareStatement("SELECT * FROM `employee`");
                        c.rs=c.ps.executeQuery();
                        System.out.println("Id\tName\tAge\tMobile Number\tDesignation\t\tDepartment\t\tSalary\t\tAddress");
                        while (c.rs.next()){
                            System.out.println(c.rs.getInt("Id")+"\t"+c.rs.getString("Name")+"\t"+c.rs.getInt("Age")+"\t"+c.rs.getString("MobileNo")+"\t\t"+c.rs.getString("Designation")+"\t\t\t"+c.rs.getString("Department")+"\t\t"+c.rs.getFloat("Salary")+"\t"+c.rs.getString("Address"));
                        }
                        break;

                    case 2: c.ps=c.con.prepareStatement("SELECT * FROM `employee` WHERE `Department`='Airplane'");
                        c.rs=c.ps.executeQuery();
                        System.out.println("Id\tName\tAge\tMobile Number\tDesignation\t\tDepartment\t\tSalary\t\tAddress");
                        while (c.rs.next()){
                            System.out.println(c.rs.getInt("Id")+"\t"+c.rs.getString("Name")+"\t"+c.rs.getInt("Age")+"\t"+c.rs.getString("MobileNo")+"\t\t"+c.rs.getString("Designation")+"\t\t\t"+c.rs.getString("Department")+"\t\t"+c.rs.getFloat("Salary")+"\t"+c.rs.getString("Address"));
                        }
                        break;
                    case 3: c.ps=c.con.prepareStatement("SELECT * FROM `employee` WHERE `Department`='Airport'");
                        c.rs=c.ps.executeQuery();
                        System.out.println("Id\tName\tAge\tMobile Number\tDesignation\t\tDepartment\t\tSalary\t\tAddress");
                        while (c.rs.next()){
                            System.out.println(c.rs.getInt("Id")+"\t"+c.rs.getString("Name")+"\t"+c.rs.getInt("Age")+"\t"+c.rs.getString("MobileNo")+"\t\t"+c.rs.getString("Designation")+"\t\t\t"+c.rs.getString("Department")+"\t\t"+c.rs.getFloat("Salary")+"\t"+c.rs.getString("Address"));
                        }
                        break;
                    case 4: System.out.print("Enter Employee Id:");
                        int id=sc.nextInt();
                        c.ps=c.con.prepareStatement("SELECT * FROM `employee` WHERE Id=?");
                        c.ps.setInt(1,id);
                        c.rs=c.ps.executeQuery();
                        System.out.println("Id\tName\tAge\tMobile Number\tDesignation\t\tDepartment\t\tSalary\t\tAddress");
                        while (c.rs.next()){
                            System.out.println(c.rs.getInt("Id")+"\t"+c.rs.getString("Name")+"\t"+c.rs.getInt("Age")+"\t"+c.rs.getString("MobileNo")+"\t\t"+c.rs.getString("Designation")+"\t\t\t"+c.rs.getString("Department")+"\t\t"+c.rs.getFloat("Salary")+"\t"+c.rs.getString("Address"));
                        }
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
                c.closeConnection();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }while (ch!=5);





    }
}
