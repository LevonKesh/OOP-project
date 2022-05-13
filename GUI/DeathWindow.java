package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathWindow extends JFrame {
    private JLabel text;
    private JButton deathButton;



    public DeathWindow() {
        super();
        setLayout(new BorderLayout());
        setSize(400, 500);

        text.setText("You died please try again");
        add(text, BorderLayout.CENTER);
        deathButton.setText("Exit");
        deathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
