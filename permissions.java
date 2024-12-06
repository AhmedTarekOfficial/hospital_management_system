package managers_permisions_for_employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import connection_data_base.Fetch_elements;
import connection_data_base.database_connection;
import connection_data_base.delete_element;
import connection_data_base.fetch_elements_cond;
import connection_data_base.insert_data;
import connection_data_base.update_elements;

public class permissions {
    

    PreparedStatement pre;
    ResultSet rst;
    



   

    public void manager_permissios() throws SQLException, InterruptedException {

        try {

            System.out.println(
                    "1-employees managment");

            try (Scanner scc = new Scanner(System.in)) {
                int choicc = scc.nextInt();

                if (choicc == 1) {
                    System.out.print("\033[H\033[2J");

                    System.out.println("what do you want to make over employees ?\n------------------");

                    System.out.println(
                            "1-list employees\n2-update employees\n3-delete employee\n4-add employee\n\n5-go back");

                    int choic_manager = scc.nextInt();

                    if (choic_manager == 1) {
                        System.out.print("\033[H\033[2J");

                        Fetch_elements employee_information = new Fetch_elements("employees", "emp_id", "first_name",
                                "last_name", "department_id");

                        String Fetch_employee_information = employee_information.get_specific_data();

                        database_connection conn = new database_connection();
                        conn.connection_data();

                        pre = conn.con.prepareStatement(Fetch_employee_information);

                        rst = pre.executeQuery();

                        while (rst.next()) {

                            String id = rst.getString("emp_id");
                            String first_name = rst.getString("first_name");
                            String last_name = rst.getString("last_name");
                            String department_id = rst.getString("department_id");

                            ArrayList<String> information_emp = new ArrayList<>();

                            information_emp.add(id);
                            information_emp.add(first_name);
                            information_emp.add(last_name);
                            information_emp.add(department_id);

                            for (String info : information_emp) {

                                System.out.print(info.matches("\\d+") ? info + " " : info + " ");

                            }
                            System.out.println();

                        }

                    } else if (choic_manager == 2) {

                        System.out.println("please enter the id of the employee you want to upadte "
                                + "the information about it : ");

                        String id_employee = scc.next();

                        fetch_elements_cond condition_fetiching = new fetch_elements_cond("employees", "emp_id");

                        condition_fetiching.set_values(id_employee);

                        if (condition_fetiching.fetchElementsFromDatabasef().equals(id_employee)) {

                            System.out.println("what you want to update for this employee ?");
                            System.out.println("--------------------------------------------------");

                            System.out.println("1-update salary\n2-update department\n3-update commition pct ");

                            int choice_ma = scc.nextInt();

                            if (choice_ma == 1) {

                                System.out.print("enter the new salary please : ");
                                String salary = scc.next();

                                update_elements update_data = new update_elements("employees", "emp_id",
                                        id_employee, "salary");

                                update_data.set_value(salary);

                                if (update_data.update_element_in_database()) {
                                    System.out.println("done update the salary");
                                } else {
                                    System.out.println("cant find the employee to update there salary");
                                }

                            } else if (choice_ma == 2) {

                                System.out.println("Please enter the id of the department you want to "
                                        + "transfer the employee to.");

                                String department_id = scc.next();

                                fetch_elements_cond fetch_from_data = new fetch_elements_cond("departments",
                                        "department_id");
                                fetch_from_data.set_values(department_id);

                                if (fetch_from_data.fetchElementsFromDatabasef() != null) {

                                    update_elements update_data_in_data_base = new update_elements("employees",
                                            "emp_id", id_employee, "department_id");
                                    update_data_in_data_base.set_value(department_id);

                                    if (update_data_in_data_base.update_element_in_database()) {
                                        System.out.println("done update the elements in database");

                                    } else {
                                        System.out.println("cant find the department");
                                    }

                                }

                            } else if (choice_ma == 3) {
                                System.out.print("enter the new commition of the employee : ");
                                String commition = scc.next();

                                update_elements update_data = new update_elements("employees", "emp_id", id_employee,
                                        "commition_pct");
                                update_data.set_value(commition);

                                if (update_data.update_element_in_database() == true) {
                                    System.out.println("done update the data of the employee");
                                } else {
                                    System.out.println("cant find the employee");

                                }

                            }

                        }

                    } else if (choic_manager == 3) {

                        System.out.print("enter the id of the employee you want to delete it : ");

                        String id_emp = scc.next();

                        fetch_elements_cond find_id_emp = new fetch_elements_cond("employees", "emp_id");
                        find_id_emp.set_values(id_emp);

                        if (find_id_emp.fetchElementsFromDatabasef().equals(id_emp)) {

                            delete_element delete_el = new delete_element("employees", "emp_id", id_emp);

                            if (delete_el.delete_elements_from_data_base()) {
                                System.out.println("complete delete the employees from the System");
                            } else {
                                System.out.println("cant find the employee in the");
                            }

                        }

                    } else if (choic_manager == 4) {
                        System.out.print("please enter the id of the employee : ");
                        String id_emp = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("please enter the first_name of the employee : ");
                        String first_name = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("please enter the last_name of the employee : ");
                        String last_name = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the salary of the employee : ");
                        String salary = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("please enter the commition of the employee : ");
                        String commition = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the id of the job of the employee : ");
                        String job_id = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the department id of this employee ");
                        String department_id = scc.next();
                        System.out.print("\033[H\033[2J");
                        System.out.print("enter the manager id of this employee : ");
                        String manager_id = scc.next();

                        fetch_elements_cond find_job_id = new fetch_elements_cond("jobs", "job_id");
                        find_job_id.set_values(job_id);

                        find_job_id.fetchElementsFromDatabasef();

                        fetch_elements_cond find_departmanet_id = new fetch_elements_cond("departments",
                                "department_id");
                        find_departmanet_id.set_values(department_id);
                        find_departmanet_id.fetchElementsFromDatabasef();

                        fetch_elements_cond find_manager_id = new fetch_elements_cond("managers", "manager_id");
                        find_manager_id.set_values(manager_id);
                        find_manager_id.fetchElementsFromDatabasef();

                       
                            insert_data insert_dataa = new insert_data("employees", id_emp , first_name , last_name , salary , commition , job_id
                            , department_id , manager_id);
                            if (insert_dataa.insert_data_to_data_base()) {
                                System.out.println("insert data done");

                            } else {
                                System.out.println("field to insert data");
                            }

                        
                            
                        
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
