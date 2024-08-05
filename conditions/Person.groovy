package com.conditions



class Person {
      def name
    def age
    Person(String name, int age) {
        this.name = name
        this.age = age
    }

    static void main(String[] args){
        def people = [
                new Person('Alice', 20),
                new Person('Bob', 17),
                new Person( 'Charlie',  22),
                new Person('David',  18),
                new Person('Eve', 21)
        ]


        def adult={person->person.age>18}
        def toUpperCase={person->new Person(person.name.toUpperCase(), person.age)}
        def byAge={p1,p2->p1.age<=>p2.age}
        def eligible=people.findAll(adult).collect(toUpperCase).sort {byAge}
        eligible.each {println it}
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                '}'
    }
}
