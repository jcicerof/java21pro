import java.util.regex.*;

public class Zipper {
    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java Zipper code");
            System.exit(0);
        }
        String input = arguments[0];
        String zip5 = "[0-9][0-9][0-9][0-9][0-9]";
        String zip9 = "[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
        if ((Pattern.matches(zip5, input)) | (Pattern.matches(zip9, input))) {
            
            System.out.println(input + " is a valid ZIPcode");
        } else {
            System.out.println(input + " is not a valid ZIPcode");
        }
    }
}
        