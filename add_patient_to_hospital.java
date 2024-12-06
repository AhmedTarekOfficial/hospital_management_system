package patient_managment;

import java.util.Scanner;

import connection_data_base.fetch_elements_cond;
import connection_data_base.insert_data;

public class add_patient_to_hospital {

   public String id_adress ;
   public String id_patient ;
    String gender_patient;
    String patient_marital_status;




    public void add_aptient_to_system() {

        try (Scanner sc = new Scanner(System.in)) {
            try {

                System.out.println("what you want to add ? ");
                System.out.println("-------------------------------");
                System.out.println("1-Add patient information\n2-add contact patient information" +
                        "\n3-Add employment information ");

                int choice_add = sc.nextInt();

                if (choice_add == 1) {

                    System.out.print("please enter the  patient id : ");
                     id_patient = sc.next();

                    
                    System.out.print("\033[H\033[2J");
                    System.out.print("please enter the first_name of the patient : ");
                    String first_name_patient = sc.next();
                    System.out.print("\033[H\033[2J");
                    System.out.print("please enter the last_name of the patient : ");
                    String last_name_patient = sc.next();
                    System.out.print("\033[H\033[2J");
                    System.out.print("please select the gender of  the patient (1-Male,2-Female) : ");
                    String gender = sc.next();
                    if (gender == "1") {

                        gender_patient = "Male";

                    } else {
                        gender_patient = "Female";
                    }

                    System.out.print("\033[H\033[2J");
                    System.out.print("please select the patient marital status\n1-Single\n2-Married" +
                            "\n3-widowed\n4-Divorced\n5-Separated : ");
                    int marital_status_choice = sc.nextInt();

                    switch (marital_status_choice) {
                        case 1:

                            patient_marital_status = "Single";

                            break;

                        case 2:
                            patient_marital_status = "Married";

                            break;
                        case 3:
                            patient_marital_status = "widowed";
                            break;
                        case 4:
                            patient_marital_status = "Divorced";
                            break;
                        case 5:
                            patient_marital_status = "Separated";

                            break;

                        default:

                            System.out.println("please  focus while you choose your marital status ");
                            break;
                    }

                    insert_data insert_information_patinent = new insert_data("patient_information",
                            id_patient, first_name_patient, last_name_patient, gender_patient, patient_marital_status,
                            null);

                    if (insert_information_patinent.insert_data_to_data_base()) {
                        Thread.sleep(2500);
                        System.out.println("done adding data of the patient to system");
                    } else {
                        System.out.println("please try again adding there is somthing happen");
                    }

                }else if (choice_add == 2 ){


                    System.out.print("enter the patient id you want to enter contact_information : ");
                     id_patient = sc.next();
                    

                    fetch_elements_cond find_id_of_patient = new fetch_elements_cond("patient_information",
                     "Patient_id");

                     find_id_of_patient.set_values(id_patient);

                     if (find_id_of_patient.fetchElementsFromDatabasef().equals(id_patient)){
            
                        
                        System.out.print("please enter the address id : ");
                         id_adress = sc.next();
                         
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the street address of the patient : ");
                        sc.nextLine();
                        String street = sc.nextLine();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the city of the patient : ");
                        String city = sc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the postal code of the patient : ");
                        String postal_code = sc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the state of the patient : ");
                        String state = sc.next();


                        insert_data insert_contact_information = new insert_data("contact_information", 
                        id_adress,street,city,postal_code,state);

                        insert_contact_information.insert_data_to_data_base();
                        update_patient up = new update_patient(id_adress, id_patient);
                        up.update_patient_table_Adress_column();

                        Thread.sleep(2500);
                        System.out.println("done inserting the information");
                        

                     }else{
                        System.out.println("cant find the id of the patient ");
                     }
                }else if (choice_add == 3 ){

                    System.out.print("enter the id of the pationt you want to add"+
                    "thier emplyment information : ");
                    String patient_id = sc.next();

                    fetch_elements_cond find_patient = new fetch_elements_cond("patient_information", "Patient_id");
                    find_patient.set_values(patient_id);

                    if (find_patient.fetchElementsFromDatabasef().equals(patient_id)){

                        System.out.print("enter the employment id : ");
                        String employ_id = sc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("please enter the patient employement_status : ");
                        String employement_status = sc.next();
                        if (employement_status.equals("unemp")) {
                            
                            insert_data ins = new insert_data("employment", employ_id,employement_status,"na","na","na",patient_id);
                            System.out.print("\033[H\033[2J");
                            ins.insert_data_to_data_base();

                        }else{

                            System.out.print("enter the emplOyed STATUS  : ");
                            String employes = sc.next();

                            System.out.print("\033[H\033[2J");
                            System.out.println("enter the retired  status : ");

                            String  retired_status = sc.next();
                            System.out.print("\033[H\033[2J");
                            System.out.println("enter the Occupation status :  ");
                            String Occupation =  sc.next() ;
                        
                            insert_data insert_employment_patient = new insert_data("employment",
                             employ_id , employement_status ,  employes , retired_status , Occupation , patient_id );
                             insert_employment_patient.insert_data_to_data_base();
                        }
                        
                        



                        
                    }
                }

            } catch (Exception er) {
                System.out.println(er.getMessage());
            }
        }

    }

}
