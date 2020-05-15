import com.brianxia.javademo.reflection.Test;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

//反射-属性示例
public class FieldDemo {

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

    //设置和获取属性
    @org.junit.Test
    public void field1() throws NoSuchFieldException, IllegalAccessException {
        Class<Test> testClass = Test.class;
        Field field = testClass.getDeclaredField("name");
        Object test = getTest();
        field.setAccessible(true);
        field.set(test,"张三");
        System.out.println(field.get(test));
    }

    //案例：map和实体类之间的转换
    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get((field.getName())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 实体对象转成Map
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @org.junit.Test
    public void field2(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","张三");
        map.put("age",18);

        Object r = map2Object(map, Test.class);
        System.out.println(r);

        Map<String, Object> stringObjectMap = object2Map(r);
        System.out.println(stringObjectMap);

    }
}
