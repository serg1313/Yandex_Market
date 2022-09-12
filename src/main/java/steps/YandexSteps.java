package steps;

import baseClass.BasePageClass;
import helper.Assertions;
import helper.constants.YandexServices;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainYandexMarketPage;
import pages.MenuCatalogPage;
import pages.YandexMarketSearchPage;
import pages.YandexPage;

import java.util.List;

/**
 * @author Администратор
 * @date 12.08.2022
 */

public class YandexSteps extends BasePageClass {
    /**
     * автор Сергей Костенко
     * поле для хранения первого найденного элемента из списка
     */
    private String firstElement;
    /**
     * автор Сергей Костенко
     * поле объекта класса поисковика Яндекс Маркет
     */
    private YandexMarketSearchPage marketSearchPage;
    /**
     * автор Сергей Костенко
     * поле объекта класса главный экран Яндекс маркета
     */
    private MainYandexMarketPage mainYandexMarketPage;

    public YandexSteps(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет открытие Яндекс браузера
     * @param url
     */
    @DisplayName("Открываем главную страницу Яндекса")
    @Step("Открываем главную страницу {url}")
    public void openYandexPage(String url) {
        driver.get(url);
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет переход с страницы Яндекса на страницу Яндекс Маркета
     */
    @Step("Открываем Яндекс Маркет")
    public void goToYandexService(YandexServices ikon) {
        new YandexPage(driver).find(ikon);
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет переход в каталог товаров Яндекс Маркета
     */
    @Step("Переходим в каталог Яндекс Маркета")
    public void openCatalog() {
        mainYandexMarketPage = new MainYandexMarketPage(driver);
        mainYandexMarketPage.goToCatalog();
    }

    /**
     * автор Сергей Костенко
     * метод поочередно осуществляет выбор вида товара и подвида товара
     * @param section вид товара
     * @param subSection подвид товара
     */
    @Step("Переходим в раздел {section} и выбираем раздел {subSection}")
    public void openSectionAndSubSection(String section, String subSection) {
        new MenuCatalogPage(driver)
                .selectSection(section)
                .selectSubSection(subSection);
    }

    /**
     * автор Сергей Костенко
     * метод устанавливает фильтрацию стоимости товара ОТ и ДО
     * @param prices
     */
    @Step("Устанавливаем цену товара в диапазоне {prices}")
    public void setPrice(List<Integer> prices) {
        marketSearchPage = new YandexMarketSearchPage(driver);
        marketSearchPage.setPrice(prices);
    }

    /**
     * автор Сергей Костенко
     * метод производит выбор производителей товара по наименованию
     * @param brands наименование производителя
     */
    @Step("Выбираем производителей ноутбуков {brands}")
    public void setManufacturers(List<String> brands) {
        marketSearchPage.selectManufacturers(brands);
    }

    /**
     * автор Сергей Костенко
     * сохраняет первый товар из списка найденных товаров после произведенного поиска
     */
    @Step("Сохраняем первый ноутбук из списка выбранных компьютеров по заданным параметрам")
    public void saveFirstItem() {
        firstElement = marketSearchPage.getItemNameByIndex(0);
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет переход на главную страницу Яндекс маркета
     */
    @Step("Возврат на главную страницу Яндекс маркета")
    public void returnMainPageMarket() {
        marketSearchPage.openMainPage();
    }

    /**
     * автор Сергей Костенко
     * метод производит ввод данных найденного товара в поле поиска Яндекс маркета
     */
    @Step("Вводим данные выбранного ноутбука в поле поиск на главной странице Яндекс маркета")
    public void findFirstElementList() {
        mainYandexMarketPage.fillSearchField(firstElement);
    }

    /**
     * автор Сергей Костенко
     * получает список товаров в результате поиска и осуществляет сравнение на наличие искомого товара в списке
     */
    @Step("Проверяем что в результате ввода данных сохраненного товара" +
            " на главной странице Яндекс Маркета данный товар имеется в списке найденных товаров")
    public void comparisonListAfterSearch() {
        YandexMarketSearchPage yandexMarketSearchPage=new YandexMarketSearchPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(yandexMarketSearchPage.getItemHeaders()));
        Actions actions = new Actions(driver);
        for (int i = 0; i < 10; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        }
        List<String> foundItems = marketSearchPage.getFoundItemNames();
        helper.Assertions.assertTrue(foundItems.stream()
                .anyMatch(x -> x.equalsIgnoreCase(firstElement)), "Сохраненного элемента нет на странице поиска с данными параметрами: \n"+firstElement);
    }

    /**
     * автор Сергей Костенко
     * производит сравнение списка товаров по указанным характеристикам
     * @param brand имя производителя
     * @param prices стоимость
     */
    @Step("Проверить, что все товары соотвествуют фильтру {brand} и {prices} на нескольких страницах")
    public void checkFiltersApplyOnItems(List<String> brand, List<Integer> prices) {
        List<Integer> pricesList = marketSearchPage.getFoundItemPrices();
        List<String> namesList = marketSearchPage.getFoundItemNames();

        for(String item : namesList) {
            boolean isNameAsFilter = item.contains(brand.get(0).toLowerCase()) || item.contains(brand.get(1).toLowerCase());
            helper.Assertions.assertTrue(isNameAsFilter, item + " не подходит под именные фильтры");
        }

        for(Integer item: pricesList){
            boolean isPriceAsFilter = item >= prices.get(0) && item <= prices.get(1);
            helper.Assertions.assertTrue(isPriceAsFilter, item + " не подходит по ценовые фильтры");
        }
    }

    /**
     * автор Сергей Костенко
     * осуществляет сравнение количества товаров в списке на указанное минимальное значчение
     * @param expectedCount
     */
    @Step("Проверить, что на странице отображалось {expectedCount} элементов ")
    public void checkCountFoundElements(int expectedCount) {
        int actualCount = marketSearchPage.getFoundItemsCount();
        Assertions.assertTrue(actualCount > expectedCount,
                "Количество элементов в выборке не соответствует заданному значению " + actualCount);
    }
}






