package com.pluralsight.phoneBookMVC.controller;

import com.pluralsight.phoneBookMVC.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "firstname", defaultValue = "Gena") String firstname,
                        @RequestParam(value = "lastname", defaultValue = "Chursov") String lastname,
                        @RequestParam(value = "age", defaultValue = "30") int age) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user")
    public User postUser(User user) {
        System.out.println(user.getFirstname());
        return user;
    }

    @PostMapping("/upload")
    public User postUser(@RequestParam("file") MultipartFile file) throws IOException {
        User user = csvToUser(file.getInputStream());
        System.out.println(user.getFirstname());
        return user;
    }

    private static User csvToUser(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<User> users = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                User user = new User();
                user.setFirstname(csvRecord.get("firstname"));
                user.setLastname(csvRecord.get("lastname"));
                user.setAge(Integer.parseInt(csvRecord.get("age")));

                users.add(user);
            }

            return users.get(0);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
