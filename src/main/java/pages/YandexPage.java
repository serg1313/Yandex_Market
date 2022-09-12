package pages;

import baseClass.BasePageClass;
import helper.constants.YandexServices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Администратор
 * @date 12.08.2022
 */

public class YandexPage extends BasePageClass {

    public YandexPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет переход из браузера Яндекса в указанный сервис
     */
    public void find(YandexServices ikon) {
        driver.findElement(By.xpath("//div[text()='"+ikon+"']/preceding-sibling::div")).click();
    }
}
