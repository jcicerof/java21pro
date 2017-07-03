public class GiftShop2 {
   public static void main(String[] arguments) {
        Storefront2 store = new Storefront2();
        store.addItem("C01", "MUG", "9.99", "150", "FALSE");
        store.addItem("C02", "LG MUG", "12.99", "82", "TRUE");
        store.addItem("C03", "MOUSEPAD", "10.49", "800", "FALSE");
        store.addItem("D01", "T SHIRT", "16.99", "90", "TRUE");
        store.sort();
        
        for (int i = 0; i < store.getSize(); i++) {
            Item2 show = (Item2)store.getItem(i);
            System.out.println("\nItem ID: " + show.getId() +
                "\nName: " + show.getName() +
                "\nRetail Price: $" + show.getRetail() +
                "\nPrice: $" + show.getPrice() +
                "\nQuantity: " + show.getQuantity());
        }
    }
}
 
