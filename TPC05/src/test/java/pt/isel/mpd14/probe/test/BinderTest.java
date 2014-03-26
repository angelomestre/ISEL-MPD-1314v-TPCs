package pt.isel.mpd14.probe.test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.mpd14.probe.*;
import pt.isel.mpd14.probe.test.model.Student;
import pt.isel.mpd14.probe.test.model.StudentDto;

/**
 * Unit test for Binder class.
 */
public class BinderTest extends TestCase{
    
    final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    public void test_bind_student_to_studentDto() throws Exception
    {
        /*
         Arrange
        */
        Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
        Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
          Act
        */
        StudentDto s2 = new Binder(new BinderFields()).bindTo(StudentDto.class, s1fields);
        System.out.println(s2);
        
        /*
        if(s2.id != s1.id){
            throw new IllegalStateException();
        }
        */
        Assert.assertEquals(s1.getId(), s2.id);
        Assert.assertEquals(s1.getName(), s2.name);
        Assert.assertEquals(null, s2.birthDate);

    }
    
    public void test_bind_to_student_properties() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
        /*
        Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "Maria josefina");
        v.put("id", 657657);
        v.put("birthdate", sdf.parse("4-5-1997"));
        /*
        Act
        */
        Student s = new Binder(new BinderProps()).bindTo(Student.class, v);
        /*
        Assert
        */
        Assert.assertEquals(v.get("name"), s.getName());
        Assert.assertEquals(v.get("id"), new Integer(s.getId()));
        Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
    
    }
    
    public void test_bind_to_student_fields_and_properties() 
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
        /*
        Arrange
        */
        Map<String, Object> v = new HashMap<>();
        v.put("name", "Rui Miguelinho");
        v.put("id", 182739);
        v.put("birthdate", sdf.parse("4-8-1990"));
        /*
        Act
        */
        Student s = new Binder(new BinderPropsAndFields()).bindTo(Student.class, v);
        /*
        Assert
        */
        Assert.assertEquals(v.get("name"), s.getName());
        Assert.assertEquals(v.get("id"), new Integer(s.getId()));
        Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
    
    }
}
