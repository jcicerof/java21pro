import java.util.*;

public class FruitBasket {
    public static void main(String[] arguments) {
        Fruit apple = new Fruit("apple", 5, .75F);
        Fruit orange = new Fruit("orange", 3, .90F);
        Fruit banana = new Fruit("banana", 2, .55F);
        Vector cart = new Vector();
        cart.add(apple);
        cart.add(orange);
        cart.add(banana);
        Iterator it = cart.iterator();
        while (it.hasNext()) {
            Fruit fr = (Fruit)it.next();
            System.out.println(fr.name+ ", " + (fr.quantity*fr.price));
        }
    }
}

class Fruit {
    String name;
    int quantity;
    float price;

    Fruit(String inName, int inQuantity, float inPrice) {
        name = inName;
        quantity = inQuantity;
        price = inPrice;
    }
}
