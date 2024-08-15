package org.swissre.assignment;

import org.swissre.assignment.model.Employee;
import org.swissre.assignment.service.CsvDataService;
import org.swissre.assignment.service.ReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Code Assignment!
 *
 */
public class CodeAssignmentApp
{
    static Map<Integer, List<Employee>> managerEmpMap = new HashMap<>();
    static Map<Integer, Employee> employeeMap = new HashMap<>();

    public static void main( String[] args )
    {
        System.out.println( "Code assignment starts..." );

        String file = "src/main/resources/employees.csv";

        CsvDataService csvDataService = new CsvDataService();
        csvDataService.readFromCsv(file, managerEmpMap, employeeMap);

        ReportService reportService = new ReportService();
        reportService.reportManagersWithSalaryLessOrMoreThanAverage(managerEmpMap, employeeMap);
        reportService.getEmployeeWithTooLongReportingLine(managerEmpMap, employeeMap);

        System.out.println( "Code assignment ends!" );
    }

}
