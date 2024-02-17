package Airplane;

import java.sql.*;

public class Connectivity {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane","root","");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    void closeConnection(){
        try{
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
