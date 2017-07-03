import java.awt.*;

public class AddressForm extends java.applet.Applet {
    Label nameLabel = new Label("Name: ");
    TextField name = new TextField(40);
    Label addressLabel = new Label("Address: ");
    TextField address = new TextField(40);
    Label cityLabel = new Label("City: ");
    TextField city = new TextField(15);
    Label stateLabel = new Label("State");
    Choice state = new Choice();
    Label zipLabel = new Label("ZIP: ");
    TextField zip = new TextField(10);
    Button saveButton = new Button("Save");

    public void init() {
        // set up state list
        state.addItem("AL");
        state.addItem("FL");
        state.addItem("GA");
        state.addItem("NC");
        state.addItem("SC");
        
        // add components to applet window
        GridLayout grid = new GridLayout(4, 1);
        setLayout(grid);

        Panel row1 = new Panel();
        row1.add(nameLabel);
        row1.add(name);
        add(row1);

        Panel row2 = new Panel();
        row2.add(addressLabel);
        row2.add(address);
        add(row2);

        Panel row3 = new Panel();
        row3.add(cityLabel);
        row3.add(city);
        row3.add(stateLabel);
        row3.add(state);
        row3.add(zipLabel);
        row3.add(zip);
        add(row3);
        
        Panel row4 = new Panel();
        row4.add(saveButton);
        add(row4);
    }
        
    public boolean action(Event evt, Object obj) {
        if (evt.target == saveButton) {
            System.out.println("Name: " + name.getText());
            System.out.println("Address: " + address.getText());
           System.out.println("City: " + city.getText());
            System.out.println("State: " + state.getSelectedItem());
            System.out.println("ZIP: " + zip.getText());
        }
        return true;   
    }
}
