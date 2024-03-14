package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

//CLASS LEVEL COMMENT: creates the GUI for the add product popup
public class AddGUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField brandField;
    private JTextField typeField;
    private JTextField nameField;
    private JLabel brandL;
    private JLabel typeL;
    private JLabel nameL;
    private JButton submit;

    private Routine thisRoutine;
    private IndiRoutineGUI irgScreen;

    // MODIFIES: this
    // EFFECTS: spawns the pop-up screen to add products to routine
    public AddGUI(Routine r, IndiRoutineGUI irg) {
        irgScreen = irg;
        thisRoutine = r;
        createPanel();
        this.setTitle("Add a product");
        this.setSize(400, 200);
        this.setVisible(true);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel in the GUI
    public void createPanel() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 200));
        this.panel.setBackground(new Color(0xC5D0DF));
        this.panel.setLayout(new FlowLayout(1));
        createFieldsButtons();

        this.panel.add(brandL);
        this.panel.add(brandField);
        this.panel.add(typeL);
        this.panel.add(typeField);
        this.panel.add(nameL);
        this.panel.add(nameField);
        this.panel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: creates the textfields and buttons for panel
    public void createFieldsButtons() {
        brandField = new JTextField();
        brandField.setPreferredSize(new Dimension(250, 40));
        brandL = createHeading("Product Brand: ");

        typeField = new JTextField();
        typeField.setPreferredSize(new Dimension(250, 40));
        typeL = createHeading("Product Type: ");

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 40));
        nameL = createHeading("Product Name: ");

        submit = new JButton();
        submit.setText("Add to routine");
        submit.addActionListener(this);
        submit.setPreferredSize(new Dimension(100, 20));
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setVerticalTextPosition(JButton.BOTTOM);
    }

    // MODIFIES: this
    // EFFECTS: allows button events to have consequence. If x button is pushed, does y.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String brandinput = brandField.getText();
            String typeinput = typeField.getText();
            String nameinput = nameField.getText();
            thisRoutine.addProduct(brandinput, typeinput, nameinput);
            this.dispose();
            irgScreen.dispose(); // makes new screen to update list
            new IndiRoutineGUI(thisRoutine);
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to create JLabel headings
    public JLabel createHeading(String heading) {
        JLabel header = new JLabel(heading);
        header.setFont(new Font("Calibri", Font.PLAIN, 15));
        header.setForeground(Color.DARK_GRAY);
        return header;
    }
}
