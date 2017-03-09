import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by HarjotSingh on 3/7/17.
 */
public class TestAdmin {
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
// this is a test to see if a class exists or not. Which is NOT a BUG
    @Test
    public void testIfClassExists(){
        this.admin.createClass("Test",2017,"Instructor",20);
        boolean exist = this.admin.classExists("Test",2017);
        assertTrue(exist);
    }
// this is a test in which we create a class in the future which is NOT a BUG
    @Test
    public void testCreateClassPresent() {
        this.admin.createClass("Class2", 2018, "Instructor", 50);
        assertTrue(this.admin.classExists("Class2", 2018));
}
// test to make sure student can join the class
    @Test
    public void testJoinClass(){
        this.admin.createClass("ECS20",2017,"Max",10);
        this.student.registerForClass("Harjot","ECS20",2017);
        assertTrue(this.student.isRegisteredFor("Harjot","ECS20",2017));
    }
// this is a test to create a class of a previous year
    @Test
    public void testCreateClass1(){
        this.admin.createClass("Test",2016,"Instructor",50);
        assertFalse(this.admin.classExists("Test",2016));
    }
// this is a test to create a class of capacity 0 which is a bug
    @Test
    public void testCreateClassCapacity2() {
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }
// this is a test to create a class of capacity of -1 which is a bug
    @Test
    public void testCreateClassCapacityNeg3(){
        this.admin.createClass("Test",2017,"Instructor",-1);
        // adding this line below
        this.admin.classExists("Test",2017);
        assertFalse(this.admin.getClassCapacity("Test",2017) < 0);
    }
// this is a test to see if an instructor teaches more than 2 classes
    @Test
    public void testIfSameProfessor4() {
        this.admin.createClass("ECS10", 2017, "Instructor", 15);
        this.admin.createClass("ECS20",2017,"Instructor",15);
        this.admin.createClass("ECS30",2017,"Instructor",15);
        boolean exist = this.admin.classExists("ECS10",2017)&&this.admin.classExists("ECS20",2017)&&this.admin.classExists("ECS30",2017);
        assertFalse(exist);
    }
// this is a test to make sure the class does not change capacity to a lower capacity
    @Test
    public void testChangeCapacityLower7() {
        this.admin.createClass("ECS10", 2017, "Instructor", 15);
        int currCapacity = this.admin.getClassCapacity("ECS10",2017);
        this.admin.changeCapacity("ECS10", 2017, 14);
        assertFalse(this.admin.getClassCapacity("ECS10", 2017) < currCapacity);
    }
// this is a test to have three students register for a class that only two can register for
    @Test
    public void testExtraStudent8(){
    this.admin.createClass("Test",2017,"Instructor",3);
    this.student.registerForClass("CHILD1","Test",2017);
    this.student.registerForClass("CHILD2","Test",2017);
    this.student.registerForClass("CHILD3","Test",2017);
    int temp = this.admin.getClassCapacity("Test",2017);
    this.admin.changeCapacity("Test",2017,2);
    assertFalse(this.admin.getClassCapacity("Test",2017) < temp);
    }
}

