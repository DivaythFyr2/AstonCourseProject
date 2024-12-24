package product;

import java.util.List;

/**
 * Здесь нужно будет вместо Comparable, унаследоваться от собственных интерфейсов
 * сортировки и поиска эллементов с использованием generics и паттерна "Стратегия".
 * Данный клас уже релизовывает паттерн Builder и является универсальным для всех
 * 3х продуктов!
 */

public class Product implements Comparable<Product> {
    private List<Product> products;
    private final String title;
    private final String autoYearOfProduction;
    private final String autoPower;
    private final String bookAuthor;
    private final String bookNumberOfPages;
    private final String rootVegetableWeight;
    private final String rootVegetableColor;

    public Product(ProductBuilder productBuilder) {
        title = productBuilder.title;
        autoYearOfProduction = productBuilder.autoYearOfProduction;
        autoPower = productBuilder.autoPower;
        bookAuthor = productBuilder.bookAuthor;
        bookNumberOfPages = productBuilder.bookNumberOfPages;
        rootVegetableWeight = productBuilder.rootVegetableWeight;
        rootVegetableColor = productBuilder.rootVegetableColor;
    }

    /*
    Тут (в нижних трех методах: manualCreation, CreatingFromAFile, AutoCreation)
    для того, чтобы не нагромаждать данный класс большим количеством функционала,
    нужно будет вместо выводов , прописать вызовы утилитных методов соответсвующего функционала!
     */
    public static void manualCreation(String type) {
        switch (type) {
            case "1":
                // Вызов утилитного метода по ручному заполнению коллекции машинами.
                List<Product> manualCarCollections = ProductUtils.createManualCarCollections();
                break;
            case "2":
                // Вызов утилитного метода по ручному заполнению коллекции книгами.
                List<Product> manualBookCollections = ProductUtils.createManualBookCollections();
                break;
            case "3":
                // Вызов утилитного метода по ручному заполнению коллекции корнеплодами.
                List<Product> manualRootVegetableCollections = ProductUtils.createManualRootVegetableCollections();
                break;
        }
    }

    public static void CreatingFromAFile(String type) {
        switch (type) {
            case "1":
                // Вызов утилитного метода для заполнения коллекции из файла машинами.
                List<Product> fromFileCarCollections = ProductUtils.createFromFileCarCollections();
                break;
            case "2":
                // Вызов утилитного метода для заполнения коллекции из файла книгами.
                List<Product> fromFileBookCollections = ProductUtils.createFromFileBookCollections();
                break;
            case "3":
                // Вызов утилитного метода для заполнения коллекции из файла корнеплодами.
                List<Product> fromFileRootVegetableCollections = ProductUtils.createFromFileRootVegetableCollections();
                break;
        }
    }

    public static void AutoCreation(String type) {
        switch (type) {
            case "1":
                // Вызов утилитного метода для автоматического заполнения коллекции машинами.
                List<Product> autoCarCollections = ProductUtils.createAutoCarCollections();
                break;
            case "2":
                // Вызов утилитного метода для автоматического заполнения коллекции книгами.
                List<Product> autoBookCollections = ProductUtils.createAutoBookCollections();
                break;
            case "3":
                // Вызов утилитного метода для автоматического заполнения коллекции корнелодами.
                List<Product> autoRootVegetableCollections = ProductUtils.createAutoRootVegetableCollections();
                break;
        }
    }

    // Тут нужно реализовать логику печати коллекции в консоль.
    public static void print() {
        System.out.println("Print");

    }

    @Override
    public int compareTo(Product product) {
        return 0;
    }

    public static class ProductBuilder {
        private String title;
        private String autoYearOfProduction;
        private String autoPower;
        private String bookAuthor;
        private String bookNumberOfPages;
        private String rootVegetableWeight;
        private String rootVegetableColor;

        public ProductBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder setAutoYearOfProduction(String autoYearOfProduction) {
            this.autoYearOfProduction = autoYearOfProduction;
            return this;
        }

        public ProductBuilder setAutoPower(String autoPower) {
            this.autoPower = autoPower;
            return this;
        }

        public ProductBuilder setBookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
            return this;
        }

        public ProductBuilder setBookNumberOfPages(String bookNumberOfPages) {
            this.bookNumberOfPages = bookNumberOfPages;
            return this;
        }

        public ProductBuilder setRootVegetableWeight(String rootVegetableWeight) {
            this.rootVegetableWeight = rootVegetableWeight;
            return this;
        }

        public ProductBuilder setRootVegetableColor(String rootVegetableColor) {
            this.rootVegetableColor = rootVegetableColor;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}