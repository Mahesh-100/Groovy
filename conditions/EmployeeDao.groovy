package com.conditions




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

        // Filter out employees who are not in a specified department.
         String targetDepartment="HR"
        def listOfNotTargetDept = { Employee e -> (e.department != targetDepartment) }
        employees.findAll(listOfNotTargetDept).each { println it }

        // Group employees by their department.
        def groupingByDept = employees.groupBy { it.department }
        groupingByDept.each { dept, empList -> println "$dept : $empList " }

        // Calculate the average salary for each department.
        def avgSalByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
            {
                [dept, empList.collect { it.salary }.sum() / empList.size()]
            }
        }
        avgSalByDept.each { deptName, sal -> println "$deptName : $sal " }

        // Find the highest-paid employee in each department.
        def highPaidEmpByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
            {
                [dept, empList.max { it.salary }]
            }
        }

        println "\nDepartments with highest salary Employee"
        highPaidEmpByDept.each { deptName, sal -> println "$deptName : $sal " }

        // Show the salary list of all the employees in descending order
        println "\nSalaries in descending order:"
        def salByDescendingOrder = employees.sort { -it.salary }
        salByDescendingOrder.each { println "${it.name},${it.age},${it.salary},${it.department}" }

        println "\nemployees between salary range:"
        def salByRange = employees.findAll { it.salary > 50000 && it.salary < 100000 }
        salByRange.each { println "${it.name},${it.age},${it.salary},${it.department}" }

        // Calculate the salary expenses for each department
        println "\nsalary expenses by each department:"
        def SalExpByDept = employees.groupBy { it.department }.collectEntries { dept, empList ->
            {
                [dept, empList.collect { it.salary }.sum() ]
            }
        }
        SalExpByDept.each { deptName, sal -> println "$deptName : $sal " }
        // Find employees with Longest and shortest names
        println "\nshort and long names:"
        def employeeWithLongestName =  employees.max {it.name.size()}
        def employeeWithShortestName  =employees.min {it.name.size()}
        def shortAndLongNames=[employeeWithShortestName,employeeWithLongestName]
        shortAndLongNames.each {println "${it.name},${it.age},${it.salary},${it.department}"}
    }

}
