package com.vworld4u.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
	@JsonProperty("Teacher")
	Teacher("Teacher"),
	@JsonProperty("Student")
	Student("Student");
	

    private final String name;       

    private UserType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
