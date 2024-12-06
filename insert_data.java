package connection_data_base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class insert_data {

    PreparedStatement PRD;
    ResultSet rst;

    private String table_name;
    private ArrayList<String> table_dataa = new ArrayList<>();

    public insert_data(String table_name, String... table_date) {

        this.table_name = table_name;
        for (String data : table_date) {

            this.table_dataa.add(data);

        }

    }

    public String get_information_value() {

        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 0; i < table_dataa.size(); i++) {

            if (table_dataa.get(i) != null) {

                if (table_dataa.get(i).matches(".*[a-zA-Z].*")) {
                    queryBuilder.append("'" + table_dataa.get(i) + "'");

                } else {
                    queryBuilder.append(table_dataa.get(i));

                }
            } else {
                queryBuilder.append("NULL");
            }

            if (i < table_dataa.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        System.out.println(queryBuilder);
        return queryBuilder.toString();
    }

    public boolean insert_data_to_data_base() {

        database_connection connnection_data = new database_connection();
        connnection_data.connection_data();

        try {
            String sql = "INSERT INTO " + table_name + " VALUES (" + get_information_value() + ")";
            PreparedStatement preparedStatement = connnection_data.con.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("the data has inserted sucssefuly");

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
        return true;

    }

}