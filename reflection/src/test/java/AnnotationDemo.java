import com.brianxia.javademo.reflection.Test;
import com.brianxia.javademo.reflection.TestAnno;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

//反射-属性示例
public class AnnotationDemo {

    private Object getTest(){
        try {
            Class<?> c4 = Class.forName("com.brianxia.javademo.reflection.Test");

            Constructor constructor ;
            Class[] classes = {int.class, String.class};
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

    //遍历所有方法、属性，获取注解响应的内容
    @org.junit.Test
    public void field1() throws NoSuchFieldException, IllegalAccessException {
        Class<Test> testClass = Test.class;
        TestAnno declaredAnnotation = testClass.getDeclaredAnnotation(TestAnno.class);
        if(declaredAnnotation != null){
            System.out.println("类上包含了TestAnno注解");
        }

        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if(declaredMethod.getAnnotation(TestAnno.class) != null){
                System.out.println(declaredMethod.getName() + "方法包含TestAnno注解");
            }
        }

        Field[] declaredFields = testClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            TestAnno declaredAnnotation1 = declaredField.getDeclaredAnnotation(TestAnno.class);
            if(declaredAnnotation1 != null){
                System.out.println(declaredField.getName() + "属性包含TestAnno注解");
                System.out.println("注解的value为"+declaredAnnotation1.value());
            }
        }
    }

}
