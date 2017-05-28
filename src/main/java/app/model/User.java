package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * This class represents a user in the database.
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") }
        )
public class User {
    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * Teh username of the user.
     */
    @Column(name = "username")
    private String username;
    /**
     * The email address of the user.
     */
    @Column(name = "email")
    private String email;
    /**
     * The firstname of the user.
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * The lastname of the user.
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * The password of the user.
     */
    @Column(name = "password")
    private String password;
    /**
     * The date of born of the user.
     */
    @Column(name = "born_date")
    private LocalDate bornDate;
    /**
     * The address of the user.
     */
    @Column(name = "address")
    private String address;
    /**
     * The company of the user.
     */
    @Column(name = "company")
    private String company;
    /**
     * The type of the user.
     */
    @Column(name = "type")
    private String type;
    /**
     * The {@link app.model.Project Projects} what belongs to the user.
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private Set<Project> projects = new HashSet<Project>(0);

    /**
     * This method returns the id of the user.
     * @return The id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the user.
     * @param id The id to be set as the user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the username.
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the username of the user.
     * @param username The username to be set as the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email of the user.
     * @return the email of teh user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the firstname of the user.
     * @return the firstname of the user
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the firstname of the user.
     * @param firstName the firstname to be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Returns the lastname of the user.
     * @return thelastname of the user
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastname of the user.
     * @param lastName the lastname to be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the password of the user.
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the date of born of the user.
     * @return the date of born of the user
     */
    public LocalDate getBornDate() {
        return bornDate;
    }

    /**
     * Sets the date of born of the user.
     * @param bornDate the date of born to be set
     */
    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    /**
     * Returns the address of the user.
     * @return the address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the {@code Address} of the user.
     * @param address The {@code Address} to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns the company of the user.
     * @return The company of ther user
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method sets the company of the user.
     * @param company The company to be set as the company of the user
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method returns the type of the user.
     * @return the type of the user
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets the type of the user.
     * @param type The type to be set as the user's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the projects of the user.
     * @return the projects of the user
     */

    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * This method sets the projects for the user.
     * @param projects The projects to be set as the user's projects
     */
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
