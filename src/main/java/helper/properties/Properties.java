package helper.properties;


import helper.properties.interfaces.UrlProperties;
import org.aeonbits.owner.ConfigFactory;

public class Properties {
    /**
     * автор Сергей Костенко
     * поле для хранения значение url адреса считанного из properies файла
     */
    public static final UrlProperties urlProperties = ConfigFactory.create(UrlProperties.class);

}
