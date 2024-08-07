package com.conditions

import java.util.concurrent.Executors
import java.util.concurrent.Future



class EmployeeDao {

    static void main(String[] args) {
        def employees = [
                new Employee("maheshkumr", 22, 30000, "Development"),
                new Employee("nani", 23, 35000, "HR"),
                new Employee("fairish", 25, 45000, "HR"),
                new Employee("suresh", 39, 60000, "IT"),
                new Employee("ramesh", 38, 70000, "finance"),
                new Employee("sri", 45, 80000, "IT"),
                new Employee("raghu", 27, 760000, "Development"),
                new Employee("ganesh", 42, 68000, "finance")
        ]
        def executor = Executors.newFixedThreadPool(2)
        // Filter out employees who are not in a specified department.
        def thread1 = {
            println Thread.currentThread().name

            String targetDepartment = "HR"
            def listOfNotTargetDept = { Employee e -> (e.department != targetDepartment) }
            employees.findAll(listOfNotTargetDept).each { println it }

            // Group employees by their department.

            def groupingByDept = employees.groupBy { it.department }
            println "\nEmployees by each department"
            groupingByDept.each { dept, empList -> println "$dept : $empList " }

            // Calculate the average salary for each department.

            def avgSalByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
                {
                    [dept, empList.collect { it.salary }.sum() / empList.size()]
                }
            }
            println "\naverage salary by each department"
            avgSalByDept.each { deptName, sal -> println "$deptName : $sal " }

            // Find the highest-paid employee in each department.

            def highPaidEmpByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
                {
                    [dept, empList.max { it.salary }]
                }
            }

            println "\nDepartments with highest salary Employee"
            highPaidEmpByDept.each { deptName, sal -> println "$deptName : $sal " }
        }
        def thread2 = {
            println Thread.currentThread().name
            // Show the salary list of all the employees in descending order


            def salByDescendingOrder = employees.sort { -it.salary }
            println "\nSalaries in descending order:"
            salByDescendingOrder.each { println "${it.name},${it.age},${it.salary},${it.department}" }



            def salByRange = employees.findAll { it.salary > 50000 && it.salary < 100000 }
            println "\nemployees between salary range:"
            salByRange.each { println "${it.name},${it.age},${it.salary},${it.department}" }

            // Calculate the salary expenses for each department


            def SalExpByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
                {
                    [dept, empList.collect { it.salary }.sum()]
                }
            }
            println "\nsalary expenses by each department:"
            SalExpByDept.each { deptName, sal -> println "$deptName : $sal " }
            // Find employees with Longest and shortest names


            def employeeWithLongestName = employees.max { it.name.size() }
            def employeeWithShortestName = employees.min { it.name.size() }
            def shortAndLongNames = [employeeWithShortestName, employeeWithLongestName]
            println "\nshort and long names:"
            shortAndLongNames.each { println "${it.name},${it.age},${it.salary},${it.department}" }

        }
        Future<?> future1=executor.submit(thread1)



        Future<?> future2=executor.submit(thread2)
        future1.get()
        future2.get()
        executor.shutdown()

    }

}
