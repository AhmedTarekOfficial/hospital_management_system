package connection_data_base;

import java.util.ArrayList;
import java.util.StringJoiner;

import java.sql.*;

public class update_elements {

    PreparedStatement pst;
    ResultSet rst;

    private String table_name;
    private String table_conditional;
    private String value_conditional;

    ArrayList<String> updated_data = new ArrayList<>();

    ArrayList<String> update_colums = new ArrayList<>();

    public update_elements(String table_name, String table_condition, String value_conditional,
            String... update_columns) {

        if (update_columns != null) {
            for (String information : update_columns) {
                this.update_colums.add(information);
            }
        }

        this.table_name = table_name;

        this.table_conditional = table_condition;
        this.value_conditional = value_conditional;
    }

    public update_elements() {

    }

    public ArrayList<String> set_value(String... value) {

        for (String in : value) {
            this.updated_data.add(in);

        }

        System.out.println(updated_data);

        return updated_data;
    }

    public String getValuesAsString() {
        StringJoiner joiner = new StringJoiner(" ");
        StringBuilder building = new StringBuilder();
        for (String value : updated_data) {
            if (value != null) {
                if (value.matches(".*[a-zA-Z].*")) {
                    building.append("'" + value + "'");
                } else {
                    return value;
                }
                System.out.println(building);
                joiner.add(value);
            }
        }
        return building.toString();
    }

    public String get_query_update() {
        StringBuilder build = new StringBuilder("update " + table_name + " ");

        build.append("set ");

        for (int i = 0; i < update_colums.size(); i++) {
            build.append(update_colums.get(i)+" ");
            build.append("= "+getValuesAsString()+" ");
            build.append("where "+table_conditional+" ");
            build.append("= "+value_conditional);
            if (i < update_colums.size() - 1) {
                build.append(", ");

            }

           

        }

        
        return build.toString();

    }

    public boolean update_element_in_database() {

        database_connection conn = new database_connection();
        conn.connection_data();

        try {

            pst = conn.con.prepareStatement(get_query_update());
            pst.executeUpdate();

            if (pst != null) {
                System.out.println("done updated the record");
            } else {
                System.out.println("cannot find the record to update it ");
            }

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
        return true;

    }

}
