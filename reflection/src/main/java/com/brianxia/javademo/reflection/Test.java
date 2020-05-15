package com.brianxia.javademo.reflection;

@TestAnno
public class Test {

    @TestAnno("年龄")
    private int age;
    private String name;

    public Test(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Test() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @TestAnno
    private void doPrivate(String str){
        System.out.println("私有方法调用" +str);
    }
}
