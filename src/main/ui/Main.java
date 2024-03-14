package ui;

import model.SkincareProduct;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        new SkincareRoutinesGUI();

//        tryy {
//            new RoutinesOverview();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}
