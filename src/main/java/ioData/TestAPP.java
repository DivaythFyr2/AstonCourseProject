package ioData;

public class TestAPP {
    public static void main(String[] args) {
        String src = "src/main/resources/Manufactures/Car Manufacturers.txt";
        String dst = "src/main/resources/External/2.txt";
        String fileName = "src/main/resources/Manufactures/Book Manufactures.txt";


//        List<String> list = IOManager.readDataFromTXTFileToList(src);
//        IOManager.writeDataToTXTFile("list", dst);
//        IOManager.writeDataToTXTFile("OK", dst);
//        System.out.println(IOManager.readDataFromTXTFileToString(fileName));
//        String[] strings = IOManager.readDataFromTXTFileToArray(fileName);
//        System.out.println(Arrays.toString(strings));
//        System.out.println(Arrays.deepToString(IOManager.readDataFromTXTFileTo2DArray(fileName)));
//        System.out.println(IOManager.writeDataToTXTFile(IOManager.readDataFromTXTFileToString(src), dst));
//        System.out.println(IOManager.getBooksNamesToList());
//        System.out.println(Arrays.deepToString(IOManager.readDataFromTXTFileTo2DArray(fileName)));
//        IOManager.writeDataToTXTFile("Русский язык", dst);
//        System.out.println(list);
//        System.out.println(IOManager.readDataFromTXTFileToStringTEST(fileName));
//        System.out.println(IOManager.getVegetablesNamesToList());
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (Math.random() * 5)+1);
        }
    }
}
