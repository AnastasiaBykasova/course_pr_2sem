package com.example.course_project_5;

import com.example.course_project_5.models.Quote;
import com.example.course_project_5.models.Quotes;

public class StartClass {
    public static void main(String[] args) {
        Quotes q = new Quotes();
        q.getQuotes();
        //print all quotes
        for (Quote quote : q) {
            System.out.println(quote);
        }
    }
}
