package org.examen.examenmap.domain;

public class Client extends Entity<Long> {
    private String name;
    private int fidelityGrade;
    private int age;
    private ClientHobby hobby;

    public enum ClientHobby {
        reading,
        music,
        hiking,
        walking,
        extreme_sports
    }

    public Client(Long id, String name, int fidelityGrade, int age, ClientHobby hobby) {
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        this.hobby = hobby;
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(int fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClientHobby getHobby() {
        return hobby;
    }

    public void setHobby(ClientHobby hobby) {
        this.hobby = hobby;
    }
}
