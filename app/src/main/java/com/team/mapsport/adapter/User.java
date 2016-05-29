package com.team.mapsport.adapter;

public class User
{
    private long identifiant;
    private int cellNumber;
    private String pseudo;
    private String password;
    private String email;
    private String country;
    private String city;
    private String firstName;
    private String lastName;


    public User(String pseudo, String password, String email) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public int getCellNumber() {
        return cellNumber;
    }
    public void setCellNumber(int number) {
        this.cellNumber = number;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}
