//package assignment;
//
//import com.fasterxml.jackson.databind.*;
//
//import java.io.*;
//import java.util.*;
//
//class JSONFileHandler implements MyFileHandler {
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public void read(String filePath, MyCollection collection) throws IOException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
//
//        if (inputStream == null) {
//            throw new FileNotFoundException("File not found in resources: " + filePath);
//        }
//
//        Employee[] employees = new ObjectMapper().readValue(inputStream, Employee[].class);
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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class JSONFileHandler implements MyFileHandler {
    private final ObjectMapper mapper = new ObjectMapper();

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
        List<Employee> employeeList = new ArrayList<>();

        while (employeeList.size() < 100) {
            Employee emp = collection.get();
            if (emp != null) {
                employeeList.add(emp);
            }
        }

        mapper.writeValue(new File(filePath), employeeList);
    }
}
