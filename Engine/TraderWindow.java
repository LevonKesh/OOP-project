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

    private JPanel playerInventory;
    private JPanel traderInventory;
    private JTextField count;

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

            } catch (NumberFormatException E) {
                count.setText("Incorrect Format");
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

            } catch (NumberFormatException E) {
                count.setText("Incorrect Format");
            }
        }
    }

    public TraderWindow(Player player) {
        super("Trader");
        this.player = player;
        this.trader = new Trader(player);

        setSize(600, 850);
        setResizable(false);
        setLayout(new FlowLayout());

        playerInventory.setLayout(new GridLayout(player.getInventory().size(), 2));

        for (int i = 0; i < player.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(player.getInventory().get(i).getName());
            inventoryItem.addActionListener(new SellingListener());
            playerInventory.add(inventoryItem);
            JButton inventoryItemCount = new JButton(String.valueOf(player.getInventoryCount().get(i)));
            playerInventory.add(inventoryItemCount);
        }
        add(playerInventory);

        add(count);

        playerInventory.setLayout(new GridLayout(player.getInventory().size(), 2));

        for (int i = 0; i < trader.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(trader.getInventory().get(i).getName());
            inventoryItem.addActionListener(new BuyingListener());
            traderInventory.add(inventoryItem);
            JButton traderInventoryCount = new JButton(String.valueOf(trader.getInventoryCount().get(i)));
            traderInventory.add(traderInventoryCount);
        }

        add(traderInventory);
    }
}
