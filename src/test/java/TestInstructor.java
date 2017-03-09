import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by HarjotSingh on 3/8/17.
 */
public class TestInstructor {
    private IAdmin admin;

    /*
        added by Harjot
     */
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        /*
            added by Harjot
         */
        this.instructor = new Instructor();
        this.student = new Student();
    }

// seeing if a student can register for class
    @Test
    public void testRegister(){
        this.admin.createClass("Test",2017, "Instructor",50);
        this.student.registerForClass("Harjot","Test",2017);
        boolean test = this.student.isRegisteredFor("Harjot","Test",2017);
        assertTrue(test);
    }
// simple test to create homework assignment
    @Test
    public void testCreateHomework(){
        this.admin.createClass("Test",2017,"Instructor",50);
        this.instructor.addHomework("Instructor","Test",2017,"hw1","code");
        assertTrue(this.instructor.homeworkExists("Test",2017,"hw1"));
    }
//test to assign homework to different class
    @Test
    public void testDifferentHomework1(){
        this.admin.createClass("Test",2017,"instructor",50);
        this.student.registerForClass("Harjot","Test",2017);
        this.instructor.addHomework("Instructor","Test",2017,"hw1","code");
        assertFalse(this.instructor.homeworkExists("Test",2017,"hw1"));
    }
// test to see if no homework name is given
@Test
    public void testDifferentHomework10(){
        this.admin.createClass("Test",2017,"instructor",50);
        this.student.registerForClass("Harjot","Test",2017);
        this.instructor.addHomework("Instructor","Test",2017,null,"code");
        assertFalse(this.instructor.homeworkExists("Test",2017,null));
    }
    //instructor give grade to someone not in class
    @Test
    public void testNotInClass2(){
        this.admin.createClass("Test",2017,"Instructor",50);
        this.student.registerForClass("Harjot","test",2017);
        this.instructor.addHomework("Instructor","Test",2017,"hw1","code");
        this.student.submitHomework("Harjot","hw1","ABC","test",2017);
        this.instructor.assignGrade("Instructor","Test",2017,"hw1","Harjot",90);
        boolean test = this.student.hasSubmitted("Harjot", "hw1","Test",2017);
        assertFalse(test);
    }
// test to check if the grade received is negative
    @Test
    public void testNegativeGrade3(){
        this.admin.createClass("Test",2017,"Instructor",50);
        this.student.registerForClass("Harjot","Test",2017);
        this.instructor.addHomework("Max","Test",2017,"hw1","code");
        this.student.submitHomework("Harjot","hw1","ABC","Test",2017);
        this.instructor.assignGrade("Max","Test",2017,"hw1","Harjot",-1);
        Integer i = new Integer(this.instructor.getGrade("Test", 2017,"hw1","Harjot"));
        assertFalse(i.equals(-1));
    }

//test not submitted
    @Test
        public void testNotSubmitted(){
        this.admin.createClass("Test",2017,"instructor",5);
        this.student.registerForClass("student1","Test",2017);
        this.instructor.addHomework("instructor","Test",2017,"hw1","code");
        this.instructor.assignGrade("instructor","Test",2017,"hw1","student1",100);
        Integer i = new Integer(this.instructor.getGrade("Test", 2017,"hw1","student1"));
        assertFalse(i.equals(100));
}
// test of instructor adding homework to a class he does not teach
    @Test
    public void testNotYourClass() {
        this.admin.createClass("ecs10",2017,"instructor",50);
        this.instructor.addHomework("Butner", "ecs10",2017, "hw1", "code");
        assertEquals("Butner", this.admin.getClassInstructor("ecs10", 2017));
    }
//test to see homework added not turned in NOT A BUG
    @Test
    public void case4() {
        this.admin.createClass("Test", 2017, "instructor", 15);
        this.student.registerForClass("harjot","Test",2017);
        this.student.registerForClass("jagdeep","Test",2017);
        this.instructor.addHomework("instructor","Test", 2017, "hw1", "code");
        this.instructor.assignGrade("instructor", "Test", 2017, "hw1", "harjot",100);
        assertTrue(this.instructor.getGrade("Test", 2017,"hw1","harjot") == 100);
    }
// test for graded homework
    @Test
    public void case3() {
        this.admin.createClass("Test", 2017, "instructor", 15);
        this.student.registerForClass("harjot","Test",2017);
        this.student.registerForClass("jagdeep","Test",2017);
        this.student.submitHomework("harjot", "hw1","ABC", "Test", 2017);
        this.instructor.assignGrade("Teach", "Test", 2017, "hw1", "harjot",100);
        assertTrue(this.instructor.getGrade("Test", 2017,"hw1","harjot") == 100);
    }
// test for homework graded by wrong instructor
    @Test
    public void case2() {
        this.admin.createClass("Test", 2017, "instructor", 15);
        this.student.registerForClass("harjot","Test",2017);
        this.student.registerForClass("jagdeep","Test",2017);
        this.instructor.addHomework("Teach","Test", 2017, "HW1", "Do it");
        this.student.submitHomework("harjot", "hw1","ABC", "Test", 2017);
        this.instructor.assignGrade("instructor", "Test", 2017, "hw1", "harjot",100);
        assertTrue(this.instructor.getGrade("Test", 2017,"hw1","harjot") == 100);
    }








}