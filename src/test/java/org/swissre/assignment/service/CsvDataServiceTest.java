package org.swissre.assignment.service;

import org.junit.Before;
import org.junit.Test;
import org.swissre.assignment.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvDataServiceTest {
    private Map<Integer, List<Employee>> managerEmpMap;
    private Map<Integer, Employee> employeeMap;

    @Before
    public void setUp(){
        managerEmpMap = new HashMap<>();
        employeeMap = new HashMap<>();
    }
    private final CsvDataService csvDataService = new CsvDataService();

    @Test(expected = RuntimeException.class)
    public void readFromCsvWithNoFileThrowsException() {
        csvDataService.readFromCsv("", null, null);
    }

    @Test
    public void readFromCsvWithEmptyFileReturnsEmptyMap() {
        String file = "src/test/resources/employees_empty.csv";
        csvDataService.readFromCsv(file, managerEmpMap, employeeMap);
        assertTrue(employeeMap.isEmpty());
        assertThat(managerEmpMap.size(), is(0));
    }

    @Test
    public void readFromCsvSuccessTest() {
        String file = "src/test/resources/employees.csv";
        csvDataService.readFromCsv(file, managerEmpMap, employeeMap);
        assertFalse(employeeMap.isEmpty());
        assertFalse(managerEmpMap.isEmpty());
    }
}
