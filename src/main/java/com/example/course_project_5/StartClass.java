package com.example.course_project_5;

public class StartClass {
    public static void main(String[] args) {
        try {
            System.out.println(new Connecting().regist_save_data("abc","abc"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
