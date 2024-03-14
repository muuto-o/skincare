package ui;

import model.Routine;
import model.SkincareProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CLASS LEVEL COMMENT: creates the GUI for the reorder product popup
public class ReorderProductGUI extends JFrame implements ActionListener {
    private Routine thisRoutine;
    private IndiRoutineGUI irgScreen;
    private SkincareProduct product;
    private JButton submit;
    private JPanel panel;
    private JTextField indexField;
    private JLabel indexL;

    // MODIFIES: this
    // EFFECTS: spawns the pop-up screen to reorder products
    ReorderProductGUI(Routine r, SkincareProduct p, IndiRoutineGUI irg) {
        this.thisRoutine = r;
        this.irgScreen = irg;
        this.product = p;
        createPanel();
        this.setTitle("");
        this.setTitle("Reorder a product");
        this.setSize(500, 150);
        this.setVisible(true);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel
    public void createPanel() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(600, 200));
        this.panel.setBackground(new Color(0xC5D0DF));
        this.panel.setLayout(new FlowLayout(1));

        indexField = new JTextField();
        indexField.setPreferredSize(new Dimension(250, 40));
        indexL = createHeading("In which STEP POSITION NUMBER in your routine do you want to put your product?");

        submit = new JButton();
        submit.setText("submit changes");
        submit.addActionListener(this);
        submit.setPreferredSize(new Dimension(100, 20));
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setVerticalTextPosition(JButton.BOTTOM);

        this.panel.add(indexL);
        this.panel.add(indexField);
        this.panel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: allows button events to have consequence. If x button is pushed, does y.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                String reorderinput = indexField.getText();
                thisRoutine.reorderProduct(product, reorderinput); // exception will occur due to this method call
                this.dispose();
                irgScreen.dispose(); // revalidate() and repaint() won't work?
                new IndiRoutineGUI(thisRoutine);
            } catch (ArrayIndexOutOfBoundsException exc) {
                JOptionPane.showMessageDialog(null,
                        exc + "! Try again you dumb hoe",
                        "Oh naur",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    // EFFECTS: helper method to create JLabel headings
    public JLabel createHeading(String heading) {
        JLabel header = new JLabel(heading);
        header.setFont(new Font("Lucida Bright", Font.PLAIN, 15));
        header.setForeground(Color.DARK_GRAY);
        return header;
    }
}
