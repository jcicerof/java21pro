import java.util.*;

public class NewComicBooks {

    public NewComicBooks() {
    }

    public static void main(String[] arguments) {
        // set up hashtable
        Hashtable quality = new Hashtable();
        Float price0 = new Float(5.00F);
        quality.put("pristine mint", price0);
        Float price1 = new Float(3.00F);
        quality.put("mint", price1);
        Float price2 = new Float(2.00F);
        quality.put("near mint", price2);
        Float price3 = new Float(1.50F);
        quality.put("very fine", price3);
        Float price4 = new Float(1.00F);
        quality.put("fine", price4);
        Float price5 = new Float(0.50F);
        quality.put("good", price5);
        Float price6 = new Float(0.25F);
        quality.put("poor", price6);
        Float price7 = new Float(0.10F);
        quality.put("coverless", price7);
        // set up collection
        Comic[] comix = new Comic[5];
        comix[0] = new Comic("Amazing Spider-Man", "1A", "very fine",
            5400.00F);
        comix[0].setPrice( (Float)quality.get(comix[0].condition) );
        comix[1] = new Comic("Incredible Hulk", "181", "near mint",
            770.00F);
        comix[1].setPrice( (Float)quality.get(comix[1].condition) );
        comix[2] = new Comic("Cerebus", "1A", "good", 260.00F);
        comix[2].setPrice( (Float)quality.get(comix[2].condition) );
        comix[3] = new Comic("Prez", "1", "pristine mint", 30.00F);
        comix[3].setPrice( (Float)quality.get(comix[3].condition) );
        comix[4] = new Comic("Night Nurse", "1", "coverless", 22.00F);
        comix[4].setPrice( (Float)quality.get(comix[4].condition) );
        for (int i = 0; i < comix.length; i++) {
            System.out.println("Title: " + comix[i].title);
            System.out.println("Issue: " + comix[i].issueNumber);
            System.out.println("Condition: " + comix[i].condition);
            System.out.println("Price: $" + comix[i].price + "\n");
        }
    }
}

class Comic {
    String title;
    String issueNumber;
    String condition;
    float basePrice;
    float price;

    Comic(String inTitle, String inIssueNumber, String inCondition,
        float inBasePrice) {
    
        title = inTitle;
        issueNumber = inIssueNumber;
        condition = inCondition;
        basePrice = inBasePrice;
    }

    void setPrice(Float factor) {
        float multiplier = factor.floatValue();
        price = basePrice * multiplier;
    }
}
