import com.brianxia.javademo.reflection.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

//反射-调用方法示例
public class MethodDemo {

    private Object getTest(){
        try {
            Class<?> c4 = Class.forName("com.brianxia.javademo.reflection.Test");

            Constructor constructor ;
            Class[] classes = {int.class,java.lang.String.class};
            try {
                //constructor = c4.getDeclaredConstructor();
                constructor = c4.getDeclaredConstructor(classes);
                try {
                    Object aaa = constructor.newInstance(1, "aaa");
                    return aaa;
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

        return  null;
    }
    //调用私有方法
    @org.junit.Test
    public void method1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = getTest();
        /**
         * public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
         * public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
         */
        Method method = Test.class.getDeclaredMethod("doPrivate", String.class);
        method.setAccessible(true);
        method.invoke(obj,"aaa");
    }

    //前置方法
    private void doPre(){
        System.out.println("在方法前调用");
    }


    //后置方法
    private void doPost(){
        System.out.println("在方法后调用");
    }

    //案例：aop的模拟代码
    public Object aop(Object obj){
        doPre();
        Object ret = null;
        try{
            Method method = Test.class.getDeclaredMethod("doPrivate", String.class);
            method.setAccessible(true);
            ret =  method.invoke(obj,"aaa");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            doPost();
            return null;
        }
    }
    //增加前置和后置方法的调用
    @org.junit.Test
    public void method2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object obj = getTest();
        aop(obj);
    }

}
