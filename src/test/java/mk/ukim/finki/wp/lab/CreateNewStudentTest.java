package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewStudentTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username", "password", "name", "surname", true);
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        this.studentService = Mockito.spy(new StudentServiceImplementation(this.studentRepository));
    }

    @Test
    public void testSuccessSave() {
        Student student = this.studentService.save("username", "password", "name", "surname", true);

        Mockito.verify(this.studentService).save("username", "password", "name", "surname", true);

        Assert.assertNotNull("Student is null", student);
        Assert.assertEquals("name do not mach", "name", student.getName());
        Assert.assertEquals("isNew do not match", true, student.isNew());
        Assert.assertEquals("surname do not mach", "surname", student.getSurname());
        Assert.assertEquals("password do not mach", "password", student.getPassword());
        Assert.assertEquals("username do not mach", "username", student.getUsername());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save(null, "password", "name", "surname", true));
        Mockito.verify(this.studentService).save(null, "password", "name", "surname", true);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save(username, "password", "name", "surname", true));
        Mockito.verify(this.studentService).save(username, "password", "name", "surname", true);
    }

    @Test
    public void testNullPassword() {
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", null, "name", "surname", true));
        Mockito.verify(this.studentService).save("username", null, "name", "surname", true);
    }

    @Test
    public void testEmptyPassword() {
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", password, "name", "surname", true));
        Mockito.verify(this.studentService).save("username", password, "name", "surname", true);
    }

    @Test
    public void testNullName() {
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password", null, "surname", true));
        Mockito.verify(this.studentService).save("username", "password", null, "surname", true);
    }

    @Test
    public void testEmptyName() {
        String name = "";
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password", name, "surname", true));
        Mockito.verify(this.studentService).save("username", "password", name, "surname", true);
    }

    @Test
    public void testNullSurname() {
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password", "name", null, true));
        Mockito.verify(this.studentService).save("username", "password", "name", null, true);
    }

    @Test
    public void testEmptySurname() {
        String surname = "";
        Assert.assertThrows("InvalidArgumentException expected",
                IllegalArgumentException.class,
                () -> this.studentService.save("username", "password", "name", surname, true));
        Mockito.verify(this.studentService).save("username", "password", "name", surname, true);
    }


}
