package com.bitter.PingSweep.config;

import com.bitter.PingSweep.model.NameList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class GetConfigValues {

    public GetConfigValues() {}

    public List<NameList> getNameValues() throws IOException { //Should be done with POST but Spring Sec is probably needed for deployment

        List<NameList> nameSet = new ArrayList<>();
        String line;
        try {
            InputStream in = getClass().getResourceAsStream("/nameFile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                String address = "";
                String name = "";
                address += line;
                line = reader.readLine();
                name += line;
                if (!(name.equals("") || address.equals(""))) {
                    System.out.println("NameFile entry " + name + " " + address);
                    NameList nextEntry = new NameList(address, name);
                    nameSet.add(nextEntry);
                }
            }
            reader.close();
            return nameSet;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}