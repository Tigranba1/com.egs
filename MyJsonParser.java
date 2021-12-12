package com.java.egs.parser;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MyJsonParser {

    public static void main(String[] args) {

        JsonObj jo = new JsonObj();

        // putting data to JSON Object
        jo.put("name", "Tigran");
        jo.put("lastName", "Bayburd");
        jo.put("age", 25);


        Map m = new LinkedHashMap(4);
        m.put("streetAddress", "21 2nd Street");
        m.put("city", "Gyumri");

        jo.put("address", m);


        // writing JSON to file:"myjson.json"
        try {
            PrintWriter pw = new PrintWriter("myjson.json");
            pw.write(jo.toStringJSON());

            pw.flush();
            pw.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


//PArse JSON for file to java object
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("myjson.json"));
        } catch (IOException e) {
            e.getMessage();
        } catch (ParseException e) {
            e.getMessage();
        }

        JSONObject jso = (JSONObject) obj;

        String firstName = (String) jso.get("name");
        String lastName = (String) jso.get("lastName");

        System.out.println(firstName);
        System.out.println(lastName);

        long age = (long) jso.get("age");
        System.out.println(age);

        Map address = ((Map)jso.get("address"));

        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }
}

