package connection_data_base;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.sql.*;


public class fetch_elements_cond {
    

    database_connection con = new database_connection();    

    
    PreparedStatement prd;
     ResultSet rst ;

    ArrayList<String> colum_conditions = new ArrayList<>();

    ArrayList<String> values_conditions = new ArrayList<>();

    String table_name;

    public fetch_elements_cond(String table_name,String...columnsss) {
        
        this.table_name = table_name;
        for (String col : columnsss) {
            this.colum_conditions.add(col);
        }
    }

    
    
    public void set_values(String... values) {

        if (values.length != colum_conditions.size()) {
            throw new IllegalArgumentException("Number of values doesn't match number of columns.");
        }

        for (String valu : values) {
            this.values_conditions.add(valu);
        }

    }

    public String get_values() {
        StringJoiner joiner = new StringJoiner(" ");
        StringBuilder bul = new StringBuilder("");

        for (String val : values_conditions) {
            if (val.matches(".*[a-zA-Z].*")) {
                bul.append("'" + val + "'");
            } else {
                bul.append(val);

            }
            joiner.add(val);

        }
        System.out.println(bul);
        return bul.toString();
    }

    public String get_colums_from_user() {

        StringJoiner ff = new StringJoiner(" ");

        for (String co : colum_conditions) {

            ff.add(co);
        }
        System.out.println(ff.toString());
        return ff.toString();

    }

    public String get_query_update() {
        StringBuilder builder = new StringBuilder("SELECT ");
    
        for (int i = 0; i < colum_conditions.size(); i++) {
            builder.append(colum_conditions.get(i));
            if (i < colum_conditions.size() - 1) {
                builder.append(", ");
            }
        }
    
        builder.append(" FROM ").append(table_name).append(" WHERE ");
        
        // Assuming colum_conditions and values_conditions have the same size
        for (int i = 0; i < colum_conditions.size(); i++) {
            builder.append(colum_conditions.get(i)).append(" = ");
            
            String val = values_conditions.get(i);
            if (val.matches(".*[a-zA-Z].*")) {
                builder.append("'").append(val).append("'");
            } else {
                builder.append(val);
            }
    
            if (i < colum_conditions.size() - 1) {
                builder.append(" AND ");
            }
        }
    
    
    
       
    return builder.toString();

}


    public String fetchElementsFromDatabasef() {

        con.connection_data();
    
        String value = "";
        try {
            
       
      
    
            prd = con.con.prepareStatement(get_query_update());
            rst = prd.executeQuery();
    
            while (rst.next()) {
                for (String column : colum_conditions) {
                     value = rst.getString(column);  
       

        
            }
        }
        
    
        }catch(Exception er) {
            System.out.println(er);
        }
        
        return  value; 
    }



    


}

    
           
    





    
       
            

    
        
    

            

        















