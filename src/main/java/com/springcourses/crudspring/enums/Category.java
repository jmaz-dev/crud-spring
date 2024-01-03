package com.springcourses.crudspring.enums;

public enum Category {
    BACKEND("Back-end"), FRONTEND("Front-end"), FULLSTACK("Full-stack"), MOBILE("Mobile");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // ToString
    @Override
    public String toString() {
        return value;
    }

}
