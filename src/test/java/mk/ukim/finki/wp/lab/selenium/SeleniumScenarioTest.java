package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.converter.TeacherFullname;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {


    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    private HtmlUnitDriver driver;


    private static TeacherFullname tfn1;
    private static TeacherFullname tfn2;
    private static Teacher t1;
    private static Teacher t2;

    private static boolean dataInitialized = false;

    private static String admin = "admin";


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {
            tfn1 = new TeacherFullname();
            tfn1.setName("Stojko");
            tfn1.setSurname("Stojkov");

            tfn2 = new TeacherFullname();
            tfn2.setName("Trajko");
            tfn2.setSurname("Trajkov");

            t1 = teacherService.save(tfn1);
            t2 = teacherService.save(tfn2);

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursesPage coursesPage = CoursesPage.to(this.driver);
        coursesPage.assertElemts(0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        coursesPage = LoginPage.doLogin(this.driver, loginPage, admin, admin);
        coursesPage.assertElemts(0, 0, 0, 1);

        coursesPage = AddCourse.addCourse(this.driver, "Operating systems", "2year", t1.getTeacherFullname().toString());
        coursesPage.assertElemts(1, 1, 1, 1);

        coursesPage = AddCourse.addCourse(this.driver, "Web programming", "3year", t2.getTeacherFullname().toString());
        coursesPage.assertElemts(2, 2, 2, 1);

        coursesPage.getDeleteButtons().get(1).click();
        coursesPage.assertElemts(1, 1, 1, 1);


    }

}
