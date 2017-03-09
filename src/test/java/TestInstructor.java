import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;

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
