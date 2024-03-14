package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

//CLASS LEVEL COMMENT: creates the GUI for each individual routine
public class IndiRoutineGUI extends JFrame implements ActionListener {
    private JPanel topPanel;
    private JPanel listPanel;
    private JLabel routinetitle;

    private JButton add;
    private JButton delete;
    private JButton reorder;
    private JButton edit;

    private Routine thisRoutine;

    String allsteps;

    // MODIFIES: this
    // EFFECTS: constructs the GUI for each individual routine
    public IndiRoutineGUI(Routine r) {
        createRoutineTitle(r);
        createButtons();
        thisRoutine = r;
        createTopPanel();
        createListPanel();
        outputRoutine(r);

        this.setTitle("MySkincareScheduler: " + r.getDayOfWeek() + " " + r.getTimeOfDay());
        this.setSize(1000, 800);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(listPanel);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates top panel where control buttons are
    public void createTopPanel() {
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(1000, 150));
        this.topPanel.setBackground(new Color(0xA06356));
        this.topPanel.setLayout(new FlowLayout(1));
        this.topPanel.add(routinetitle);
        this.topPanel.add(add);
        this.topPanel.add(delete);
        this.topPanel.add(edit);
        this.topPanel.add(reorder);
    }

    // MODIFIES: this
    // EFFECTS: creates the middle panel where the list of the products in the skincare routine will be displays
    public void createListPanel() {
        this.listPanel = new JPanel();
        this.listPanel.setPreferredSize(new Dimension(1000, 300));
        this.listPanel.setBackground(new Color(0xFFFFFF));
        this.listPanel.setLayout(new FlowLayout(1));
    }

    // MODIFIES: this
    // EFFECTS: creates top panel where control buttons are
    public void createRoutineTitle(Routine r) {
        routinetitle = new JLabel("My Skincare Routine for: " + r.getDayOfWeek() + " " + r.getTimeOfDay());
        this.routinetitle.setPreferredSize(new Dimension(1000, 100));
        this.routinetitle.setHorizontalAlignment(JLabel.CENTER);
        this.routinetitle.setVerticalAlignment(JLabel.CENTER);
        this.routinetitle.setForeground(Color.white);
        this.routinetitle.setFont(new Font("Lucida Bright", 0, 25));
    }

    // MODIFIES: this
    // EFFECTS: outputs the series of products via JLabels
    public void outputRoutine(Routine r) {

        if (r.emptyRoutine()) {
            JLabel emptyroutine = new JLabel("no products in your routine...yet!");
            this.listPanel.add(emptyroutine);
        } else {
            String step = "";
            for (SkincareProduct p : r.getRoutine()) {
                step += "Step " + (r.getRoutine().indexOf(p) + 1) + ": " + outputProduct(p) + "<br>";
                allsteps = "<html>" + step + "</html>";
            }
            this.listPanel.add(new JLabel(allsteps));
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons for the top panel
    public void createButtons() {
        add = new JButton();
        add.setText("Add a product");
        add.setHorizontalAlignment(JButton.CENTER);
        add.setVerticalTextPosition(JButton.BOTTOM);
        add.addActionListener(this);

        delete = new JButton();
        delete.setText("delete a product");
        delete.setHorizontalAlignment(JButton.CENTER);
        delete.setVerticalTextPosition(JButton.BOTTOM);
        delete.addActionListener(this);

        reorder = new JButton();
        reorder.setText("reorder a product in your routine");
        reorder.setHorizontalAlignment(JButton.CENTER);
        reorder.setVerticalTextPosition(JButton.BOTTOM);
        reorder.addActionListener(this);

        edit = new JButton();
        edit.setText("edit a product in your routine");
        edit.setHorizontalAlignment(JButton.CENTER);
        edit.setVerticalTextPosition(JButton.BOTTOM);
        edit.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: helper function to outputRoutine. Outputs the product information in String format
    public String outputProduct(SkincareProduct p) {
        return p.getProductType() + " - " + p.getProductName() + " " + "by " + p.getProductBrand();
    }

    // MODIFIES: this
    // EFFECTS: allows button events to have consequence. If x button is pushed, does y.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new AddGUI(thisRoutine, this);
        }
        if (e.getSource() == delete) {
            if (thisRoutine.emptyRoutine()) {
                emptyRoutineMessage();
            } else {
                new SelectGUI(thisRoutine, "delete", this);
            }
        }
        if (e.getSource() == edit) {
            if (thisRoutine.emptyRoutine()) {
                emptyRoutineMessage();
            } else {
                new SelectGUI(thisRoutine, "edit", this);
            }
        }
        if (e.getSource() == reorder) {
            if (thisRoutine.emptyRoutine()) {
                emptyRoutineMessage();
            } else {
                new SelectGUI(thisRoutine, "reorder", this);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays a JOptionpane if the routine does not have any products
    public void emptyRoutineMessage() {
        JOptionPane.showMessageDialog(null,
                "Routine is empty. Can't do anything...Try adding some products first",
                "Oh naur",
                JOptionPane.PLAIN_MESSAGE);
    }
}
