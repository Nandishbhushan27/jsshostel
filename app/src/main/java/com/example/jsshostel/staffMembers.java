package com.example.jsshostel;

public class staffMembers {

    String name, role;
    long age;

    public staffMembers(){

    }

    public staffMembers(String name, String role, long age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}




