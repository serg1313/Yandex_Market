package helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {
    /**
     *автор Сергей Костенко
     * передает тестовые данные в тестовый метод
     * @return поток аргументов для считывания тестовым методом
     */
    public static Stream<Arguments> parameters() {
        List<String> brandList = Arrays.asList("Microsoft", "DELL");
        List<Integer> priceList = Arrays.asList(10000, 900000);
        return Stream.of(
                Arguments.of("Компьютеры", "Ноутбуки", priceList, brandList, 12)
        );
    }
}
