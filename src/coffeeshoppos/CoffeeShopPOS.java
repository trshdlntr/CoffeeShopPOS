package coffeeshoppos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trish
 */
public class CoffeeShopPOS {

    public static void main(String[] args) {
        
        String url="jdbc:mysql://localhost:3306/";
        String username="Trisha Delantar";
        String password="@Lalay22011139*";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url, username, password);
            
            System.out.println(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoffeeShopPOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CoffeeShopPOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
