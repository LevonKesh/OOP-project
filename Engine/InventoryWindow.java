package Engine;

import Entity.Player;
import ItemsAndSpells.Item;
import ItemsAndSpells.Weapon;
import Parsers.ItemParser;
import Parsers.WeaponParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryWindow extends JFrame {
    Player player;
    JPanel inventory = new JPanel();;

    private class InventoryActionListener implements ActionListener {

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
            if (reqItem != null) {
                if (reqItem instanceof Weapon) {
                    player.setChosenWeapon((Weapon) reqItem);
                }
                else if (reqItem.getName().equals("Healing Potion")) {
                    player.usePotion();
                    updateInventory();
                }
            }

        }
    }

    public InventoryWindow(Player player) {
        super("Inventory");
        this.player = player;

        setSize(250, 150);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inventory.setLayout(new GridLayout(this.player.getInventory().size(), 2));
        updateInventory();
        add(inventory);

        setVisible(true);
    }

    private void updateInventory() {
        inventory.removeAll();
        for (int i = 0; i < this.player.getInventory().size(); i++) {
            JButton inventoryItem = new JButton(this.player.getInventory().get(i).getName());
            inventoryItem.addActionListener(new InventoryActionListener());
            inventory.add(inventoryItem);
            JButton inventoryItemCount = new JButton(String.valueOf(this.player.getInventoryCount().get(i)));
            inventory.add(inventoryItemCount);
        }
        inventory.revalidate();
        inventory.repaint();
    }
}
