package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * This class represents a user in the database
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") }
        )
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "born_date")
    private LocalDate bornDate;
    @Column(name = "address")
    private String address;
    @Column(name = "company")
    private String company;
    @Column(name = "type")
    private String type;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private Set<Project> projects = new HashSet<Project>(0);

    /**
     * This method returns the id of the user
     * @return The id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the user
     * @param id The id to be set as the user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the username of the user
     * @param username The username to be set as the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     *
     * @return
     */

    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public LocalDate getBornDate() {
        return bornDate;
    }

    /**
     *
     * @param bornDate
     */
    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns the company of the user
     * @return The company of ther user
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method sets the company of the user
     * @param company The company to be set as the company of the user
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method returns the type of the user
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets the type of the user
     * @param type The type to be set as the user's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the projects of the user
     * @return
     */

    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * This method sets the projects for the user
     * @param projects The projects to be set as the user's projects
     */
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
