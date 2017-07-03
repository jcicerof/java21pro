import java.util.*;

public class Storefront2 {
    private LinkedList catalog = new LinkedList();

    public void addItem(String id, String name, String price,
        String quant, String discount) {

        Item2 it = new Item2(id, name, price, quant, discount);
        catalog.add(it);
    }

    public Item2 getItem(int i) {
        return (Item2)catalog.get(i);
    }

    public int getSize() {
        return catalog.size();
    }

    public void sort() {
        Collections.sort(catalog);
    }
}

