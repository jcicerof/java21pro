import java.util.regex.*;

public class ShowWords {
    
    public static void main(String[] arguments) {
        Pattern pattern = Pattern.compile("\\S+");
        Matcher matcher = pattern.matcher(arguments[0]);
        while (matcher.find())
            System.out.println("[" + matcher.group() + "]");
    }
}

        