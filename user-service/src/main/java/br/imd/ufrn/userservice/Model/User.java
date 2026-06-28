package br.imd.ufrn.userservice.Model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private @Id
    @GeneratedValue long id;
    private String name;
    private int age;
    private String city;
    private String motherName;
    private String fatherName;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age, String city, String motherName, String fatherName) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.motherName = motherName;
        this.fatherName = fatherName;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getCity() {
        return this.city;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
        return true;
        if (!(o instanceof User))
        return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
            && Objects.equals(this.age, user.age)
            && Objects.equals(this.city, user.city)
            && Objects.equals(this.motherName, user.motherName)
            && Objects.equals(this.fatherName, user.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.age, this.city, this.motherName, this.fatherName);
    }

    @Override
    public String toString() {
        return "Usuário{" + "id=" + this.id + ", nome='" + this.name + '\'' + ", idade='" + this.age + '\'' + ", cidade='" + this.city + '\'' + ", nome da mãe='" + this.motherName + '\'' + ", nome do pai='" + this.fatherName + '\'' + '}';
    }


}
