//package assignment;
//
//import com.fasterxml.jackson.databind.MappingIterator;
//import com.fasterxml.jackson.dataformat.csv.*;
//
//import java.io.*;
//import java.util.*;
//
//public class CSVFileHandler implements MyFileHandler {
//    private final CsvMapper mapper = new CsvMapper();
//
//    @Override
//    public void read(String filePath, MyCollection collection) throws IOException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
//
//        if (inputStream == null) {
//            throw new FileNotFoundException("File not found in resources: " + filePath);
//        }
//
//        // Explicitly define the schema to ensure correct column order
//        CsvSchema schema = CsvSchema.builder()
//                .addColumn("firstName", CsvSchema.ColumnType.STRING)
//                .addColumn("lastName", CsvSchema.ColumnType.STRING)
//                .addColumn("dateOfBirth", CsvSchema.ColumnType.STRING) // Read as STRING first
//                .addColumn("experience", CsvSchema.ColumnType.NUMBER)
//                .setUseHeader(true) // Ensure the first row is treated as a header
//                .build();
//
//        MappingIterator<Employee> iterator = mapper.readerFor(Employee.class)
//                .with(schema)
//                .readValues(inputStream);
//
//        while (iterator.hasNext()) {
//            Employee emp = iterator.next();
//            collection.add(emp);
//        }
//    }
//
//    @Override
//    public void write(String filePath, MyCollection collection) throws IOException {
//        CsvSchema schema = mapper.schemaFor(Employee.class).withHeader();
//
//        // Convert MyCollection to List<Employee>
//        List<Employee> employeeList = new ArrayList<>();
//        for (int i = 0; i < 300; i++) {
//            Employee emp = collection.get();
//            if (emp != null) {
//                employeeList.add(emp);
//            }
//        }
//
//        // Fix: Pass a list, not MyCollection
//        mapper.writer(schema).writeValue(new File(filePath), employeeList);
//    }
//}


package assignment;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.*;

import java.io.*;
import java.util.*;

public class CSVFileHandler implements MyFileHandler {
    private final CsvMapper mapper = new CsvMapper();

    @Override
    public void read(String filePath, MyCollection collection) throws IOException {
        File file = new File(filePath); // FIX: Read from an external file
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        CsvSchema schema = CsvSchema.builder()
                .addColumn("firstName", CsvSchema.ColumnType.STRING)
                .addColumn("lastName", CsvSchema.ColumnType.STRING)
                .addColumn("dateOfBirth", CsvSchema.ColumnType.STRING)
                .addColumn("experience", CsvSchema.ColumnType.NUMBER)
                .setUseHeader(true)
                .build();

        MappingIterator<Employee> iterator = mapper.readerFor(Employee.class).with(schema).readValues(file);

        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            collection.add(emp);
        }
    }

    @Override
    public void write(String filePath, MyCollection collection) throws IOException {
        CsvSchema schema = mapper.schemaFor(Employee.class).withHeader();
        List<Employee> employeeList = new ArrayList<>();

        while (employeeList.size() < 100) {
            Employee emp = collection.get();
            if (emp != null) {
                employeeList.add(emp);
            }
        }

        mapper.writer(schema).writeValue(new File(filePath), employeeList);
    }
}
