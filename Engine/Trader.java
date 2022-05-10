package Engine;

import Entity.Player;
import ItemsAndSpells.Item;

import java.util.ArrayList;

public class Trader {
    private Player player;
    private int interactionCount = 0;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

    private static final Item coin = new Item("Coin", "Golden coins of old Pheldanor that are used commonly in the island of Brandor",
            1, true);

    public Trader(Player player, ArrayList<Item> inventory, ArrayList<Integer> inventoryCount) {
        this.player = player;
        this.inventory = inventory;
        this.inventoryCount = inventoryCount;
    }

    public ArrayList<Item> getInventory() {
        return (ArrayList<Item>) inventory.clone();
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Integer> getInventoryCount() {
        return (ArrayList<Integer>) inventoryCount.clone();
    }

    public void setInventoryCount(ArrayList<Integer> inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public int getInteractionCount() {
        return interactionCount;
    }

    public void setInteractionCount(int interactionCount) {
        this.interactionCount = interactionCount;
    }

    // maybe needs to be written in other way; in parent class for both trader and entity
    public void addToInventory(Item newItem, int count) {
        if (this.inventory.contains(newItem)) {
            int index = this.inventory.indexOf(newItem);
            this.inventoryCount.set(index, this.inventoryCount.get(index) + count);
        } else {
            this.inventory.add(newItem);
            this.inventoryCount.add(count);
        }
    }

    public void takeFromInventory(Item item, int count) {
        if (this.inventory.contains(item) &&
                this.inventoryCount.get(this.inventory.indexOf(item)) >= count &&
                item.isSellable() && item.isLootable()) {
            int index = this.inventory.indexOf(item);
            this.inventoryCount.set(index, this.inventoryCount.get(index) - count);
            if (this.inventoryCount.get(index) == 0) {
                this.inventory.remove(index);
                this.inventoryCount.remove(index);
            }
        } else {
            System.out.println("No such item in inventory or not enough of the given item"); // Fixme: May be needs to be written as an exception needs a double check
        }
    }

    public Item getItem(int index) {
        return inventory.get(index);
    }

    public void sellToTrader(Item item) {
        if (item.getValue() * interactionCount <= inventoryCount.get(inventory.indexOf(coin))) {
            player.takeFromInventory(item, interactionCount);
            player.addToInventory(coin, item.getValue() * interactionCount);
            addToInventory(item, interactionCount);
            takeFromInventory(coin, item.getValue() * interactionCount);
        }
        else {
            System.out.println("Trader does not have enough money.");
        }
    }

    public void buyFromTrader(Item item) {
        if (item.getValue() * interactionCount <= player.getInventoryCount().get(inventory.indexOf(coin))) {
            player.addToInventory(item, interactionCount);
            player.takeFromInventory(coin, item.getValue() * interactionCount);
            takeFromInventory(item, interactionCount);
            addToInventory(coin, item.getValue() * interactionCount);
        }
        else {
            System.out.println("You do not have enough money to buy the item.");
        }
    }
}
