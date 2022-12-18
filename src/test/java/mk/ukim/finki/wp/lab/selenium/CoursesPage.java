package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Getter
public class CoursesPage extends AbstractPage{

    @FindBy(css = "tr[class=course]")
    private List<WebElement> courseRows;

    @FindBy(css = ".delete-course")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-course")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-course")
    private List<WebElement> addCourseButton;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public void assertElemts(int coursesNumber, int deleteButtons, int editButtons,  int addButtons) {
        Assert.assertEquals("rows do not match", coursesNumber, this.getCourseRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddCourseButton().size());
    }

}
