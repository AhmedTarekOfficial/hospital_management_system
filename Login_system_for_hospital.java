package login_System;

import java.sql.*;
import java.util.Scanner;

import connection_data_base.database_connection;
import connection_data_base.fetch_elements_cond;
import connection_data_base.searching_data_for_all_tables;
import managers_permisions_for_employee.permissions;
public class Login_system_for_hospital {

    PreparedStatement pre;
    ResultSet rst;

    public void Login_system() throws SQLException, InterruptedException {

        try {

            database_connection conn = new database_connection();
            conn.connection_data();

            System.out.println(" Welcome to 'Founders' Square' Health Whether you're here " +
                    "for your health or to provide it," +
                    "we're glad you're here.  "
                    + "\n------------------------------------------------------------------------------------------------------------------");

            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("1-sign up As employee\n\n2-sign in as patient");

                fetch_elements_cond element = new fetch_elements_cond("managers", "email", "manager_id");

                try (Scanner so = new Scanner(System.in)) {
                    int choic = sc.nextInt();

                    if (choic == 1) {
                        System.out.print("\033[H\033[2J");

                        System.out.println("enter your employee_email : ");
                        String email_employee = so.next();

                        System.out.print("\033[H\033[2J");
                        System.out.println("enter your id ");
                        String password_employee = so.next();
                        System.out.print("\033[H\033[2J");

                        element.set_values(email_employee, password_employee);

                        String fetch_data = element.fetchElementsFromDatabasef();

                        if (fetch_data != null && !fetch_data.isEmpty()) {

                            System.out.print("\033[H\033[2J");

                            System.out.println("Login successful!\n---------------------");
                            System.out.println();

                            searching_data_for_all_tables see = new searching_data_for_all_tables(password_employee);
                            String namee = see.find_data_in_all_tables();
                           
                            System.out.println("welcome : "+namee);
                            System.out.println(" ");
                            permissions pe = new permissions();
                            pe.manager_permissios();



                        } else {

                            System.out.println("Invalid email or id. Please try again.");

                        }

                    }
                }
            }

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }

    }

}
