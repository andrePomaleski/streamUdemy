package org.example;

import org.example.entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public static List<Employee> readEmployeeCSV() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("csv/employees.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Not found");
        }
        catch (IOException e) {
            System.out.println("IO error");
        }
        return employees;
    }
}