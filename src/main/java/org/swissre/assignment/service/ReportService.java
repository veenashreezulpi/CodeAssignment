package org.swissre.assignment.service;

import org.swissre.assignment.model.Employee;

import java.util.List;
import java.util.Map;

public class ReportService {

    public void reportManagersWithSalaryLessOrMoreThanAverage(Map<Integer, List<Employee>> managerMap, Map<Integer, Employee> empMap){

        for (Map.Entry<Integer, List<Employee>> entry : managerMap.entrySet()) {
            Double avgSal = entry.getValue().stream().mapToDouble(m -> m.getSalary()).average().getAsDouble();
            Double minSal = (0.2*avgSal)+avgSal;
            Double maxSal = (0.5*avgSal)+avgSal;
            Employee emp = empMap.get(entry.getKey());
            if(emp.getSalary() < minSal){
                System.out.println(emp +" earns less than 20% of average salary of its direct subordinates by "+ (minSal - emp.getSalary()));
            } else if(emp.getSalary() > maxSal){
                System.out.println(emp +" earns more than 50% of average salary of its direct subordinates by "+ (emp.getSalary() - maxSal));
            }
        }
    }

    public void getEmployeeWithTooLongReportingLine(Map<Integer, List<Employee>> managerMap, Map<Integer, Employee> empMap) {

        empMap.forEach((id, emp) -> {
            int count = 0;
            Integer empId = emp.getManagerId();
            while (managerMap.containsKey(empId)) {
                count++;
                empId = empMap.get(empId).getManagerId();
            }
            if (count > 4) {
                System.out.println(emp + " having reporting line which is too long by " + (count - 4));
            }

        });

    }
}
