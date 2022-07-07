package com.example;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {

    private static HashMap<String, String> birthdays = new HashMap<String, String>();

    //
    // Func: ReadJSONFile
    // Desc: Reads a json file storing an array and returns an object
    // that can be iterated over
    //
    public static JSONArray ReadJSONArrayFile(String fileName) {
        //
        // read the birthday.json file and iterate over it
        //

        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONArray birthdayList = null;

        try (FileReader reader = new FileReader(fileName)) {
        // Read JSON file
        Object obj = jsonParser.parse(reader);

        birthdayList = (JSONArray) obj;
        //System.out.println(birthdayList);

        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (ParseException e) {
        e.printStackTrace();
        }

        return birthdayList;
    }

    public static void main(final String[] args) {
        //
        // how to read user input from keyboard
        //
        System.out.println("Reading user input into a string");

        // get user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name:");
        String name = input.nextLine();

        // print user input
        System.out.println("name = " + name);

        // close the scanner
        input.close();

        //
        // reads a json data file
        //

        /*
        * students will need to change the path below to work on THEIR laptop. This is currently the path for my laptop.
        * If students do not know or understand what a "path" is, students should first complete the
        * extra credit module on Files, Directories, and Folders in Canvas.
        */
        String pathToFile =
        "C:/Users/qujes/Documents/GitHub/LupitasLookupApp/src/lupita/src/main/java/com/example/birthday.json";

        JSONArray jsonData = ReadJSONArrayFile(pathToFile);

        // loop over list
        String birthday;
        JSONObject obj;

    
        for (Integer i = 0; i < jsonData.size(); i++) {
            // parse the object and pull out the name and birthday
            obj = (JSONObject) jsonData.get(i);
            birthday = (String) obj.get("birthday");
            String newname = (String) obj.get("name");

            birthdays.put(newname, birthday);
        }
    
        //System.out.println(birthdays);

        boolean found = false;
        for (String i : birthdays.keySet()) {
            if (i.contains(name)) {
                System.out.println("Lupita's friend " + i + "'s birthday is on " + birthdays.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Lupita has no friends with the name " + name);
        }
        
    }
}
