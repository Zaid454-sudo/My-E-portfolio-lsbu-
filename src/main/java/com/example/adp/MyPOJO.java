package com.example.adp;
public class MyPOJO {
    private String firstName;
    private String lastName;
    private long idNumber;
    public MyPOJO(final String firstName,
                  final String lastName,
                  final long idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public long getIdNumber() {
        return this.idNumber;
    }
}
