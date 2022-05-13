package GUI;

import javax.swing.*;

public class ErrorWindow extends JFrame{
    JLabel text = new JLabel();

    public ErrorWindow(String textOfError) {
        super("Trade impossible");
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        text.setText(textOfError);
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public ErrorWindow() {
        new ErrorWindow("Unknown error");
    }
}
