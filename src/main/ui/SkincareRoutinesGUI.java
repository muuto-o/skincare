package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.EventLog;
import model.Event;
import model.Routine;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

//CLASS LEVEL COMMENT: creates the GUI for the main screen
public class SkincareRoutinesGUI extends JFrame implements ActionListener {
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JLabel introLabel;
    private ImageIcon image;

    private JButton monM;
    private JButton monN;
    private JButton tueM;
    private JButton tueN;
    private JButton wedM;
    private JButton wedN;
    private JButton thuM;
    private JButton thuN;
    private JButton friM;
    private JButton friN;
    private JButton satM;
    private JButton satN;
    private JButton sunM;
    private JButton sunN;
    private JButton save;
    private JButton load;
    private JButton exit;

    private static final String MONM_SAVE = "./data/monM.json";
    private static final String MONN_SAVE = "./data/monN.json";
    private static final String TUEM_SAVE = "./data/tueM.json";
    private static final String TUEN_SAVE = "./data/tueN.json";
    private static final String WEDM_SAVE = "./data/wedM.json";
    private static final String WEDN_SAVE = "./data/wedN.json";
    private static final String THUM_SAVE = "./data/thuM.json";
    private static final String THUN_SAVE = "./data/thuN.json";
    private static final String FRIM_SAVE = "./data/friM.json";
    private static final String FRIN_SAVE = "./data/friN.json";
    private static final String SATM_SAVE = "./data/satM.json";
    private static final String SATN_SAVE = "./data/satN.json";
    private static final String SUNM_SAVE = "./data/sunM.json";
    private static final String SUNN_SAVE = "./data/sunN.json";

    // per days of week
    // per days of week
    private JsonWriter jsonWriterMonM;
    private JsonReader jsonReaderMonM;
    private JsonWriter jsonWriterMonN;
    private JsonReader jsonReaderMonN;
    private JsonWriter jsonWriterTueM;
    private JsonReader jsonReaderTueM;
    private JsonWriter jsonWriterTueN;
    private JsonReader jsonReaderTueN;
    private JsonWriter jsonWriterWedM;
    private JsonReader jsonReaderWedM;
    private JsonWriter jsonWriterWedN;
    private JsonReader jsonReaderWedN;
    private JsonWriter jsonWriterThuM;
    private JsonReader jsonReaderThuM;
    private JsonWriter jsonWriterThuN;
    private JsonReader jsonReaderThuN;
    private JsonWriter jsonWriterFriM;
    private JsonReader jsonReaderFriM;
    private JsonWriter jsonWriterFriN;
    private JsonReader jsonReaderFriN;
    private JsonWriter jsonWriterSatM;
    private JsonReader jsonReaderSatM;
    private JsonWriter jsonWriterSatN;
    private JsonReader jsonReaderSatN;
    private JsonWriter jsonWriterSunM;
    private JsonReader jsonReaderSunM;
    private JsonWriter jsonWriterSunN;
    private JsonReader jsonReaderSunN;

    // constant number of routines
    private Routine monMorning = new Routine("Monday", "Morning");
    private Routine monNight = new Routine("Monday", "Night");
    private Routine tueMorning = new Routine("Tuesday", "Morning");
    private Routine tueNight = new Routine("Tuesday", "Night");
    private Routine wedMorning = new Routine("Wednesday", "Morning");
    private Routine wedNight = new Routine("Wednesday", "Night");
    private Routine thuMorning = new Routine("Thursday", "Morning");
    private Routine thuNight = new Routine("Thursday", "Night");
    private Routine friMorning = new Routine("Friday", "Morning");
    private Routine friNight = new Routine("Friday", "Night");
    private Routine satMorning = new Routine("Saturday", "Morning");
    private Routine satNight = new Routine("Saturday", "Night");
    private Routine sunMorning = new Routine("Sunday", "Morning");
    private Routine sunNight = new Routine("Sunday", "Night");

    private EventLog eventlog;

    // MODIFIES: this
    // EFFECTS: spawns in the main screenonReaderMonM = new JsonReader(MONM_SAVE);
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public SkincareRoutinesGUI() {
        jsonWriterMonM = new JsonWriter(MONM_SAVE);
        jsonReaderMonM = new JsonReader(MONM_SAVE);
        jsonWriterMonN = new JsonWriter(MONN_SAVE);
        jsonReaderMonN = new JsonReader(MONN_SAVE);
        jsonWriterTueM = new JsonWriter(TUEM_SAVE);
        jsonReaderTueM = new JsonReader(TUEM_SAVE);
        jsonWriterTueN = new JsonWriter(TUEN_SAVE);
        jsonReaderTueN = new JsonReader(TUEN_SAVE);
        jsonWriterWedM = new JsonWriter(WEDM_SAVE);
        jsonReaderWedM = new JsonReader(WEDM_SAVE);
        jsonWriterWedN = new JsonWriter(WEDN_SAVE);
        jsonReaderWedN = new JsonReader(WEDN_SAVE);
        jsonWriterThuM = new JsonWriter(THUM_SAVE);
        jsonReaderThuM = new JsonReader(THUM_SAVE);
        jsonWriterThuN = new JsonWriter(THUN_SAVE);
        jsonReaderThuN = new JsonReader(THUN_SAVE);
        jsonWriterFriM = new JsonWriter(FRIM_SAVE);
        jsonReaderFriM = new JsonReader(FRIM_SAVE);
        jsonWriterFriN = new JsonWriter(FRIN_SAVE);
        jsonReaderFriN = new JsonReader(FRIN_SAVE);
        jsonWriterSatM = new JsonWriter(SATM_SAVE);
        jsonReaderSatM = new JsonReader(SATM_SAVE);
        jsonWriterSatN = new JsonWriter(SATN_SAVE);
        jsonReaderSatN = new JsonReader(SATN_SAVE);
        jsonWriterSunM = new JsonWriter(SUNM_SAVE);
        jsonReaderSunM = new JsonReader(SUNM_SAVE);
        jsonWriterSunN = new JsonWriter(SUNN_SAVE);
        jsonReaderSunN = new JsonReader(SUNN_SAVE);
        eventlog = EventLog.getInstance();

        image = new ImageIcon("./data/logo.png");
        createIntroLabel();
        createButtons();
        createTopPanel();
        createMiddlePanel();
        createBottomPanel();

        this.setTitle("MySkincareScheduler");
        this.setSize(1000, 800);
        this.setIconImage(image.getImage());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates top panel
    public void createTopPanel() {
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(1000, 300));
        this.topPanel.setBackground(new Color(0xA06356));
        this.topPanel.setLayout(new FlowLayout(1));
        this.topPanel.add(introLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates the heading and logo
    public void createIntroLabel() {
        this.introLabel = new JLabel("MySkincareScheduler <3");
        this.introLabel.setPreferredSize(new Dimension(1000, 300));
        this.introLabel.setForeground(Color.white);
        this.introLabel.setFont(new Font("Lucida Bright", 0, 30));
        //this.introLabel.setOpaque(true);
        introLabel.setIcon(image);
    }

    // MODIFIES: this
    // EFFECTS: creates the middle panel where the routines buttons are displayed
    public void createMiddlePanel() {
        this.middlePanel = new JPanel();
        this.middlePanel.setPreferredSize(new Dimension(1000, 800));
        this.middlePanel.setBackground(new Color(0xFFFFFF));
        this.middlePanel.setLayout(new FlowLayout(1));
        // todo: if you plan to add more days, make a table structure
        this.middlePanel.add(monM);
        this.middlePanel.add(monN);
        this.middlePanel.add(tueM);
        this.middlePanel.add(tueN);
        this.middlePanel.add(wedM);
        this.middlePanel.add(wedN);
        this.middlePanel.add(thuM);
        this.middlePanel.add(thuN);
        this.middlePanel.add(friM);
        this.middlePanel.add(friN);
        this.middlePanel.add(satM);
        this.middlePanel.add(satN);
        this.middlePanel.add(sunM);
        this.middlePanel.add(sunN);
    }

    // MODIFIES: this
    // EFFECTS: creates the bottom panel where the save and load buttons are
    public void createBottomPanel() {
        this.bottomPanel = new JPanel();
        this.bottomPanel.setPreferredSize(new Dimension(1000, 100));
        this.bottomPanel.setBackground(new Color(0xA06356));
        this.bottomPanel.setLayout(new FlowLayout(1));
        this.bottomPanel.add(save);
        this.bottomPanel.add(load);
        this.bottomPanel.add(exit);
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons.
    public void createButtons() {
        buttonsOfWeek();

        save = new JButton();
        save.setHorizontalAlignment(JButton.CENTER);
        save.setVerticalTextPosition(JButton.TOP);
        save.setText("Save");
        save.addActionListener(this);

        load = new JButton();
        load.setHorizontalAlignment(JButton.CENTER);
        load.setVerticalTextPosition(JButton.TOP);
        load.setText("Load");
        load.addActionListener(this);

        exit = new JButton();
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.TOP);
        exit.setText("Exit");
        exit.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates all the buttons that represent the 14 routines of the week
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void buttonsOfWeek() {
        monM = new JButton();
        monM.setText("Monday Morning Routine");
        monM.addActionListener(this);

        monN = new JButton();
        monN.setText("Monday Night Routine");
        monN.addActionListener(this);

        tueM = new JButton();
        tueM.setText("Tuesday Morning Routine");
        tueM.addActionListener(this);

        tueN = new JButton();
        tueN.setText("Tuesday Night Routine");
        tueN.addActionListener(this);

        wedM = new JButton();
        wedM.setText("Wednesday Morning Routine");
        wedM.addActionListener(this);

        wedN = new JButton();
        wedN.setText("Wednesday Night Routine");
        wedN.addActionListener(this);

        thuM = new JButton();
        thuM.setText("Thursday Morning Routine");
        thuM.addActionListener(this);

        thuN = new JButton();
        thuN.setText("Thursday Night Routine");
        thuN.addActionListener(this);

        friM = new JButton();
        friM.setText("Friday Morning Routine");
        friM.addActionListener(this);

        friN = new JButton();
        friN.setText("Friday Night Routine");
        friN.addActionListener(this);

        satM = new JButton();
        satM.setText("Saturday Morning Routine");
        satM.addActionListener(this);

        satN = new JButton();
        satN.setText("Saturday Night Routine");
        satN.addActionListener(this);

        sunM = new JButton();
        sunM.setText("Sunday Morning Routine");
        sunM.addActionListener(this);

        sunN = new JButton();
        sunN.setText("Sunday Night Routine");
        sunN.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: allows button events to have consequence. If x button is pushed, does y.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == monM) {
            new IndiRoutineGUI(monMorning);
        }
        if (e.getSource() == monN) {
            new IndiRoutineGUI(monNight);
        }
        if (e.getSource() == tueM) {
            new IndiRoutineGUI(tueMorning);
        }
        if (e.getSource() == tueN) {
            new IndiRoutineGUI(tueNight);
        }
        if (e.getSource() == wedM) {
            new IndiRoutineGUI(wedMorning);
        }
        if (e.getSource() == wedN) {
            new IndiRoutineGUI(wedNight);
        }
        if (e.getSource() == thuM) {
            new IndiRoutineGUI(thuMorning);
        }
        if (e.getSource() == thuN) {
            new IndiRoutineGUI(thuNight);
        }
        if (e.getSource() == friM) {
            new IndiRoutineGUI(friMorning);
        }
        if (e.getSource() == friN) {
            new IndiRoutineGUI(friNight);
        }
        if (e.getSource() == satM) {
            new IndiRoutineGUI(satMorning);
        }
        if (e.getSource() == satN) {
            new IndiRoutineGUI(satNight);
        }
        if (e.getSource() == sunM) {
            new IndiRoutineGUI(sunMorning);
        }
        if (e.getSource() == sunN) {
            new IndiRoutineGUI(sunNight);
        }
        if (e.getSource() == exit) {
            EventLog.getInstance().logEvent(new Event("program exited"));
            printLog(eventlog); // todo: edit this
            System.exit(0);
        }
        if (e.getSource() == save) {
            try {
                jsonWriterMonM.open(); // TODO: to abstract this you probably could put all of th eobjects in an array?
                jsonWriterMonN.open();
                jsonWriterTueM.open();
                jsonWriterTueN.open();
                jsonWriterWedM.open();
                jsonWriterWedN.open();
                jsonWriterThuM.open();
                jsonWriterThuN.open();
                jsonWriterFriM.open();
                jsonWriterFriN.open();
                jsonWriterSatM.open();
                jsonWriterSatN.open();
                jsonWriterSunM.open();
                jsonWriterSunN.open();
                jsonWriterMonM.writeRoutine(monMorning); // TODO: make a new jason writer and reader for every routine
                jsonWriterMonN.writeRoutine(monNight);
                jsonWriterTueM.writeRoutine(tueMorning);
                jsonWriterTueN.writeRoutine(tueNight);
                jsonWriterWedM.writeRoutine(wedMorning);
                jsonWriterWedN.writeRoutine(wedNight);
                jsonWriterThuM.writeRoutine(thuMorning);
                jsonWriterThuN.writeRoutine(thuNight);
                jsonWriterFriM.writeRoutine(friMorning);
                jsonWriterFriN.writeRoutine(friNight);
                jsonWriterSatM.writeRoutine(satMorning);
                jsonWriterSatN.writeRoutine(satNight);
                jsonWriterSunM.writeRoutine(sunMorning);
                jsonWriterSunN.writeRoutine(sunNight);
                jsonWriterMonM.close();
                jsonWriterMonN.close();
                jsonWriterTueM.close();
                jsonWriterTueN.close();
                jsonWriterWedM.close();
                jsonWriterWedN.close();
                jsonWriterThuM.close();
                jsonWriterThuN.close();
                jsonWriterFriM.close();
                jsonWriterFriN.close();
                jsonWriterSatM.close();
                jsonWriterSatN.close();
                jsonWriterSunM.close();
                jsonWriterSunN.close();
                EventLog.getInstance().logEvent(new Event("JSON file saved"));
                JOptionPane.showMessageDialog(null, "Changes saved <3", "Note:", JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException exc) {
                EventLog.getInstance().logEvent(new Event("FileNotFoundException occurred"));
                JOptionPane.showMessageDialog(null, "Unable to write file", "Oh naur:", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if (e.getSource() == load) {
            try {
                monMorning = jsonReaderMonM.read();
                monNight = jsonReaderMonN.read();
                tueMorning = jsonReaderTueM.read();
                tueNight = jsonReaderTueN.read();
                wedMorning = jsonReaderWedM.read();
                wedNight = jsonReaderWedN.read();
                thuMorning = jsonReaderThuM.read();
                thuNight = jsonReaderThuN.read();
                friMorning = jsonReaderFriM.read();
                friNight = jsonReaderFriN.read();
                satMorning = jsonReaderSatM.read();
                satNight = jsonReaderSatN.read();
                sunMorning = jsonReaderSunM.read();
                sunNight = jsonReaderSunN.read();
                EventLog.getInstance().logEvent(new Event("JSON file loaded"));
                JOptionPane.showMessageDialog(null, "Loaded previous save <3", "Note:", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException exc) {
                EventLog.getInstance().logEvent(new Event("IO Exception occurred"));
                JOptionPane.showMessageDialog(null, "Unable to read from file", "Oh naur", JOptionPane.PLAIN_MESSAGE);
            } catch (JSONException exc) {
                EventLog.getInstance().logEvent(new Event("JSONException occurred"));
                //JOptionPane.showMessageDialog(null, "your JSON is a little shoite", "Oh naur",
                // JOptionPane.PLAIN_MESSAGE);
                // error will occur if some JSON files are completely blank.
                // means nothing has been added to it yet. so its fine.
            }
        }
    }

    public void printLog(EventLog el) { // TODO: edit this
        for (Event next : el) {
            System.out.println(next.toString());
        }
        repaint();
    }
}

