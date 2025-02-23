//package assignment;
//
//import com.fasterxml.jackson.dataformat.xml.*;
//
//import java.io.*;
//import java.util.*;
//
//class XMLFileHandler implements MyFileHandler {
//    private final XmlMapper mapper = new XmlMapper();
//
//    @Override
//    public void read(String filePath, MyCollection collection) throws IOException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
//
//        if (inputStream == null) {
//            throw new FileNotFoundException("File not found in resources: " + filePath);
//        }
//
//        Employee[] employees = new XmlMapper().readValue(inputStream, Employee[].class);
//        for (Employee emp : employees) {
//            collection.add(emp);
//        }
//    }
//
//
//    @Override
//    public void write(String filePath, MyCollection collection) throws IOException {
//        List<Employee> employeeList = new ArrayList<>();
//        for (int i = 0; i < 300; i++) {
//            Employee emp = collection.get();
//            if (emp != null) {
//                employeeList.add(emp);
//            }
//        }
//        mapper.writeValue(new File(filePath), employeeList);
//    }
//}


package assignment;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.*;

public class XMLFileHandler implements MyFileHandler {
    private final XmlMapper mapper = new XmlMapper();

    @Override
    public void read(String filePath, MyCollection collection) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        Employee[] employees = mapper.readValue(file, Employee[].class);
        for (Employee emp : employees) {
            collection.add(emp);
        }
    }

    @Override
    public void write(String filePath, MyCollection collection) throws IOException {
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print XML
        List<Employee> employeeList = new ArrayList<>();

        while (employeeList.size() < 100) {
            Employee emp = collection.get();
            if (emp != null) {
                employeeList.add(emp);
            }
        }

        mapper.writeValue(new File(filePath), employeeList);
    }

//    @Override
//    public void write(String filePath, MyCollection collection) throws IOException {
//        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print XML
//        mapper.writeValue(new File(filePath), collection); // Write MyCollection directly
//    }

//    @Override
//    public void write(String filePath, MyCollection collection) throws IOException {
//        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print XML
//        MyCollection limitedCollection = new MyCollection();
//
//        while (limitedCollection.getWriteCounter() < 100) {
//            Employee emp = collection.get();
//            if (emp != null) {
//                limitedCollection.add(emp);
//            }
//        }
//
//        mapper.writeValue(new File(filePath), limitedCollection); // Write the wrapped object
//    }

}
