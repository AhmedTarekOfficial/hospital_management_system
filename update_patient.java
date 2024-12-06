package patient_managment;

import java.util.Scanner;

import connection_data_base.*;

import java.sql.*;

public class update_patient {

    PreparedStatement prd ;
    ResultSet rst ;
    String valu_col = "" ;
    Scanner sc = new Scanner(System.in);

    String id_adress;
    String patient_id;

    public update_patient(String id_Adress, String patient_id) {
        this.id_adress = id_Adress;
        this.patient_id = patient_id;

    }

    public update_patient(){

    }




public void update_employement_information(){

    try{    

        System.out.print("enter the patient id you want to update there employement information : ");
        String patient_id = sc.next();

        fetch_elements_cond find_patient_id = new fetch_elements_cond("patient_information", 
        "Patient_id");
        find_patient_id.set_values(patient_id);

        if(find_patient_id.fetchElementsFromDatabasef().equals(patient_id)){
            System.out.print("enter the employ id of the patient : ");
            String employ_idd = sc.next();

            find_patient_id = new fetch_elements_cond("employment", "employ_id");
            find_patient_id.set_values(employ_idd);

            if (find_patient_id.fetchElementsFromDatabasef().equals(employ_idd)){



                System.out.print("what do you want to update of employment information of the patient ?\n"+
            "1-employment status\n2-employed status\n3-retrid status\n4-Occupation status : ");
    
            String choice = sc.next();

            switch (choice) {
                case "1":
                System.out.print("enter the new  status of employement of employee : ");
                    String new_status = sc.next();
                    update_elements update_employement_status = new update_elements("employment", "employ_id", employ_idd, "employment_status");
                    update_employement_status.set_value(new_status);

                    if (update_employement_status.update_element_in_database()){
                        System.out.println("done update the employment status");
                    }else{
                        System.out.println("cant find the patient please try again");
                    }
                    break;
                    case "2" :
                    System.out.print("enter the new employed status : ");
                    String new_employed_status = sc.next();

                    update_employement_status = new update_elements("employment", "employ_id",
                     employ_idd, "emplyed");
                     update_employement_status.set_value(new_employed_status);
                     if(update_employement_status.update_element_in_database()){
                        System.out.println("done update the employed status");
                     }else{
                        System.out.println("cant find the employee");
                     }

                     break ; 

                     case "3" :

                     System.out.print("enter the new status of retrid of patient : ");
                     String new_retrid = sc.next() ; 
                     update_employement_status = new update_elements("employment", "employ_id", 
                     employ_idd, "retired");
                     update_employement_status.set_value(new_retrid);
                     if (update_employement_status.update_element_in_database()){
                        System.out.println("done update the retrid status");
                     }else{
                        System.out.print("cant find the patient please try again  ");
                     }

                     break ;
                     
                     case "4":

                     System.out.print("enter the new Occupation status of the patient : ");
                     String Occupation = sc.next();

                    update_employement_status  = new update_elements("employment", "employ_id", 
                    employ_idd, "Occupation");
                    update_employement_status.set_value(Occupation);

                    if(update_employement_status.update_element_in_database()){
                        System.out.println("done update the occupation of the patient");
                    }else{
                        System.out.println("cant find the patient ");
                    }

                     break;

                     
            
                default:
                System.out.println("wrong choice please try again and choose the correct choice ");
                    break;
            }

            }


            



        }


       
    }catch(Exception er){
        System.out.println(er.getMessage());
    }

}



public void update_contact_patient_information(){



    try{


    System.out.print("enter the id of patient you want to upadate there information : ");
    String id_patient = sc.next();

    fetch_elements_cond find_patient_id = new fetch_elements_cond("patient_information",
     "Patient_id");
     find_patient_id.set_values(id_patient);

     if(find_patient_id.fetchElementsFromDatabasef().equals(id_patient)){

        System.out.print("what do you want to update in the contact information "+
        "of the patient ?\n1-street adress\n2-city\n3-postal code\n4-state : ");
        int choice_update = sc.nextInt();
        if(choice_update == 1){
            System.out.print("enter the new adress street of the pationt : ");
            String new_Address = sc.next();

            database_connection conn = new database_connection();

            conn.connection_data();

            String sql = "select pb.address_id from patient_information pb , contact_information co "+
            "where(pb.address_id=co.address_id) and Patient_id = "+id_patient;

            prd = conn.con.prepareStatement(sql);
            rst =  prd.executeQuery();

            while (rst.next()) {

                valu_col = rst.getString(1);
                
            }


            update_elements update_contact_information = new 


            update_elements("contact_information", "address_id", valu_col, "street_address");
            update_contact_information.set_value(new_Address);

            if (update_contact_information.update_element_in_database()){
                System.out.println("done update the column");
            }else{
                System.out.println("cant find the patient");
            }

        }else if (choice_update == 2 ){

            System.out.print("please enter the new city of tha patient : ");
            String new_city = sc.next();


            database_connection conn = new database_connection();

            conn.connection_data();

            String sql = "select pb.address_id from patient_information pb , contact_information co "+
            "where(pb.address_id=co.address_id) and Patient_id = "+id_patient;

            prd = conn.con.prepareStatement(sql);
            rst =  prd.executeQuery();

            while (rst.next()) {

                valu_col = rst.getString(1);
                
            }

            
            update_elements update_contact_information = new 


            update_elements("contact_information", "address_id", valu_col, "city");
            update_contact_information.set_value(new_city);

            if (update_contact_information.update_element_in_database()){
                System.out.println("done update the column");
            }else{
                System.out.println("cant find the patient");
            }

        }else if (choice_update == 3){

            System.out.print("enter the new postal code of the patient : ");
            String new_postal_code = sc.next();



            
            database_connection conn = new database_connection();

            conn.connection_data();

            String sql = "select pb.address_id from patient_information pb , contact_information co "+
            "where(pb.address_id=co.address_id) and Patient_id = "+id_patient;

            prd = conn.con.prepareStatement(sql);
            rst =  prd.executeQuery();

            while (rst.next()) {

                valu_col = rst.getString(1);
                
            }


            update_elements update_contact_information = new 


            update_elements("contact_information", "address_id", valu_col, "city");
            update_contact_information.set_value(new_postal_code);

            if (update_contact_information.update_element_in_database()){
                System.out.println("done update the column");
            }else{
                System.out.println("cant find the patient");
            }
        }else if (choice_update == 4){

            System.out.print("enter the new  state code of the patient please : ");
            String new_state_code = sc.next();



            database_connection conn = new database_connection();

            conn.connection_data();

            String sql = "select pb.address_id from patient_information pb , contact_information co "+
            "where(pb.address_id=co.address_id) and Patient_id = "+id_patient;

            prd = conn.con.prepareStatement(sql);
            rst =  prd.executeQuery();

            while (rst.next()) {

                valu_col = rst.getString(1);
                
            }

            update_elements update_contact_information = new 


            update_elements("contact_information", "address_id", valu_col, "city");
            update_contact_information.set_value(new_state_code);

            if (update_contact_information.update_element_in_database()){
                System.out.println("done update the column");
            }else{
                System.out.println("cant find the patient");
            }
        }
        
        



     }



    }catch(Exception er){
        System.out.println(er.getMessage());

    }

}


public void update_patient_table_Adress_column() {

        update_elements update_elements_for_patient = new update_elements("patient_information",
                "Patient_id", patient_id, "address_id");
        update_elements_for_patient.set_value(id_adress);

        if (update_elements_for_patient.update_element_in_database()) {
            System.out.println("the update record has done ");
        } else {
            System.out.println("cannot find thd patient id please try again");
        }
    }

}
