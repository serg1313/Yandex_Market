package baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Администратор
 * @date 12.08.2022
 */

public abstract class BasePageClass {
    /**
     * автор Сергей Костенко
     * поле вебдрайвера
     */
    protected WebDriver driver;
    /**
     * автор Сергей Костенко
     * поле явного ожидания
     */
    protected WebDriverWait wait;

    public BasePageClass(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, 40);
    }
}
