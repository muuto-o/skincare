package ui;

import model.Routine;
import model.SkincareProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CLASS LEVEL COMMENT: creates the GUI for the edit product pop
public class EditProductGUI extends JFrame implements ActionListener {
    private Routine thisRoutine;
    private IndiRoutineGUI irgScreen;
    private SkincareProduct product;
    private JButton submit;
    private JPanel panel;
    private JTextField brandField;
    private JTextField typeField;
    private JTextField nameField;
    private JLabel brandL;
    private JLabel typeL;
    private JLabel nameL;
    private JLabel heading;

    // MODIFIES: this
    // EFFECTS: spawns the pop-up screen to edit products
    EditProductGUI(Routine r, SkincareProduct p, IndiRoutineGUI irg) {
        this.thisRoutine = r;
        this.irgScreen = irg;
        this.product = p;
        createPanel();
        this.setTitle("");
        this.setTitle("Reorder a product");
        this.setSize(250, 800);
        this.setResizable(false);
        this.setVisible(true);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void createPanel() {
        this.panel = new JPanel();
        this.panel.setBackground(new Color(0xC5D0DF));
        this.panel.setLayout(new FlowLayout(1));

        heading = createHeading("Make edits as you want:");

        brandField = new JTextField();
        brandField.setPreferredSize(new Dimension(250, 40));
        brandField.setText(product.getProductBrand());
        brandL = createHeading("Product Brand: ");

        typeField = new JTextField();
        typeField.setPreferredSize(new Dimension(250, 40));
        typeField.setText(product.getProductType());
        typeL = createHeading("Product Type: ");

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 40));
        nameField.setText(product.getProductName());
        nameL = createHeading("Product Name: ");


        submit = new JButton();
        submit.setText("submit changes");
        submit.addActionListener(this);
        submit.setPreferredSize(new Dimension(100, 20));
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setVerticalTextPosition(JButton.BOTTOM);

        this.panel.add(heading);
        this.panel.add(brandL);
        this.panel.add(brandField);
        this.panel.add(typeL);
        this.panel.add(typeField);
        this.panel.add(nameL);
        this.panel.add(nameField);
        this.panel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: allows button events to have consequence. If x button is pushed, does y.
    @Override
    public void actionPerformed(ActionEvent e) {
        String brandinput = brandField.getText();
        String typeinput = typeField.getText();
        String nameinput = nameField.getText();
        product.setProductBrand(brandinput);
        product.setProductType(typeinput);
        product.setProductName(nameinput);
        this.dispose();
        irgScreen.dispose(); // makes new screen to update list
        new IndiRoutineGUI(thisRoutine);
    }

    // MODIFIES: this
    // EFFECTS: helper method to create JLabel headings
    public JLabel createHeading(String heading) {
        JLabel header = new JLabel(heading);
        header.setFont(new Font("Lucida Bright", Font.PLAIN, 15));
        header.setForeground(Color.DARK_GRAY);
        return header;
    }
}
