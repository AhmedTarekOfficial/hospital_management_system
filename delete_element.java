package connection_data_base;
import java.sql.*;;

public class delete_element {

    PreparedStatement prd ; 
    ResultSet rst;


private String table_name ; 
private String conditional_table ;
private String value_conditional ; 


public delete_element(String table_name , String coditional_table, String value_conditionall){

this.table_name = table_name;
this.conditional_table=coditional_table;
this.value_conditional=value_conditionall;



}


public StringBuilder Check_the_chrachter(){

    StringBuilder buildd = new StringBuilder();
             
    if (value_conditional.matches(".*[a-zA-Z].*")) {
        buildd.append("'" + value_conditional + "'");
    } else {
        buildd.append(value_conditional);
    }

    System.out.println(buildd);

return buildd;

}




public boolean delete_elements_from_data_base(){

database_connection conn = new database_connection();
conn.connection_data();




    try{

        prd = conn.con.prepareStatement("delete from "+table_name +" " +"where "+conditional_table + "= "+Check_the_chrachter());     
         
        System.out.println(prd);
        int row_affected = prd.executeUpdate() ;

        if (row_affected > 0){
            System.out.println("Done. Data deleted successfully.");
        }else{
            System.out.println("Failed to delete data. No matching records found.");
}
        

    }catch(Exception er){
        System.out.println(er.getMessage());
    }

    return true ;


}

    
}
