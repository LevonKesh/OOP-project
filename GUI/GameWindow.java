package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Battle.Dice;
import Entity.Entity;
import Entity.Player;
import Entity.Trader;
import ItemsAndSpells.Weapon;
import Parsers.*;

public class GameWindow extends JFrame {
    private Player player;

    PlayerStatWindow playerStatWindow;
    TraderWindow traderWindow;
    InventoryWindow inventoryWindow;

    private JPanel mainPanel = new JPanel();
    private JTextArea storyText = new JTextArea();
    private JScrollPane scrolledText = new JScrollPane(storyText);

    private JLabel picture = new JLabel();

    // Right Panel
    private JPanel rightPanel = new JPanel();

    private JButton nextButton = new JButton("Next");

    private JButton inventory = new JButton("Inventory");
    private JButton playerStats = new JButton("Player Stats");

    private JButton exitButton = new JButton("Exit");

    // ACT 1
    private JButton comeCloser = new JButton("Come Closer");
    private JButton investigate = new JButton("Investigate");

    private JButton battle = new JButton("To Battle");
    private JButton stealth = new JButton("Try to sneak from them");

    // ACT 2
    // peaceful area
    private JButton trader = new JButton("Local Trader");
    private JButton halfling = new JButton("Go to the Halfling.");
    private JButton halfwit = new JButton("Got to the halfwit.");

    // battle with mages
    private JButton persuade = new JButton("Try to persuade the mage.");

    //ACT 3
    // NO buttons needed

    private JButton[] buttons = new JButton[4];

    // Buttonspanel
    JPanel buttonsPanel = new JPanel();
    private boolean isTrollPassed = false;

    private class buttonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            String event = e.getActionCommand();
            //NEXT BUTTON
            if (event.equals(nextButton.getText())) {
                if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(1))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Goblin"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(0).get(3));
                    updateButtonsPanel(battle, stealth, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(2))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Goblin"));
                    entities.remove(entities.size() - 1); // to get only one goblin
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(0).get(3));
                    updateButtonsPanel(battle, stealth, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(4))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Goblin", "Wolf"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(0).get(5));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(5))) {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(6));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(6))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Bugbear"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(0).get(7));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(7))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(0));
                    updateButtonsPanel(trader, halfling, halfwit, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(1))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(6));
                    updateButtonsPanel(battle, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(8))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(9));
                    updateButtonsPanel(battle, persuade, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(11))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Evil Mage", "Cultist"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(1).get(12));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(12))) {
                    storyText.setText(StoryParser.parseDatabase().get(2).get(0));
                    Weapon alanduil = (Weapon) WeaponParser.getSelectedWeapons("Sword Of Alenduil").get(0);
                    player.addToInventory(alanduil, 1);
                    player.setChosenWeapon(alanduil);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(10))) {
                    storyText.setText(StoryParser.parseDatabase().get(2).get(0));
                    Weapon alanduil = (Weapon) WeaponParser.getSelectedWeapons("Sword Of Alenduil").get(0);
                    player.addToInventory(alanduil, 1);
                    player.setChosenWeapon(alanduil);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(2))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(3));
                    player.addToInventory(ItemParser.getSelectedItems("Healing Potion").get(0), 5);
                    updateButtonsPanel(battle, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(4))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(5));
                    player.addToInventory(ItemParser.getSelectedItems("Key From Mage").get(0), 1);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(5))) {
                    if (!isTrollPassed) {
                        storyText.setText(StoryParser.parseDatabase().get(1).get(1));
                        updateButtonsPanel(nextButton, trader, inventory, playerStats);
                        ;
                    } else {
                        storyText.setText(StoryParser.parseDatabase().get(1).get(8));
                        updateButtonsPanel(nextButton, inventory, playerStats);
                    }
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(7))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(2));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(6))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(9));
                    updateButtonsPanel(battle, persuade, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(2).get(0))) {
                    storyText.setText(StoryParser.parseDatabase().get(2).get(1));
                    updateButtonsPanel(battle, inventory, playerStats);
                }
            }


            // COME CLOSER
            else if (event.equals(comeCloser.getText())) {
                storyText.setText(StoryParser.parseDatabase().get(0).get(1));
                updateButtonsPanel(nextButton, inventory, playerStats);
            }
            //INVESTIGATE
            else if (event.equals(investigate.getText())) {
                if (player.getIntelligence() + Dice.d20() >
                        14 + EnemyParser.getSelectedEnemies("Goblin").get(0).getIntelligence()) {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(2));
                    player.addSkillPoints(2);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(1));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                }
            }
            // BATTLE
            else if (event.equals(battle.getText())) {
                if (storyText.getText().equals(StoryParser.parseDatabase().get(0).get(3))) {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(4));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(6))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Troll"));
                    entities.add(player);
                    new BattleWindow(entities);
                    isTrollPassed = true;
                    if (player.getInventory().contains(ItemParser.getSelectedItems("Key From Mage").get(0))) {
                        storyText.setText(StoryParser.parseDatabase().get(1).get(8));
                        updateButtonsPanel(nextButton, inventory, playerStats);
                    } else {
                        storyText.setText(StoryParser.parseDatabase().get(1).get(7));
                        updateButtonsPanel(nextButton, inventory, playerStats);
                    }
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(9))) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(11));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(1).get(3))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Cultist", "Cultist"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(1).get(4));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else if (storyText.getText().equals(StoryParser.parseDatabase().get(2).get(1))) {
                    ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Dragon"));
                    entities.add(player);
                    new BattleWindow(entities);
                    storyText.setText(StoryParser.parseDatabase().get(2).get(2));
                    updateButtonsPanel(exitButton);
                }
            }
            // STEALTH
            else if (event.equals(stealth.getText())) {
                if (player.getDexterity() + Dice.d20() >
                        15 + EnemyParser.getSelectedEnemies("Goblin").get(0).getIntelligence()) {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(5));
                    player.addSkillPoints(2);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else {
                    storyText.setText(StoryParser.parseDatabase().get(0).get(4));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                }
            }


            // PERSUADE
            else if (event.equals(persuade.getText())) {
                if (player.getCharisma() + Dice.d20() >
                        15 + EnemyParser.getSelectedEnemies("Evil Mage").get(0).getIntelligence()) {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(10));
                    player.addSkillPoints(5);
                    updateButtonsPanel(nextButton, inventory, playerStats);
                } else {
                    storyText.setText(StoryParser.parseDatabase().get(1).get(11));
                    updateButtonsPanel(nextButton, inventory, playerStats);
                }
            }

            // HALFLING
            else if (event.equals((halfling.getText()))) {
                storyText.setText(StoryParser.parseDatabase().get(1).get(1));
                updateButtonsPanel(nextButton, trader, inventory, playerStats);
            }

            // HALFWIT
            else if (event.equals((halfwit.getText()))) {
                storyText.setText(StoryParser.parseDatabase().get(1).get(2));
                updateButtonsPanel(nextButton, trader, inventory, playerStats);
            }

            // TRADER
            else if (event.equals(trader.getText())) {
                traderWindow.setTraderInventory();
                traderWindow.setPlayerInventory();
                traderWindow.setVisible(true);
            }

            // INVENTORY
            else if (event.equals(inventory.getText())) {
                inventoryWindow.updateInventory();
                inventoryWindow.setVisible(true);
            }

            // EXIT
            else if (event.equals(exitButton.getText())) {
                System.exit(0);
            }

            else if (event.equals(playerStats.getText())) {
                playerStatWindow.setVisible(true);
            }
        }
    }

    public GameWindow(Player player) {
        super("Quest to Phandolin");
        this.player = player;

        // Basic Player Windows generation
        playerStatWindow = new PlayerStatWindow(player);
        traderWindow = new TraderWindow(player, new Trader(player));
        inventoryWindow = new InventoryWindow(player);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setResizable(false);
        setLayout(new FlowLayout());

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 650));
        mainPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        storyText.setLineWrap(true);
        storyText.setEditable(false);
        storyText.setText(StoryParser.parseDatabase().get(0).get(0));
        storyText.setFont(new Font(Font.SERIF, Font.PLAIN, 16));

        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrolledText, BorderLayout.CENTER);

        Image image = new ImageIcon("GFX\\art.jpg").getImage();
        image = image.getScaledInstance(600, 350, Image.SCALE_SMOOTH);
        picture.setIcon(new ImageIcon(image));
        picture.setSize(650, 350); // May need a resizing in the future
        mainPanel.add(picture, BorderLayout.SOUTH);
        add(mainPanel);

        rightPanel.setLayout(new BorderLayout());

        nextButton.addActionListener(new buttonListener());
        comeCloser.addActionListener(new buttonListener());
        investigate.addActionListener(new buttonListener());
        battle.addActionListener(new buttonListener());
        stealth.addActionListener(new buttonListener());
        trader.addActionListener(new buttonListener());
        halfling.addActionListener(new buttonListener());
        halfwit.addActionListener(new buttonListener());
        persuade.addActionListener(new buttonListener());
        playerStats.addActionListener(new buttonListener());
        inventory.addActionListener(new buttonListener());
        exitButton.addActionListener(new buttonListener());

        buttonsPanel.setLayout(new GridLayout(5, 1));
        updateButtonsPanel(comeCloser, investigate, inventory, playerStats);

        rightPanel.add(buttonsPanel, BorderLayout.NORTH);
        rightPanel.add(new JLabel(), BorderLayout.CENTER);


        add(rightPanel);

        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonsPanel(JButton... buttons1) {
//        buttonsPanel.setLayout(new GridLayout(buttons.length, 1));
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] != null) {
                buttons[i].setAlignmentY(Component.CENTER_ALIGNMENT);
//                buttons[i].setSize(150, 25);
                buttonsPanel.remove(buttons[i]);
            }
        }

        for (int i = 0; i < buttons1.length; i++) {
            if (buttons1[i] != null) {
                buttons1[i].setAlignmentY(Component.CENTER_ALIGNMENT);
                buttonsPanel.add(buttons1[i]);
            }
        }
        buttons = buttons1;
        buttonsPanel.revalidate();
        buttonsPanel.repaint();

    }
}
