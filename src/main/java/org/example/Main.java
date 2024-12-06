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
        List<Employee> employees = readEmployeeCSV();
        double minimiumValue = 2000.0;

        List<Employee> greaterThanMinimiumEmployee = employees.stream()
                .filter(x -> x.getWage() >= minimiumValue)
                .sorted((x, y) -> x.getEmail().compareToIgnoreCase(y.getEmail()))
                .toList();

        greaterThanMinimiumEmployee.forEach(x -> {
            System.out.println(x.getEmail());
        });

        double startsWithMWage = employees.stream()
                .filter(x -> x.getName().startsWith("M"))
                .map(x -> x.getWage())
                .reduce(0.0, Double::sum);

        System.out.println("%.2f".formatted(startsWithMWage));

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