package assignment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

@JsonPropertyOrder({"firstName", "lastName", "dateOfBirth", "experience"}) // JSON & CSV field order
public class Employee {
    @JacksonXmlProperty(localName = "firstName")  // XML field order
    private String firstName;

    @JacksonXmlProperty(localName = "lastName")
    private String lastName;

    @JacksonXmlProperty(localName = "dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JacksonXmlProperty(localName = "experience")
    private double experience;

    // Default Constructor (Required for Jackson)
    public Employee() {}

    // Constructor
    public Employee(String firstName, String lastName, Date dateOfBirth, double experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }

    // Getters & Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public double getExperience() { return experience; }
    public void setExperience(double experience) { this.experience = experience; }
}
