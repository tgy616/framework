package java.util;

/**
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-08-02 16:22
 **/
//这个类可否被编译
//这个类可否被执行
public class List {

    public static void main(String[] args) {
        System.out.println("我是一个List");
        ClassLoader c=List.class.getClassLoader();
        while (c!=null){
            System.out.println(c);
            c=c.getParent();
        }
    }
}
