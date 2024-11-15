package Classes;
import java.util.*;


public class BookCollection
{
    List <Book> library = new ArrayList <Book> ();

    public String addBook (String title, String author, String ISBN)
    {
        if (checkISBN(ISBN))
        {
            library.add (new Book (title, author, ISBN));
            return "Book \"" + title + "\" added successfully.";
        }

        else
            return "Wrong ISBN!";
    }

    public List <Book> retrieveBook ()
    {
        return library;
    }

    public String borrowBook (String ISBNParam)
    {
        int index = searchBook (ISBNParam);
        boolean flag;

        if (index == -1)
            return ("Book with ISBN: \"" + ISBNParam + "\" isn't found.");

        flag = library.get(index).isBorrowed ();

        if (!flag)
        {
            library.get(index).borrowed();
            return ("Book \"" + library.get(index).getTitle() + "\" with ISBN: \"" +
                    library.get(index).getISBN() + "\" is now borrowed.");
        }

        else
            return ("Book \"" + library.get(index).getTitle() + "\" with ISBN: \"" +
                    library.get(index).getISBN() + "\" is already borrowed.");
    }

    public String returnBook (String ISBNParam)
    {
        int index = searchBook (ISBNParam);
        boolean flag;

        if (index == -1)
            return ("Book with ISBN: \"" + ISBNParam + "\" isn't found.");

        flag = library.get(index).isBorrowed ();

        if (flag)
        {
            library.get(index).returned();
            return ("Book \"" + library.get(index).getTitle() + "\" with ISBN: \"" +
                    library.get(index).getISBN() + "\" is now returned.");
        }

        else
            return ("Book \"" + library.get(index).getTitle() + "\" with ISBN: \"" +
                    library.get(index).getISBN() + "\" is already in the library!");
    }

    public int searchBook (String ISBNParam)
    {
        int i;
        for (i = 0 ; i < library.size() ; i++)
            if (library.get(i).getISBN().equals(ISBNParam))
                return i;

        return -1;
    }

    private boolean checkISBN (String ISBNParam)
    {
        int i, r, sum = 0;

        for (i = 10 ; i >= 2 ; i--)
            sum += i*Character.getNumericValue(ISBNParam.charAt(10-i));

        r = 11-(sum%11);

        if ((Character.getNumericValue(ISBNParam.charAt(9)) == r) ||
                ((ISBNParam.charAt(9) == 'X' || ISBNParam.charAt(9) == 'x') && r == 10))
            return true;

        else
            return false;
    }
}