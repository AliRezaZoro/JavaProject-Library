package org.example;
import Classes.*;

import static spark.Spark.*;
import com.google.gson.*;


public class Main
{
    public static void main (String [] args)
    {
        BookCollection bookCollection = new BookCollection ();


        post ("/api/v1/addBook", (req, res) ->
        {
            res.type ("application/Json");

            String titleParam = req.queryParams ("Title");
            String authorParam = req.queryParams ("Author");
            String ISBNParam = req.queryParams ("ISBN");

            if (titleParam.isEmpty() || authorParam.isEmpty() || ISBNParam.isEmpty())
                return "Fulfill the list.";

            else
            {
                if (bookCollection.searchBook (ISBNParam) != -1)
                    return "Book \"" + titleParam + "\" already exists.";

                else
                    return bookCollection.addBook (titleParam, authorParam, ISBNParam);
            }
        });


        get ("/api/v1/getBooks", (req, res) ->
        {
            res.type ("application/Json");

            String ISBNParam = req.queryParams ("ISBN");

            if (ISBNParam == null || ISBNParam.isEmpty())
                return new Gson().toJson (bookCollection.retrieveBook());

            else
            {
                int index = bookCollection.searchBook (ISBNParam);

                if (index != -1)
                    return new Gson().toJson (bookCollection.retrieveBook().get(index));

                else
                {
                    res.status(404);
                    return "Book with ISBN: \"" + ISBNParam + "\" isn't found.";
                }
            }
        });


        get ("/api/v1/borrowBook", (req, res) ->
        {
            res.type ("application/Json");

            String ISBNParam = req.queryParams ("ISBN");

            return bookCollection.borrowBook(ISBNParam);
        });


        get ("/api/v1/returnBook", (req, res) ->
        {
            res.type ("application/Json");

            String ISBNParam = req.queryParams ("ISBN");

            return bookCollection.returnBook(ISBNParam);
        });
    }
}