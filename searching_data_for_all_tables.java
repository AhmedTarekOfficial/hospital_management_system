package connection_data_base;

import java.sql.*; 
public class searching_data_for_all_tables {

database_connection conn = new database_connection();
 String condition ;
 PreparedStatement prd ; 
 ResultSet rst ;


 public searching_data_for_all_tables( String condition ){
    this.condition=condition;

 }




 public String find_data_in_all_tables(){

    conn.connection_data();
    String name = "";
    try{

        prd = conn.con.prepareStatement("select first_name from employees where emp_id =  "+condition+"\n"+
        "union select manager_name from managers where manager_id = "+condition);
        rst = prd.executeQuery();


        while (rst.next()) {

            name = rst.getString(1);

            
            
        }


    }catch(Exception er ){
        System.out.println(er.getMessage());
    }
    return name ;

 }

    
}
