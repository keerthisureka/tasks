package assignment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.concurrent.atomic.AtomicInteger;

@JacksonXmlRootElement(localName = "Employees") // Root element in XML
public class MyCollection {
    @JacksonXmlElementWrapper(useWrapping = false) // Prevents unwanted <ArrayList> or <item>
    @JacksonXmlProperty(localName = "Employee") // Ensures correct child tag naming
    public Employee[] employees = new Employee[300];

    @JsonIgnore
    private int writeCounter = 0;
    @JsonIgnore
    private int readCounter = 0;

    //Getters
    public int getWriteCounter() {
        return writeCounter;
    }
    public int getReadCounter() {
        return readCounter;
    }

    // Methods
    public void add(Employee emp) {
        employees[writeCounter] = emp;
        writeCounter++;
    }

    public synchronized Employee get() {
        if (readCounter >= employees.length) return null; // Stop when all records are read

        Employee employee = employees[readCounter];
        readCounter++; // Move to the next record
        return employee;
    }
}