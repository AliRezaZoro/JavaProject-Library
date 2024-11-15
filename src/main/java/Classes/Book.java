package Classes;


public class Book
{
    private final String title, author, ISBN;
    private boolean isBorrowed = false;

    protected Book (String title, String author, String ISBN)
    {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    protected String getTitle ()
    {
        return title;
    }

    protected String getAuthor ()
    {
        return author;
    }

    protected String getISBN ()
    {
        return ISBN;
    }

    protected boolean isBorrowed ()
    {
        return isBorrowed;
    }

    protected void borrowed ()
    {
        this.isBorrowed = true;
    }

    protected void returned ()
    {
        this.isBorrowed = false;
    }
}