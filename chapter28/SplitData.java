import java.util.regex.*;

public class SplitData {
    String[] input = { "320/10.50/Dec 11 2002/39.95",
        "110-4.25-Dec 15 2002-39.95",
        "8%54.00%Dec 4 2002%0" };

    public SplitData() {
        for (int i = 0; i < input.length; i++) {
            String[] piece = input[i].split("[-/%]");
            for (int j = 0; j < piece.length; j++)
                System.out.print(piece[j] + "\t");
            System.out.print("\n");
        }
    }

    public static void main(String[] arguments) {
        SplitData app = new SplitData();
    }
}