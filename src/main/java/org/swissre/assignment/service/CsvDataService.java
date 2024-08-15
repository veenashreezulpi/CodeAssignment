package org.swissre.assignment.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import org.swissre.assignment.model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class CsvDataService {
    public void readFromCsv(String filename, Map<Integer, List<Employee>> managerEmpMap, Map<Integer, Employee> employeeMap){

        List<String[]> allLines;
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).
                withSkipLines(1).build()) {
            allLines = reader.readAll();
        } catch (IOException | CsvException ex) {
            throw new RuntimeException(ex);
        }

        for (String[] line: allLines) {
            Employee employee = mapEmployeeFromFileData(line);
            employeeMap.put(employee.getId(), employee);
            if(employee.getManagerId() != null){
                if (managerEmpMap.containsKey(employee.getManagerId())) {
                    managerEmpMap.get(employee.getManagerId()).add(employee);
                } else {
                    List<Employee> employeeList = new ArrayList<>();
                    employeeList.add(employee);
                    managerEmpMap.put(employee.getManagerId(), employeeList);
                }
            }
        }
    }
    private Employee mapEmployeeFromFileData(String[] line) {
        Employee emp = new Employee();
        emp.setId(Integer.valueOf(line[0]));
        emp.setFirstName(line[1]);
        emp.setLastName(line[2]);
        emp.setSalary(Double.valueOf(line[3]));
        if(StringUtils.isNotBlank(line[4])){
            emp.setManagerId(Integer.valueOf(line[4]));
        }
        return emp;
    }
}
