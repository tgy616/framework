package com.tgy;

/**
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-08-02 17:14
 **/
class Person{
    String name;
    public Person(String name){
        this.name=name;
    }
    public void sayHello(){
        System.out.println("hello "+name);
    }

}
public class AppTest{
    public static void main(String[] args) {
        Person p;//引用，存放在局部变量表里
        //new 的时候也是一种数据，放在堆里面
        p=new Person("zhangsan");
        p.sayHello();
    }
}
