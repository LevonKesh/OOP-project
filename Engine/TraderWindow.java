package Engine;

import Entity.Player;
import ItemsAndSpells.Item;
import Parsers.ItemParser;
import Parsers.WeaponParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TraderWindow extends JFrame {
    private Trader trader;
    private Player player;

    private JPanel playerInventory = new JPanel();
    private JPanel traderInventory = new JPanel();
    private JLabel countText = new JLabel();
    private JTextField count = new JTextField();

    private class BuyingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = e.getActionCommand();
            Item reqItem = null;
            if (ItemParser.getSelectedItems(itemName).size() != 0) {
                reqItem = ItemParser.getSelectedItems(itemName).get(0);
            }
            else {
                reqItem = WeaponParser.getSelectedWeapons(itemName).get(0);
            }
            try {
                int itemCount = Integer.parseInt(count.getText());
                trader.setInteractionCount(itemCount);
                trader.buyFromTrader(reqItem);
                setPlayerInventory();
                setTraderInventory();

            } catch (NumberFormatException e1) {
                count.setText("Incorrect Format");
            }
            catch (TradeImpossibleException e2) {
                new ErrorWindow(e2.getMessage());
            }
        }
    }

    private class SellingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = e.getActionCommand();
            Item reqItem = null;
            if (ItemParser.getSelectedItems(itemName).size() != 0) {
                reqItem = ItemParser.getSelectedItems(itemName).get(0);
            }
            else {
                reqItem = WeaponParser.getSelectedWeapons(itemName).get(0);
            }
            try {
                int itemCount = Integer.parseInt(count.getText());
                trader.setInteractionCount(itemCount);
                trader.sellToTrader(reqItem);
                setPlayerInventory();
                setTraderInventory();

            }
            catch (NumberFormatException E) {
                count.setText("Incorrect Format");
            }
            catch (TradeImpossibleException e2) {
                new ErrorWindow(e2.getMessage());
            }
        }
    }

    public TraderWindow(Player player, Trader trader) {
        super("Trader");
        this.player = player;
        this.trader = trader;

        setSize(700, 200);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        playerInventory.setLayout(new GridLayout(this.player.getInventory().size(), 2));

        for (int i = 0; i < player.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(this.player.getInventory().get(i).getName());
            inventoryItem.addActionListener(new SellingListener());
            playerInventory.add(inventoryItem);
            JButton inventoryItemCount = new JButton(String.valueOf(this.player.getInventoryCount().get(i)));
            playerInventory.add(inventoryItemCount);
        }
        add(playerInventory);

        JPanel centralPanel = new JPanel(new BorderLayout());
        countText.setText("Insert the count of items here:");
        centralPanel.add(countText, BorderLayout.NORTH);
        count.setText("0");
        count.setSize(300, 200);
        centralPanel.add(count, BorderLayout.SOUTH);
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(centralPanel);

        traderInventory.setLayout(new GridLayout(trader.getInventory().size(), 2));

        for (int i = 0; i < trader.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(trader.getInventory().get(i).getName());
            inventoryItem.addActionListener(new BuyingListener());
            traderInventory.add(inventoryItem);
            JButton traderInventoryCount = new JButton(String.valueOf(trader.getInventoryCount().get(i)));
            traderInventory.add(traderInventoryCount);
        }

        add(traderInventory);

        setVisible(true);
    }

    private void setPlayerInventory() {
        playerInventory.removeAll();
        for (int i = 0; i < player.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(this.player.getInventory().get(i).getName());
            inventoryItem.addActionListener(new SellingListener());
            playerInventory.add(inventoryItem);
            JButton inventoryItemCount = new JButton(String.valueOf(this.player.getInventoryCount().get(i)));
            playerInventory.add(inventoryItemCount);
        }
        playerInventory.revalidate();
        playerInventory.repaint();
    }

    private void setTraderInventory() {
        traderInventory.removeAll();
        for (int i = 0; i < trader.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(trader.getInventory().get(i).getName());
            inventoryItem.addActionListener(new BuyingListener());
            traderInventory.add(inventoryItem);
            JButton traderInventoryCount = new JButton(String.valueOf(trader.getInventoryCount().get(i)));
            traderInventory.add(traderInventoryCount);
        }
        traderInventory.revalidate();
        traderInventory.repaint();
    }
}
