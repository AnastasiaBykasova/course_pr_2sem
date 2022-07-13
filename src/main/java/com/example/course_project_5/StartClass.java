package com.example.course_project_5;

import com.example.course_project_5.helpers.PasswordHelper;
import com.example.course_project_5.models.Quote;
import com.example.course_project_5.models.QuoteWorker;
import com.example.course_project_5.models.Quotes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;

public class StartClass {
    public static void main(String[] args) {
        try {
            System.out.println(PasswordHelper.generateStorngPasswordHash("1"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
//        allQuote();
    }

    private static void printAllQuotes() {
        Quotes q = new Quotes();
        q.loadQuotes();
        //print all quotes
        for (Quote quote : q) {
            System.out.println(quote);
        }
    }
    private static void allQuote() {
        deleteQuote(changeQuote(addQuote()));
    }
    private static Quote addQuote() {
        Quote q = new Quote(-1,
                "Some text",
                new Date(System.currentTimeMillis()),
                "Some Subject",
                "Some Teacher",
                1,
                1);
        q = QuoteWorker.addQuote(q);
        System.out.println(q);
        return q;
    }

    private static Quote changeQuote(Quote quote) {
        Quote q = null;
        if (quote == null) {
            q = new Quote(2,
                    "Some text2",
                    new Date(System.currentTimeMillis()),
                    "Some Subject",
                    "Some Teacher",
                    1,
                    1);
        } else {
            q = quote;
            q.setText(quote.getText() + " 2");
        }
        System.out.println(QuoteWorker.updateQuote(q));
        return q;
    }

    private static void deleteQuote(Quote q) {
        System.out.println(QuoteWorker.deleteQuote(q.getId()));
    }

}
