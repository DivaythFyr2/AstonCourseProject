package product;

import java.util.ArrayList;
import java.util.List;

/**
 * Возможно в дальнейшем этот класс можно будет как то оптимизировать для уменьшения количества кода.
 * Но на данный момент я вижу этот класс так!
 */

public class ProductUtils {
    private ProductUtils() {
    }

    /**
     * В этих методах дложна быть прописана логика валидации ввода!
     */
    static List<Product> createManualCarCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Toyota").setAutoPower("200").setAutoYearOfProduction("2020").build());
        System.out.println("createManualCarCollections - Done!");
        return carCollections;
    }

    static List<Product> createFromFileCarCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Toyota").setAutoPower("200").setAutoYearOfProduction("2020").build());
        System.out.println("createFromFileCarCollections - Done!");
        return carCollections;
    }

    static List<Product> createAutoCarCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Toyota").setAutoPower("200").setAutoYearOfProduction("2020").build());
        System.out.println("createAutoCarCollections - Done!");
        return carCollections;
    }

    static List<Product> createManualBookCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Война и мир").setBookAuthor("Лев Толстой").setBookNumberOfPages("1225").build());
        System.out.println("createManualBookCollections - Done!");
        return carCollections;
    }

    static List<Product> createFromFileBookCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Война и мир").setBookAuthor("Лев Толстой").setBookNumberOfPages("1225").build());
        System.out.println("createFromFileBookCollections - Done!");
        return carCollections;
    }

    static List<Product> createAutoBookCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Война и мир").setBookAuthor("Лев Толстой").setBookNumberOfPages("1225").build());
        System.out.println("createAutoBookCollections - Done!");
        return carCollections;
    }

    static List<Product> createManualRootVegetableCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Морковь").setRootVegetableWeight("0.2").setRootVegetableColor("Orange").build());
        System.out.println("createManualRootVegetableCollections - Done!");
        return carCollections;
    }

    static List<Product> createFromFileRootVegetableCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Морковь").setRootVegetableWeight("0.2").setRootVegetableColor("Orange").build());
        System.out.println("createFromFileRootVegetableCollections - Done!");
        return carCollections;
    }

    static List<Product> createAutoRootVegetableCollections() {
        List<Product> carCollections = new ArrayList<>();
        carCollections.add(new Product.ProductBuilder().setTitle("Морковь").setRootVegetableWeight("0.2").setRootVegetableColor("Orange").build());
        System.out.println("createAutoRootVegetableCollections - Done!");
        return carCollections;
    }
}
