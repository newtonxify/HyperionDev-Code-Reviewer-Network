import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*The program to check if the given ISBN number
* is correct or not. If the ISBN number is correct,
* the program will out a "valid statement,
* if it is incorrect, the program will output an invalid statement*/

// The class name for ISBN 10
class ISBN10
{
    // isbn static initialization
    private static String isbn10;

    // The constructor to initialize isbn String parameter
    public ISBN10(String isbn10){
        this.isbn10 = isbn10;
    }
    // The boolean method to validate the ISBN
    static boolean isValidISBN10(String isbn)
    {
        // first check if the length is 10  digits
        int n = isbn.length();
        if (n != 10)
            return false;

        // calculate weighted sum of the first 9 digits
        int sum = 0;
        for (int i = 0; i < 9; i++)
        {
            int digit = isbn.charAt(i) - '0';
            if (0 > digit || 9 < digit)
                return false;
            sum += (digit * (10 - i));
        }

        // validate the last digit
        char last = isbn.charAt(9);
        if (last != 'X' && (last < '0' ||
                last > '9'))
            return false;

        // If last digit is 'X', add 10
        // to sum, else add its value
        sum += ((last == 'X') ? 10 : (last - '0'));

        // Return true if weighted sum
        // of digits is divisible by 11.
        return (sum % 11 == 0);
    }
    // Print the message to the console
    // if ISBN10 is valid print Valid
    // if ISBN10 is invalid print Invalid
    public static String printMessage10(){
        if (isValidISBN10(isbn10))
            System.out.print("Valid");
        else
            System.out.print("Invalid");
        return isbn10;
    }
}

// The class name for ISBN 10
class ISBN13{
    private static String isbn13;
    // The constructor to initialize isbn String parameter
    public ISBN13(String isbn13){
        this.isbn13 = isbn13;
    }
    // CALCULATE THE SUM OF THE ISBN
    private static int getSum(long isbn13) {
        int count = 0;
        int sum = 0;
        do {
            sum += count % 2 == 0 ? isbn13 % 10 : 3 * (isbn13 % 10);
            count++;
            isbn13 /= 10;
        } while (isbn13 > 0);
        return sum;
    }
	
    private static boolean isAValidISBN13(long isbn13) {
        return getSum(isbn13) % 10 == 0;
    }

    // Print the message to the console
    // if ISBN13 is valid print Valid
    // if ISBN13 is invalid print Invalid
    public static String printMessage13(){
        if (isbn13.length() != 13) {
            System.out.println("invalid");
        }
        else if (isAValidISBN13(Long.parseLong(isbn13))){
            System.out.println(isbn13 + "valid");
        }
        else {
            System.out.println(isbn13 + " Invalid");
        }
        return isbn13;
    }
}

// The test class for ISBN 10 implementation
// output the validity of the ISBN
class Test_Suite {
    String isbn10 = "007462542X";
    
    ISBN10 theISBN10 = new ISBN10(isbn10);
    @Test
    public void testPrintMessage() {
        assertEquals(isbn10, theISBN10.printMessage10());
    }
}
// The test class for ISBN 13 implementation
// output the validity of the ISBN
class Test_Suite13 {
    String isbn13 = "007462542";

    ISBN13 theISBN13 = new ISBN13(isbn13);
    @Test
    public void testPrintMessage() {
        //System.out.println("Your ISBN13 is: "+isbn13+" and is");
        assertEquals(isbn13, ISBN13.printMessage13());
    }
}
