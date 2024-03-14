package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

//CLASS LEVEL COMMENT: creates the GUI for the select product popup
public class SelectGUI extends JFrame implements ActionListener {
    private Routine thisRoutine;
    private IndiRoutineGUI irgScreen;
    private JButton submit;
    private JPanel panel;
    private JTextField indexField;
    private JLabel indexL;
    private String action;

    // MODIFIES: this
    // EFFECTS: spawns the pop-up screen to select products
    public SelectGUI(Routine r, String action, IndiRoutineGUI irg) {
        this.setTitle("Select a product to " + action);
        this.action = action;
        this.thisRoutine = r;
        this.irgScreen = irg;
        createPanel();
        this.setTitle("Delete a product");
        this.setSize(700, 100);
        this.setVisible(true);
        this.add(panel);

    }

    // MODIFIES: this
    // EFFECTS: creates the main panel
    public void createPanel() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 200));
        this.panel.setBackground(new Color(0xC5D0DF));
        this.panel.setLayout(new FlowLayout(1));

        indexField = new JTextField();
        indexField.setPreferredSize(new Dimension(250, 40));
        indexL = createHeading("What is the STEP POSITION of the product you want to select?");

        submit = new JButton();
        submit.setText(action + " now");
        submit.addActionListener(this);
        submit.setPreferredSize(new Dimension(100, 20));
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setVerticalTextPosition(JButton.BOTTOM);

        this.panel.add(indexL);
        this.panel.add(indexField);
        this.panel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: helper method to create JLabel headings
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                if (action.equals("delete")) {
                    String deleteinput = indexField.getText();
                    SkincareProduct selectedprod = thisRoutine.selectProductIndexBased(deleteinput);
                    thisRoutine.deleteProduct(selectedprod);
                    this.dispose();
                    irgScreen.dispose(); // remakes new screen to update list
                    new IndiRoutineGUI(thisRoutine);
                } else if (action.equals("reorder")) {
                    String selectinput = indexField.getText();
                    SkincareProduct selectedprod = thisRoutine.selectProductIndexBased(selectinput);
                    this.dispose();
                    new ReorderProductGUI(thisRoutine, selectedprod, irgScreen);
                } else if (action.equals("edit")) {
                    String selectinput = indexField.getText();
                    SkincareProduct selectedprod = thisRoutine.selectProductIndexBased(selectinput);
                    this.dispose();
                    new EditProductGUI(thisRoutine, selectedprod, irgScreen);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException exc) {
                JOptionPane.showMessageDialog(null,
                        exc + "! Try again",
                        "Oh naur.",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
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