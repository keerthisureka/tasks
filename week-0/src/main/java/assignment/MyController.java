//package assignment;
//
//import java.io.IOException;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class MyController {
//    public static void main(String[] args) throws InterruptedException {
//        MyCollection collection = new MyCollection();
//
//        Thread csvReader = new Thread(() -> {
//            try {
//                new CSVFileHandler().read("employees.csv", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread xmlReader = new Thread(() -> {
//            try {
//                new XMLFileHandler().read("employees.xml", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread jsonReader = new Thread(() -> {
//            try {
//                new JSONFileHandler().read("employees.json", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        csvReader.start();
//        xmlReader.start();
//        jsonReader.start();
//
//        csvReader.join();
//        xmlReader.join();
//        jsonReader.join();
//
//        System.out.println("Write Counter: " + collection.getWriteCounter());
//        System.out.println("Read Counter: " + collection.getReadCounter());
//        for(int i = 0; i < 300; i++) {
//            Employee e = collection.employees[i];
//            System.out.println(e.getFirstName() + ", " + e.getLastName() + ", " + e.getDateOfBirth() + ", " + e.getExperience());
//            System.out.println();
//        }
//
//
//        Thread csvWriter = new Thread(() -> {
//            try {
//                new CSVFileHandler().write("output.csv", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread xmlWriter = new Thread(() -> {
//            try {
//                new XMLFileHandler().write("output.xml", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread jsonWriter = new Thread(() -> {
//            try {
//                new JSONFileHandler().write("output.json", collection);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        csvWriter.start();
//        xmlWriter.start();
//        jsonWriter.start();
//
//        csvWriter.join();
//        xmlWriter.join();
//        jsonWriter.join();
//
//        System.out.println("Write Counter: " + collection.getWriteCounter());
//        System.out.println("Read Counter: " + collection.getReadCounter());
//    }
//}


package assignment;

public class MyController {
    public static void main(String[] args) throws InterruptedException {
        MyCollection collection = new MyCollection();

        // **Reading Threads**
        Thread csvReader = new Thread(() -> {
            try {
                new CSVFileHandler().read("employees.csv", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonReader = new Thread(() -> {
            try {
                new JSONFileHandler().read("employees.json", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlReader = new Thread(() -> {
            try {
                new XMLFileHandler().read("employees.xml", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // **Start Readers in Parallel**
        csvReader.start();
        jsonReader.start();
        xmlReader.start();

        csvReader.join();
        jsonReader.join();
        xmlReader.join();

        System.out.println("Finished Reading. Total Records: " + collection.getWriteCounter()); // Should be 300

        // **Writing Threads**
        Thread csvWriter = new Thread(() -> {
            try {
                new CSVFileHandler().write("output.csv", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonWriter = new Thread(() -> {
            try {
                new JSONFileHandler().write("output.json", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlWriter = new Thread(() -> {
            try {
                new XMLFileHandler().write("output.xml", collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // **Start Writers in Parallel**
        csvWriter.start();
        jsonWriter.start();
        xmlWriter.start();

        csvWriter.join();
        jsonWriter.join();
        xmlWriter.join();

        System.out.println("Finished Writing. Total Records Written: " + collection.getReadCounter()); // Should be 300
    }
}
