package com.conditions

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.locks.ReentrantLock

class EmployeeService {
    private final ReentrantLock lock = new ReentrantLock()

     def insertEmployeeDetails(List<Employee> employees, String filePath) {
        lock.lock()
        try {
            new File(filePath).withWriter { writer ->
                employees.each { employee ->
                    writer.writeLine("${employee.name},${employee.age},${employee.salary},${employee.department}")
                }
            }
        }finally {
            lock.unlock()
        }
    }
     void updateEmployeeDepartment(String filePath, String employeeName, String newDepartment) {
        lock.lock()
        try {
            def lines = Files.readAllLines(Paths.get(filePath))
            def updatedLines = []

            lines.each { line ->
                def details = line.split(',')
                if (details[0] == employeeName) {
                    details[3] = newDepartment
                }
                updatedLines << details.join(',')
            }

            Files.write(Paths.get(filePath), updatedLines.join('\n').bytes)
        }
            finally {
                lock.unlock()
            }
    }
    static void main(String... args){
        EmployeeService empService=new EmployeeService()
        def employees = [
                new Employee("maheshkumar", 22, 30000, ),
                new Employee("nani", 23, 35000, ),
                new Employee("fairish", 25, 45000, ),
                new Employee("suresh", 39, 60000, ),
                new Employee("ramesh", 38, 70000, ),
                new Employee("sri", 45, 80000, ),
                new Employee("raghu", 27, 760000, ),
                new Employee("ganesh", 42, 68000, )
        ]
        def executor= Executors.newFixedThreadPool(2)
        def filePath = "C:/Users/MaheshD/Documents/employees.txt"
        def insertTask={
            empService.insertEmployeeDetails(employees, filePath)
            println "inserted successfully"
        }
        def updateTask={
            empService.updateEmployeeDepartment(filePath, "sri", "HR")
            empService.updateEmployeeDepartment(filePath, "ganesh", "Developer")

            println "updated successfully"
        }
        Future<?>insert=executor.submit(insertTask)
        Future<?>update=executor.submit(updateTask)
        insert.get()
        update.get()

        executor.shutdown()
    }
}
