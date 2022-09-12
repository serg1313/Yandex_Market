package helper.properties.interfaces;

import org.aeonbits.owner.Config;

/**
 * @author Администратор
 * @date 20.08.2022
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/url.properties"
})
public interface UrlProperties extends Config {
    /**
     * автор Сергей Костенко
     * поле для указания url страницы
     * @return
     */
    @Config.Key("yandex.url")
    String yandexExchangeUrl();
}
