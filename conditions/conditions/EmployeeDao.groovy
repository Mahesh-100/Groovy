package com.conditions

import java.util.stream.Collectors


class EmployeeDao {

    static void main(String[] args){
        def employees=[
                new Employee("mahesh",22,30000,"Development"),
                new Employee("kumar",23,35000,"HR"),
                new Employee("fairish",25,45000,"HR"),
                new Employee("suresh",39,60000,"IT"),
                new Employee("ramesh",38,70000,"finance"),
                new Employee("sirisha",45,80000,"IT"),
                new Employee("raghu",27,760000,"Development"),
                new Employee("ganesh",42,68000,"finance")
        ]

        // Filter out employees who are not in a specified department.
        String targetDepartment="HR"
def listOfNotTargetDept={Employee e-> (e.department != targetDepartment) }
//employees.findAll(listOfNotTargetDept).each {println it}

        // Group employees by their department.
        def groupingByDept=employees.groupBy {it.department}
        //groupingByDept.each{dept,empList->println "$dept : $empList "}

        // Calculate the average salary for each department.
        def avgSalByDept=employees.groupBy {it.department}.collectEntries {dept,empList->{
            [dept, empList.collect { it.salary }.sum() / empList.size()]}}
            avgSalByDept.each{deptName,sal->println "$deptName : $sal "}

        // Find the highest-paid employee in each department.
        def highPaidEmpByDept=employees.groupBy {it.department}.collectEntries {dept,empList->{
            [dept, empList.max{it.salary}]
        }
        }
        println "Departments with highest salary Employee"
        highPaidEmpByDept.each{deptName,sal->println "$deptName : $sal "}


    }

}
