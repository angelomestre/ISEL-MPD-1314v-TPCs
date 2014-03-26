package pt.isel.mpd14.probe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Binder {
    
    private BinderMember binderM;
    
    public Binder(BinderMember binderM){
        this.binderM = binderM;
    }
    
    public static Map<String, Object> getFieldsValues(Object o)
            throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> res = new HashMap<>();
        Field[] fs = o.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            res.put(f.getName(), f.get(o));
        }
        return res;
    }
    
    public <T> T bindTo(Class<T> klass, Map<String, Object> vals)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (klass == null || vals == null) {
            throw new IllegalArgumentException();
        }
        T target = klass.newInstance();
        for (Map.Entry<String, Object> e : vals.entrySet()) {
            binderM.bind(target, e.getKey(), e.getValue());
        }
        return target;
    }

}

class WrapperUtilites {

    final static Map<Class<?>, Class<?>> wrappers = new HashMap<>();

    static {
        wrappers.put(boolean.class, Boolean.class);
        wrappers.put(short.class, Short.class);
        wrappers.put(boolean.class, Boolean.class);
        wrappers.put(int.class, Integer.class);

    }

    public static Class<?> toWrapper(Class<?> c) {
        return c.isPrimitive() ? wrappers.get(c) : c;
    }

}
