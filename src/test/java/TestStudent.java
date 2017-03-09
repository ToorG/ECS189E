import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by HarjotSingh on 3/8/17.
 */
public class TestStudent {
    /*
        added by Harjot
     */
    private IInstructor instructor;
    private IStudent student;
    private IAdmin admin;
    @Before
    public void setup() {
        this.admin = new Admin();
        /*
            added by Harjot
         */
        this.instructor = new Instructor();
        this.student = new Student();
    }

// test to make sure student cannot register for a full class

    @Test
    public void testRegisterForClass2(){
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("student1","Test",2017);
        this.student.registerForClass("student2","Test",2017);
        this.student.registerForClass("student3","Test",2017);
        assertFalse(this.student.isRegisteredFor("student3","Test",2017));
    }
    // test for non student to turn in homework
    @Test
    public void testMakeClass2()  {
        this.admin.createClass("Test", 2017, "instructor", 15);
        this.student.registerForClass("student1", "Test", 2017);
        this.instructor.addHomework("teacher", "Test", 2017, "hw1","code");
        this.student.submitHomework("student1", "hw1","ABC","Test", 2017);
        this.student.submitHomework("student2","hw1","ABC","Test",2017);
        assertFalse(this.student.hasSubmitted("student2","hw1","Test",2017));
    }





}
