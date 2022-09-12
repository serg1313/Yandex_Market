package test1;

import baseTest.BaseTest;
import helper.constants.YandexServices;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import helper.properties.Properties;
import steps.YandexSteps;

import java.util.List;

/**
 * @author Администратор
 * @date 12.08.2022
 */

public class YandexTests extends BaseTest {

    @Feature("Проверка поиска ноутбуков по заданным параметрам на сайте Яндекс Маркет")
    @DisplayName("Проверка поиска ноутбуков по заданным параметрам на сайте Яндекс Маркет")
    @MethodSource("helper.DataProvider#parameters")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    public void yandexSearchTest(String section, String subSection, List<Integer> prices, List<String> brands, Integer foundItems) {
        YandexSteps yandexSteps = new YandexSteps(driver);
        yandexSteps.openYandexPage(Properties.urlProperties.yandexExchangeUrl());
        yandexSteps.goToYandexService(YandexServices.Маркет);
        yandexSteps.openCatalog();
        yandexSteps.openSectionAndSubSection(section, subSection);
        yandexSteps.setPrice(prices);
        yandexSteps.setManufacturers(brands);
        yandexSteps.saveFirstItem();
        yandexSteps.checkCountFoundElements(foundItems);
        yandexSteps.checkFiltersApplyOnItems(brands, prices);
        yandexSteps.returnMainPageMarket();
        yandexSteps.findFirstElementList();
        yandexSteps.comparisonListAfterSearch();

    }
}


