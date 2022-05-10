package Engine;

import Entity.Player;
import ItemsAndSpells.Item;

import java.util.ArrayList;

public class Trader {
    private Player player;
    private int interactionCount = 0;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

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

    public void sellToTrader(Item item) {
        player.takeFromInventory(item, interactionCount);
    }

    public void buyFromTrader(Item item) {
        player.addToInventory(item, interactionCount);
    }
}
