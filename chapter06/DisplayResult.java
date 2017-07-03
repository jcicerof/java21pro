public class DisplayResult {
    public DisplayResult(String input) {
        try {
            float in = Float.parseFloat(input);
            Squared sq = new Squared(in);
            float result = sq.value;
           System.out.println("The square of " + input + " is " + result);
        } catch (NumberFormatException nfe) {
            System.out.println(input + " is not a valid number.");
        }
    }

    class Squared {
        float value;

        Squared(float x) {
            value = x * x;
        }
    }

    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java DisplayResult number");
        } else {
            DisplayResult dr = new DisplayResult(arguments[0]);
        }
    }
}
