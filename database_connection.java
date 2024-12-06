package connection_data_base;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * connection
 */
public class database_connection {
    
    public  Connection con ;

    public  void connection_data(){
        
        

        try{
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","rootAsdF@#$");
    
            if (con != null){
                
                System.out.println("connection done ");
                System.out.print("\033[H\033[2J");
            }else{
                System.out.println("there is a issue when connection to data base");
            }

            
    
            }catch(Exception er){
                System.out.println(er);
                
            }

        }

    }
            

