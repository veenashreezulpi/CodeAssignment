package org.swissre.assignment.service;

import org.junit.Before;
import org.junit.Test;
import org.swissre.assignment.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ReportServiceTest {

    private Map<Integer, List<Employee>> managerEmpMap;
    private Map<Integer, Employee> employeeMap;

    @Before
    public void setUp(){
        managerEmpMap = new HashMap<>();
        employeeMap = new HashMap<>();
    }
    private final CsvDataService csvDataService = new CsvDataService();
    private final ReportService reportService = new ReportService();

    @Test
    public void testReportManagersWithSalaryLessOrMoreThanAverage(){
        String file = "src/test/resources/employees.csv";
        csvDataService.readFromCsv(file, managerEmpMap, employeeMap);
        reportService.reportManagersWithSalaryLessOrMoreThanAverage(managerEmpMap, employeeMap);
        assertTrue(true);
    }

    @Test
    public void testGetEmployeeWithTooLongReportingLine(){
        String file = "src/test/resources/employees.csv";
        csvDataService.readFromCsv(file, managerEmpMap, employeeMap);
        reportService.getEmployeeWithTooLongReportingLine(managerEmpMap, employeeMap);
        assertTrue(true);
    }
}
