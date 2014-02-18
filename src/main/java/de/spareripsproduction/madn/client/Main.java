package de.spareripsproduction.madn.client;


import javax.swing.*;

/**
 * Created by marian on 08/02/14.
 */
public class Main {

    public static void main(String args[]) {
        System.out.println("This is client main!");


        JFrame frame = new JFrame("Dr. Rips ärgere dich nicht!");
        JTextField field = new JTextField();
        field.setSize(100,23);
        field.setText("Fertig - abputzen");
        frame.setLayout(null);
        frame.add(field);
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //region LoL

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                System.out.println("Dr. Rips ärgere dich nicht!"+ i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(field.getText());
        //endregion

        //fertig - abputzen
    }
}
