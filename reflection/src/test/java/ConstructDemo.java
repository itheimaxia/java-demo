import com.brianxia.javademo.reflection.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

//反射-构造方法示例
public class ConstructDemo {

    //获取所有构造方法
    @org.junit.Test
    public void constuct(){
        Class c4 = Test.class;
        Constructor[] constructors ;
        constructors = c4.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            // Modifier 类提供了 static方法和常量，对类和成员访问修饰符进行解码。
            System.out.print(Modifier.toString(constructor.getModifiers()) + "参数：");
            Class[] parametertypes = constructor.getParameterTypes();
            //打印所有的参数
            for(Class clazz : parametertypes){
                System.out.print(clazz.getName() + " ");
            }
            System.out.println("");
        }

    }



    //获取指定的构造方法
    @org.junit.Test
    public void constuct2(){
        Class c4 = Test.class;
        Constructor constructor ;
        Class[] classes = {int.class,java.lang.String.class};
        try {
            //constructor = c4.getDeclaredConstructor();
            constructor = c4.getDeclaredConstructor(classes);
            // Modifier 类提供了 static方法和常量，对类和成员访问修饰符进行解码。
            System.out.print(Modifier.toString(constructor.getModifiers()) + "参数：");
            Class[] parametertypes = constructor.getParameterTypes();
            //打印所有的参数
            for(Class clazz : parametertypes){
                System.out.print(clazz.getName() + " ");
            }
            System.out.println("");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    //调用构造方法
    @org.junit.Test
    public void constuct3(){
        Class c4 = Test.class;
        Constructor constructor ;
        Class[] classes = {int.class,java.lang.String.class};
        try {
            //constructor = c4.getDeclaredConstructor();
            constructor = c4.getDeclaredConstructor(classes);
            try {
                Object aaa = constructor.newInstance(1, "aaa");

                System.out.println(aaa);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    //案例：模拟一下spring创建bean
    @org.junit.Test
    public void constuct4(){
        try {
            Class<?> c4 = Class.forName("com.brianxia.javademo.reflection.Test");

            Constructor constructor ;
            Class[] classes = {int.class,java.lang.String.class};
            try {
                //constructor = c4.getDeclaredConstructor();
                constructor = c4.getDeclaredConstructor(classes);
                try {
                    Object aaa = constructor.newInstance(1, "aaa");

                    System.out.println(aaa);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
