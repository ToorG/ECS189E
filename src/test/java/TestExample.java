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
 * Created by Vincent on 23/2/2017.
 */
public class TestExample {

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


    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClassPreviousClass1() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    /**
     * Created by Harjot 03/07/2017
     */

    // assigning grade to some student in class
    @Test
    public void testCheckStudentInClass() {
        this.admin.createClass("ECS50", 2017, "Butner", 50);
        this.student.registerForClass("Harjot", "ECS50", 2017);
        boolean registered = this.student.isRegisteredFor("Harjot", "ECS50", 2017);
        assertTrue(registered);
    }
        /*this.instructor.addHomework("Butner","ECS50",2017,"homework", "Test");
        this.student.submitHomework("Harjot","homework","random","ECS50",2017);
        this.instructor.assignGrade("Butner","ECS50",2017,"homework","Harjot",90);
        boolean registered = this.student.isRegisteredFor("Harjot","ECS50",2017);
        assertTrue(registered);*/

    // creating a class with a capacity of only 0 students
    @Test
    public void testCreateClassCapacity2() {
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }
/*
    @Test
    public testChangeCapacity(){
        this.admin.createClass("Test",2017,"Instructor",15);
        int currCapacity = this.admin.getClassCapacity("Test",2017);
        this.admin.createClass("Test",2017,"Instructor",14);
        assertTrue(this.admin.classExists("Test", 2017))>= currCapacity;

    }
    */
    // creating a class that is being taught by the same professor

    @Test
    public void testIfSameProfessor3() {
        this.admin.createClass("ECS10", 2017, "Instructor", 15);
        this.admin.createClass("ECS20",2017,"Instructor",15);
        this.admin.createClass("ECS30",2017,"Instructor",15);
        boolean exist = this.admin.classExists("ECS10",2017)&&this.admin.classExists("ECS20",2017)&&this.admin.classExists("ECS30",2017);
        assertFalse(exist);
    }

    // checking if a class exists NOT A BUG
    @Test
    public void testClassExists() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertFalse(!(this.admin.classExists("Test", 2017)));
    }

    // checking if a class exists with no instructor


    /*
        NOTES::
                - assertFalse for something that is supposed to be false, so if it is false  meaning it
                - assertTrue for something that is supposed to be true, so if it is not true meaning it does not exist then it throws a BUG
     */

    // checking if homework exists
    @Test
    public void testHomeworkExists(){
        this.admin.createClass("ECS20",2017,"Nelson",50);
        this.instructor.addHomework("Nelson","ECS20",2017,"HW1","homework1");
        assertTrue(this.instructor.homeworkExists("ECS20",2017,"HW1"));
    }
    // add homework to see if it exists, do True because it should have existed but if it does not exist then it returns a bug
    @Test
    public void testInstructorAssignHomework4(){
        this.instructor.addHomework("Sean","ecs60",2017,"defragmenter","code1");
        boolean exists = this.instructor.homeworkExists("ECS60",2017,"defragmenter");
        assertTrue(exists);
    }
    // test to check if a student from another class can turn in homework for another class
    @Test
    public void testIsRegisteredForCorrectClass(){
        this.admin.createClass("ecs60",2017,"Sean",50);
        this.student.registerForClass("Harjot","ecs60",2017);
        this.instructor.addHomework("Sean","ecs60",2017,"defragmenter","code1");
        boolean exists = this.student.hasSubmitted("Harjot","defragmenter","ecs60",2017);
        assertTrue(exists);
    }


}
