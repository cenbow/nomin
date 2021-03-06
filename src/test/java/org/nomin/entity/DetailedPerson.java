package org.nomin.entity;

import java.util.Date;
import java.util.List;
import static java.text.MessageFormat.*;

/**
 * Just represents a business entity.
 * @author Dmitry Dobrynin
 *         Created 13.04.2010 11:04:19
 */
public class DetailedPerson extends Person {
    private String description;
    private String educationName;
    private String educationDescription;

    public DetailedPerson() {}

    public DetailedPerson(String name, String lastName, Date birthDate, Gender gender, List<Child> children,
                          String description, String educationName, String educationDescription) {
        super(name, lastName, birthDate, gender, children);
        this.description = description;
        this.educationName = educationName;
        this.educationDescription = educationDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
    }

    public String toString() {
        return format("DetailedPerson [ name = {0} lastName = {1} birthDate = {2} gender = {3} children = {4} strDate = {5} description = {6} educationName = {7} educationDescription = {8}]",
                getName(), getLastName(), getBirthDate(), getGender(), getChildren(), getStrDate(), description, educationName, educationDescription);
    }
}
