package pages;

import baseClass.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Администратор
 * @date 15.08.2022
 */

public class MainYandexMarketPage extends BasePageClass {
    /**
     * автор Сергей Костенко
     * поле кнопки каталог
     */
    protected By btnCatalog = By.xpath("//button[@id='catalogPopupButton']");
    /**
     * автор Сергей Костенко
     * кнопка поиска
     */
    private By btnSearch = By.xpath("//span[text()='Найти']");
    /**
     * автор Сергей Костенко
     * поле поиска для ввода данных
     */
    private By fieldSearch = By.xpath("//input[@id='header-search']");


    public MainYandexMarketPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * метод проводит нажатие на кнопку на сайте Яндекс Маркет
     */
    public void goToCatalog() {
        driver.findElement(btnCatalog).click();
    }

    /**
     * автор Сергей Костенко
     * метод принимает текст для ввода в поле поиска и нажимает на кнопку поиска
     * @param name принимает текст для поиска
     */
    public void fillSearchField(String name) {
        driver.findElement(fieldSearch).click();
        driver.findElement(fieldSearch).sendKeys(name);
        driver.findElement(btnSearch).click();
        String expectedElementXpath = String.format("//li[@itemprop='itemListElement']//a[@title='%s']", name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expectedElementXpath)));
    }
}
