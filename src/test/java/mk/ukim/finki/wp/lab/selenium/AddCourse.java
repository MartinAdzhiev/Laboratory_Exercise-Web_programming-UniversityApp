package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddCourse extends AbstractPage{

    private WebElement name;
    private WebElement description;
    private WebElement teacher;
    private WebElement submit;

    public AddCourse(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage addCourse(WebDriver driver, String name, String description, String teacher) {
        get(driver, "/courses/add-course");
        AddCourse addCourse = PageFactory.initElements(driver, AddCourse.class);
        addCourse.name.sendKeys(name);
        addCourse.description.sendKeys(description);
        addCourse.teacher.click();
        addCourse.teacher.findElement(By.xpath("//option[. = '" + teacher + "']")).click();

        addCourse.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }



}
