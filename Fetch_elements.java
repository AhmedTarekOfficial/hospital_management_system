package connection_data_base;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Fetch_elements  {

    database_connection conn = new database_connection();

    PreparedStatement psd;
    ResultSet rst;
    String table_name;

    
    
    ArrayList<String> columns = new ArrayList<>();

    public Fetch_elements(String table_name, String... col) {

        this.table_name = table_name;
        for (String cl : col) {
            this.columns.add(cl);
        }
    }

    public Fetch_elements() {

    }

    

   

    

    ArrayList <String> colum_conditions = new ArrayList<>();

    ArrayList <String> values_conditions = new ArrayList<>() ; 
   
    String table_namee ; 
   
    
   
   
   public void set_values(String...values){
   
       for (String valu : values){
           this.values_conditions.add(valu);
       }
   
   
   
   
   
   
    }
    
    
   
   
    public String get_values(){
       StringJoiner joiner = new StringJoiner(" ");
       StringBuilder bul = new StringBuilder("");
   
       for (String val : values_conditions){
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
   
   
   
   
   public String get_colums_from_user(){
   
       StringJoiner ff = new StringJoiner(" ");
   
       for (String co : colum_conditions){
   
           ff.add(co);
       }
       System.out.println(ff.toString());
       return ff.toString() ;
   
   }




    public String get_specific_data() {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");

        for (int i = 0; i < columns.size(); i++) {
            queryBuilder.append(columns.get(i));
            if (i < columns.size() - 1) {
                queryBuilder.append(", ");
            }

        }

        queryBuilder.append(" from " + table_name);
        return queryBuilder.toString();

    }

    
    // استعلام علي الداتا ولاكن مع تحديد عواميد معينه
    public void fetchElementsFromDatabase() {

        conn.connection_data();
        try {
            psd = conn.con.prepareStatement(get_specific_data());
            rst = psd.executeQuery();

            while (rst.next()) {
                for (String column : columns) {
                    String value = rst.getString(column);

                    System.out.print(value + "\t");
                }

                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
    }


    // تاني استعلام للداتا ولاكن بشرط معين
    public void fetchElementsFromDatabase_with_con() {

        conn.connection_data();
        try {
            psd = conn.con.prepareStatement(get_query_update());
            rst = psd.executeQuery();

            while (rst.next()) {
                for (String column : colum_conditions) {
                    String value = rst.getString(column);

                    System.out.print(value + "\t");
                }

                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    public String get_query_update() {
        StringBuilder builde = new StringBuilder("SELECT ");
    
        for (int i = 0; i < colum_conditions.size(); i++) {
            builde.append(colum_conditions.get(i));
            if (i < colum_conditions.size() - 1) {
                builde.append(", ");
            }
        }
    
        builde.append(" FROM " + table_namee + " WHERE ");
        
        // Assuming colum_conditions and values_conditions have the same size
        for (int i = 0; i < colum_conditions.size(); i++) {
            builde.append(colum_conditions.get(i) + " = ");
            
            String val = values_conditions.get(i);
            if (val.matches(".*[a-zA-Z].*")) {
                builde.append("'" + val + "'");
            } else {
                builde.append(val);
            }
    
            if (i < colum_conditions.size() - 1) {
                builde.append(" AND ");
            }
        }
    
        System.out.println(builde);
        return builde.toString();
    }
    

}
