package pages;

import baseClass.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Администратор
 * @date 15.08.2022
 */

public class YandexMarketSearchPage extends BasePageClass {
    /**
     * автор Сергей Костенко
     * кнопка очищения поля поиска производителей
     */
    private By btnClearBrand = By.xpath("//div[contains(@data-zone-data,'Производитель')]//button[@type='button' and @title='Очистить']");

    private By searchResultsInAllCategories = By.xpath("//a[contains(@title, 'Результаты поиска')]");
    /**
     * автор Сергей Костенко
     * поле кнопки показать всех производителей ноутбуков
     */
    private By btnShowAll = By.xpath("//span[text()='Показать всё']");
    /**
     * автор Сергей Костенко
     * поле для перехода на главную страницу маркета
     */
    private By fieldMainPageMarket = By.xpath("//a[(contains(@href,'kompiuternaia-tekhnika' )) and not(contains(text(),'Компьютеры'))]");
    /**
     * автор Сергей Костенко
     * поле поиска производителя для ввода данных
     */
    private By brandField = By.xpath("//label[text()='Найти производителя']//following::input[1]");
    /**
     * автор Сергей Костенко
     * поле с результатом количества найденных товаров
     */
    private By foundedItemsCount = By.xpath("//div[@data-grabber='SearchIntents']//span//span");

    /**
     * автор Сергей Костенко
     * поле описания товара
     */
    private By itemHeaders = By.xpath("//h3");

    /**
     * автор Сергей Костенко
     * поле стоимости товара
     */
    private By itemPrices = By.xpath("//div[@data-test-id='virtuoso-item-list']//article//a[@title]//following::span[@data-autotest-currency][1]");

    /**
     * автор Сергей Костенко
     * поля фильтрации товара по цене
     */
    private By priceFields = By.xpath("//div[@data-auto='filter-range-glprice']//following-sibling::input[1]");

    public YandexMarketSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * метод вносит в фильтр поиска ограниченние по стоимости товара ОТ и ДО заданные значения
     */
    public void setPrice(List<Integer> prices) {
        driver.findElements(priceFields).get(0).sendKeys(prices.get(0).toString());
        driver.findElements(priceFields).get(1).sendKeys(prices.get(1).toString());
    }

    /**
     * автор Сергей Костенко
     * Выбирает производителя из списка
     * @param brands список производителей
     */
    public void selectManufacturers(List<String> brands) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Показать всё']")));
        driver.findElement(btnShowAll).click();
        for (String brand : brands) {
            driver.findElement(brandField).sendKeys(brand);
            driver.findElement(By.xpath("//span[text()='" + brand + "']")).click();
            driver.findElement(btnClearBrand).click();
        }
    }

    /**
     * автор Сергей Костенко
     * Получает название товара по индексу
     * @param index индекс товара с нуля
     * @return
     */
    public String getItemNameByIndex(int index) {
        return driver.findElements(itemHeaders).get(index).getText();
    }

    /**
     * автор Сергей Костенко
     * метод нажимает на кнопку для перехода в главное меню Яндекс Маркета
     */
    public void openMainPage() {
        driver.findElement(fieldMainPageMarket).click();
    }

    /**
     * автор Сергей Костенко
     * Получает название товаров
     * @return Возвращает список с названиями найденных товаров
     */
    public List<String> getFoundItemNames(){
        return driver.findElements(itemHeaders).stream()
                .map(x->x.getText().toLowerCase()).collect(Collectors.toList());
    }

    /**
     * автор Сергей Костенко
     * Получает цены
     * @return Возвращает список c ценами у найденных товаров
     */
    public List<Integer> getFoundItemPrices(){
        return driver.findElements(itemPrices).stream()
                .map(x-> Integer.parseInt(x.getText().replaceAll(" ", "")
                        .replaceAll("₽", ""))).collect(Collectors.toList());
    }

    /**
     * Возвращает количество найденных товаров
     */
    public int getFoundItemsCount(){
        WebElement countElement = driver.findElement(foundedItemsCount);
        return Integer.parseInt(countElement.getText());
    }

    /**
     * автор Сергей Костенко
     * возвращает локатор элементов результата поиска
     * @return локатор xpath
     */
    public By getItemHeaders() {
        return itemHeaders;
    }
}
