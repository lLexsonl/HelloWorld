/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Person;

/**
 *
 * @author MIPC
 */
public class Student implements Person{
    String id;
    String name;
    int age;
    public Student(String i, String n, int a){
        id = i;
        name = n;
        age = a;
    }
    protected int studyHours(){return age/2;}
    public String getID(){return id;}
    @Override
    public String getName(){return name;}
    @Override
    public int getAge(){return age;}
    @Override
    public boolean equals(Person other){
        if(!(other instanceof Student)) return false;
        Student s = (Student) other;
        return id.equals(s.id);
    }
    @Override
    public String toString(){
        return "Studen(ID: " + id + ", Name: " + name + ", Age: " + age + ")";
    }
}
