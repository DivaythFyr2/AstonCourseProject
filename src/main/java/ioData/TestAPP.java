package ioData;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;

public class TestAPP {
    public static void main(String[] args) {
        String src = "src/main/resources/Manufactures/Car Manufacturers.txt";
        String dst = "src/main/resources/External/output.txt";
        String fileName = "src/main/resources/Manufactures/Book Manufactures.txt";
        String CARS_EXTERNAL_FILE = "src/main/resources/External/Cars.txt";
        String CARS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Car Manufacturers.txt";
        String BOOKS_EXTERNAL_FILE = "src/main/resources/External/Books.txt";
        String BOOKS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Book Manufactures.txt";
        String VEGETABLES_EXTERNAL_FILE = "src/main/resources/External/RootVegetables.txt";
        String VEGETABLES_MANUFACTURES_FILE = "src/main/resources/Manufactures/Root vegetable Manufactures.txt";
        String CARS_EXTERNAL_OUTPUT_FILE = "src/main/resources/External/CarsOutput.txt";



        List<RootVegetable> rootVegetableList = RootVegetablesCreatorUtil.addRandomsRootVegetables(3);
        System.out.println(RootVegetablesCreatorUtil.appendRootVegetableListInTXTFile(rootVegetableList));
//
//        List<Book> books = BookCreatorUtil.addRandomsBooks(2);
//        System.out.println(BookCreatorUtil.appendBookListInTXTFile(books));
//        List<Car> carList = CarCreatorUtil.addRandomsCars(3);
//        System.out.println(CarCreatorUtil.appendCarListInTXTFile(carList));


//        IOManager.appendStringToTXTFile(CARS_EXTERNAL_OUTPUT_FILE, "text111");


        //        CarCreatorUtil.addCarsFromTXTFile();
//        CarCreatorUtil.addRandomsCars(3);
//        BookCreatorUtil.addBooksFromTXTFile();
//        BookCreatorUtil.addRandomsBooks(3);
//        RootVegetablesCreatorUtil.addRootVegetablesFromTXTFile();
//        RootVegetablesCreatorUtil.addRandomsRootVegetables(3);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(CARS_EXTERNAL_FILE);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(CARS_MANUFACTURES_FILE);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(BOOKS_EXTERNAL_FILE);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(BOOKS_MANUFACTURES_FILE);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(VEGETABLES_EXTERNAL_FILE);
//        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(VEGETABLES_MANUFACTURES_FILE);
//        System.out.println(Arrays.deepToString(arr2D));
//        System.out.println("=========================================");
//
//        List<String> stringList = IOManager.getBookAuthorsNames();
//        stringList.forEach(System.out::println);
//
//        String name1 = arr2D[1][1];
//        String name2 = stringList.get(1);
//
//        System.out.println("name1 = " + name1);
//        System.out.println("name2 = " + name2);
//        System.out.println(name1.equals(name2) );
//
//        System.out.println(ValidationUtils.checkString(name1, IOManager.getBookAuthorsNames()));
//
//
//        List<String> stringList = new ArrayList<>();
//
//        for (int i=0; i<10; i++){
//            stringList.add("Text" +i);
//        }
//        System.out.println(IOManager.appendDataToTXTFile(dst, stringList));
    }
}