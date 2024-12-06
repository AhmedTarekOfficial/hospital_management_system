package patient_managment;

import java.util.Scanner;
import java.sql.*;

import connection_data_base.database_connection;
import connection_data_base.delete_element;
import connection_data_base.fetch_elements_cond;
public class delete_patient {

    String get_patint = "";
    String get_id = "";

    PreparedStatement prd1;
    PreparedStatement prd;
    ResultSet rst;
    ResultSet rst1;
    

    Scanner sc = new Scanner(System.in);

    public delete_patient() {

    }

    public boolean delete_infor_patien() {

        database_connection conn = new database_connection();
        conn.connection_data();

        try {

            System.out.print("enter the id of the patient you want to delete thier information from system : ");
            String id_patient = sc.next();

            fetch_elements_cond find_patient = new fetch_elements_cond("patient_information",
                    "Patient_id");
            find_patient.set_values(id_patient);

            if (find_patient.fetchElementsFromDatabasef().equals(id_patient)) {

                String sql = "select pb.Patient_id , pb.address_id from " +
                        "patient_information pb , contact_information co , employment emp " +
                        "where(pb.address_id = co.address_id ) and (pb.Patient_id = emp.Patient_id) and pb.Patient_id = "
                        + id_patient;

                prd = conn.con.prepareStatement(sql);
                rst = prd.executeQuery();

                while (rst.next()) {

                    get_patint = rst.getString(1);
                    get_id = rst.getString(2);

                }

                String drop_FH_patient_id = "alter table employment drop constraint Fh_em  ";
                prd1 = conn.con.prepareStatement(drop_FH_patient_id);

                
                String searching_constraint_name_employment_first_before_delete_rows 
                = " SELECT constraint_name " +
                "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                "where constraint_schema = database()" +
                "and TABLE_NAME  = 'employment' " +
                "and constraint_name = 'Fh_em' ";

                String searching_constraint_name_patient_first_before_delete_rows = " SELECT constraint_name " +
                "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                "where constraint_schema = database()" +
                "and TABLE_NAME  = 'patient_information' " +
                "and constraint_name = 'Fh_me' ";

                prd = conn.con.prepareStatement(searching_constraint_name_patient_first_before_delete_rows);
                rst1 = prd.executeQuery();




                prd1 = conn.con.prepareStatement(searching_constraint_name_employment_first_before_delete_rows);
                rst = prd1.executeQuery();

                        String drop_Fh_adress_id = "alter table patient_information drop constraint Fh_me  ";
                        prd = conn.con.prepareStatement(drop_Fh_adress_id);

                if(rst.next() && rst1.next()){

                    prd1.executeUpdate();
                   delete_element del = new delete_element("employment", "employ_id", id_patient);
                            del.delete_elements_from_data_base();

                    



                }













                try {
                    if (prd1.executeUpdate() > 0) {
                        
                        if (prd.executeUpdate() > 0) {
                            delete_element del = new delete_element("contact_information", "address_id", get_id);
                            del.delete_elements_from_data_base();
                            del = new delete_element("patient_information", "Patient_id", id_patient);
                            del.delete_elements_from_data_base();
                            del = new delete_element("employment", "employ_id", id_patient);
                            del.delete_elements_from_data_base();
                            System.out.println("delete information succesful");
                        }
                    }

                } catch (Exception er) {

                    

                    String constrain_FH_patient_id = "ALTER TABLE employment\n" + 
                                                "ADD CONSTRAINT Fh_em\n" + 
                                                " FOREIGN KEY (patient_id) REFERENCES patient_information(patient_id);";

                    String constrain_Fh_adress_id = "alter table patient_information add constraint Fh_me foreign key(address_id)"
                            +
                            " references contact_information(address_id)";

                    String searching_constraint_name_employment = " SELECT constraint_name " +
                            "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                            "where constraint_schema = database()" +
                            "and TABLE_NAME  = 'employment' " +
                            "and constraint_name = 'Fh_em' ";

                    String searching_constraint_name_patient = " SELECT constraint_name " +
                            "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                            "where constraint_schema = database()" +
                            "and TABLE_NAME  = 'patient_information' " +
                            "and constraint_name = 'Fh_me' ";

                    prd1 = conn.con.prepareStatement(searching_constraint_name_employment);
                    rst = prd1.executeQuery();

                    if (rst.next()) {
                        System.out.println("THE CONSTRAINT HAS ALREADY existing");
                        return false;
                        

                    } else {
                        prd1 = conn.con.prepareStatement(constrain_FH_patient_id);
                        prd1.executeUpdate();

                    }

                    prd = conn.con.prepareStatement(searching_constraint_name_patient);
                    rst = prd.executeQuery();

                    if (rst.next()) {
                        System.out.println("THE CONSTRAINT HAS ALREADY existing");
                        return false;

                    } else {
                        prd = conn.con.prepareStatement(constrain_Fh_adress_id);
                        prd.executeUpdate();

                    }

                    System.out.println(er.getMessage());

                    
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
