package guestbook;

public class Guestbook {
    public static String filterString(String input) {
        input = replaceText(input, '^', ' ');
        input = replaceText(input, (char)13, ' ');
        input = replaceText(input, (char)10, ' ');
        return input;        
    }

    private static String replaceText(String inString, char oldChar,
        char newChar) {

        while (inString.indexOf(oldChar) != -1) {
            int oldPosition = inString.indexOf(oldChar);
            StringBuffer data = new StringBuffer(inString);
            data.setCharAt(oldPosition, newChar);
            inString = data.toString();
        }
        return inString;
    }
}
