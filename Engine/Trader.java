package Engine;

import Entity.Player;
import ItemsAndSpells.Item;
import Parsers.ItemParser;
import Parsers.WeaponParser;

import java.util.ArrayList;
import java.util.Locale;

public class Trader {
    private Player player;
    private int interactionCount = 0;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

    private static final Item coin = ItemParser.getSelectedItems("Coins").get(0);

    public Trader(Player player, ArrayList<Item> inventory, ArrayList<Integer> inventoryCount) {
        this.player = player;
        this.inventory = inventory;
        this.inventoryCount = inventoryCount;
    }

    public Trader(Player player) {
        this.player = player;
        this.inventory = new ArrayList<>();
        this.inventoryCount = new ArrayList<>();

        addToInventory(WeaponParser.getSelectedWeapons("Iron Sword").get(0), 4);
        addToInventory(coin, 3000);
        addToInventory(ItemParser.getSelectedItems("Healing Potion").get(0), 20);
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

    public void setInteractionCount(int interactionCount) throws TradeImpossibleException{
        if (interactionCount >= 0) {
            this.interactionCount = interactionCount;
        }
        else {
            throw new TradeImpossibleException("Interaction count incorrect");
        }
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
                item.isSellable()) {
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

    public void sellToTrader(Item item) throws TradeImpossibleException {
        if (!this.getInventory().contains(coin)) {
            throw new TradeImpossibleException("Trader does not have  any money left to buy anything.");
        }
        if (item.getValue() * interactionCount <= inventoryCount.get(inventory.indexOf(coin))) {
            player.takeFromInventory(item, interactionCount);
            player.addToInventory(coin, item.getValue() * interactionCount);
            addToInventory(item, interactionCount);
            takeFromInventory(coin, item.getValue() * interactionCount);
        }
        else {
            throw new TradeImpossibleException("Trader does not have enough money to buy " + item.getName().toLowerCase() + ".");
        }
    }

    public void buyFromTrader(Item item) throws TradeImpossibleException{
        if (!player.getInventory().contains(coin)) {
            throw new TradeImpossibleException("You do not have money to buy anything.");
        }
        else if (item.getValue() * interactionCount <= player.getInventoryCount().get(player.getInventory().indexOf(coin))) {
            player.addToInventory(item, interactionCount);
            player.takeFromInventory(coin, item.getValue() * interactionCount);
            takeFromInventory(item, interactionCount);
            addToInventory(coin, item.getValue() * interactionCount);
        }
        else {
            throw new TradeImpossibleException("You do not have enough money to buy " + item.getName().toLowerCase() + ".");
        }
    }
}
