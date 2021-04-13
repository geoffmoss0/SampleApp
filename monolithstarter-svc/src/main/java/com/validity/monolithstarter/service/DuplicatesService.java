package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DuplicatesService {
    public Object getDuplicates() {
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\gm117\\Downloads\\simple-app-starter\\simple-app-starter\\test-files\\normal.csv"), "UTF-8"));
            String line = reader.readLine();
            line = reader.readLine();
            System.out.println(line);
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Failure";
    }

    private class Data {
        //id,first_name,last_name,company,email,address1,address2,zip,city,state_long,state,phone
        int id;
        String firstName;
        String lastName;
        String company;
        String email;
        String address1;
        String address2;
        String zip;
        String city;
        String stateLong;
        String state;
        String phone;

        public Data(int id, String first_name,String last_name,String company,String email,String address1,String address2,String zip,String city,String state_long,String state, String phone) {
            this.id = id;
            this.firstName = first_name;
            this.lastName = last_name;
            this.company = company;
            this.email = email;
            this.address1 = address1;
            this.address2 = address2;
            this.zip = zip;
            this.city = city;
            this.stateLong = state_long;
            this.state = state;
            this.phone = phone;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Data)) return false;
            Data check = (Data) other;

            if (this.firstName.toLowerCase().equals(check.getFirstName().toLowerCase())) {
                if (this.lastName.toLowerCase().equals(check.getLastName().toLowerCase())) {

                    if (this.email.toLowerCase().equals(check.getEmail().toLowerCase())) {
                        return true;
                    }

                    if (this.phone.equals(check.getPhone())) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getEmail() {
            return this.email;
        }

        public String getPhone() {
            return this.phone;
        }
    }
}
