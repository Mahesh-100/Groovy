package com.conditions

class Arithmetic {

    private int num1
    private int num2
    private String operation

    int getNum1() {
        return num1
    }

    void setNum1(int num1) {
        this.num1 = num1
    }

    def getNum2() {
        return num2
    }

    void setNum2(num2) {
        this.num2 = num2
    }

    def getOperation() {
        return operation
    }

    void setOperation(operation) {
        this.operation = operation
    }


    static void main(String[] args) {

        Arithmetic obj=new Arithmetic()
        obj.setNum1(19)
        obj.setNum2(9)
        obj.setOperation("/")


        switch (obj.getOperation()){
            case "+" :
               int c= obj.getNum1()+obj.getNum2()
                println(c)
                break
            case "-" :
                int c= obj.getNum1()-obj.getNum2()
                println(c)
                break
            case "*" :
                int c= obj.getNum1()*obj.getNum2()
                println(c)
                break
            case "/" :
                double c=  (obj.getNum1() / obj.getNum2())
                println(c)
                break
        }
        println("addition:" )
        print(obj.getNum1().power(obj.getNum2()))
    }
}
