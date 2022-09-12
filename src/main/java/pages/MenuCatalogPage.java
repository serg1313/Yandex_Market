package pages;

import baseClass.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Администратор
 * @date 15.08.2022
 */

public class MenuCatalogPage extends BasePageClass {

    public MenuCatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * нажимает на указанную секцию в каталоге
     * @param name секция выбора
     * @return
     */
    public MenuCatalogPage selectSection(String name) {
        driver.findElement(By.xpath(String.format("//span[text()='%s']", name))).click();
        return this;
    }

    /**
     * автор Сергей Костенко
     * нажимает на указанную подсекцию в каталоге
     * @param name подсекция выбоа
     */
     public void selectSubSection(String name){
         driver.findElement(By.xpath(String.format("//a[text()='%s']", name))).click();
    }
}
