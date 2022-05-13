package GUI;

import Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow extends JFrame {
  private JPanel mainPanel = new JPanel();
  private JPanel nameInsertion = new JPanel(new BorderLayout());
  private JButton startButton = new JButton("Start");
  private JLabel nameLabel = new JLabel("Please enter the name of the player here:");
  private JTextField nameInsertionField = new JTextField();
  private JButton submitButton = new JButton("Submit");

  // action listener class here
  private class startListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      startButton.setVisible(false);
      nameInsertion.setVisible(true);
    }
  }

  public class submitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String playerName = nameInsertionField.getText();
      if (playerName.equals("")) {
        nameLabel.setText("You have not entered a name. Please try again:");
      } else {
        Player player = new Player(playerName);
        dispose();
        new GameWindow(player);
      }
    }
  }

  public StartingWindow() {
    super("Quest to Phandolin");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 400);
    setResizable(false);
    setLayout(new FlowLayout());


    //The images
    Image image = new ImageIcon("GFX\\logo.png").getImage();
    image = image.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
    ImageIcon icon = new ImageIcon("GFX\\icon.png");
    this.setIconImage(icon.getImage());
    mainPanel.setLayout(new BorderLayout());

    JLabel infoPanel = new JLabel("Welcome to the quest of Phandolin.");
    infoPanel.setIcon((new ImageIcon(image)));
    infoPanel.setHorizontalTextPosition(JLabel.CENTER);
    infoPanel.setVerticalTextPosition(JLabel.BOTTOM);
    infoPanel.setVerticalAlignment(JLabel.BOTTOM);
    mainPanel.add(infoPanel, BorderLayout.NORTH);

    startButton.addActionListener(new startListener());
    startButton.setSize(new Dimension(100,100));
    mainPanel.add(startButton, BorderLayout.CENTER);

    nameInsertion.add(nameLabel, BorderLayout.NORTH);
    nameInsertion.add(nameInsertionField, BorderLayout.CENTER);
    nameInsertion.add(submitButton, BorderLayout.SOUTH);
    submitButton.addActionListener(new submitListener());
    nameInsertion.setVisible(false);

    mainPanel.add(nameInsertion, BorderLayout.SOUTH);
    // Name insertion field

    add(new JLabel());
    add(mainPanel);
    add(new JLabel());

    this.setLocationRelativeTo(null);
    setVisible(true);
  }
}
