package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.*;

@Service
public class DuplicatesService {
    public Object getDuplicates() {
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\gm117\\Downloads\\simple-app-starter\\simple-app-starter\\test-files\\normal.csv"), "UTF-8"));
            String line = reader.readLine(); // I already know what the schema is, no need for this line

            ArrayList<Data> entries = new ArrayList<>();
            ArrayList<Data> unique = new ArrayList<>();
            ArrayList<Data> duplicates = new ArrayList<>();

            boolean running = true;
            while (true) {
                line = reader.readLine();
                if (line == null || line.equals("")) break;
                //System.out.println(line);
                String[] values = line.split(",");
                Data entry = new Data(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11]);
                boolean dup = false;
                for (Data d : entries) {
                    if (entry.equals(d)) {
                        // Put the entry in the list of duplicates and stop looking
                        duplicates.add(entry);
                        dup = true;
                        break;
                    }
                }
                if (!dup) {
                    // Didn't find a matching value, add to the list of unique entries
                    unique.add(entry);
                }
                // Keep a list of all the entries, regardless of whether or not they're duplicates
                entries.add(entry);
            }

            /*
            System.out.println(unique.size() + " " + duplicates.size() + " " + entries.size());
            for (Data d : unique) {
                System.out.print(d.id + ", ");
            }
            System.out.println("");
             */

            LinkedHashMap<Integer, String> mapToJson = new LinkedHashMap<>();

            // Just get the data in somehow
            // I'm just using the first library I found, there's obviously better ways to do this
            for (int i = 0; i < unique.size(); i++) {
                mapToJson.put(i, unique.get(i).toString());
            }

            String jsonUnique = JSONValue.toJSONString(mapToJson);
            mapToJson = new LinkedHashMap<>();
            for (int i = 0; i < duplicates.size(); i++) {
                mapToJson.put(i, duplicates.get(i).toString());
            }

            String jsonDuplicates = JSONValue.toJSONString(mapToJson);

            JSONObject jsonOut = new JSONObject();
            jsonOut.put("unique", jsonUnique);
            jsonOut.put("duplicates", jsonDuplicates);

            return jsonOut.toString();
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
                        // First name, last name, and email should constitute a unique value
                        return true;
                    }

                    if (this.phone.equals(check.getPhone())) {
                        // Same should be true with phone
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

        public String toString() {
            //id,first_name,last_name,company,email,address1,address2,zip,city,state_long,state,phone
            return this.id + "," + this.firstName + "," + this.lastName + "," + this.company + "," + this.email + "," + this.address1 + "," + this.address2 + "," + this.zip
                + "," + this.city + "," + this.stateLong + "," + this.state + "," + this.phone;
        }
    }
}
