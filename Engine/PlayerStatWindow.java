package Engine;

import Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerStatWindow extends JFrame {
    private Player player;

    JPanel attrs;
    JButton strength;
    JButton strengthValue;
    JButton dexterity;
    JButton dexterityValue;
    JButton constitution;
    JButton constitutionValue;
    JButton intelligence;
    JButton intelligenceValue;
    JButton wisdom;
    JButton wisdomValue;
    JButton charisma;
    JButton charismaValue;

    JLabel skillpoints;

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            if (Integer.parseInt(skillpoints.getText()) > 0) {
                switch (name) {
                    case ("Strength"):
                        player.setStrength(player.getStrength() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getStrength()));
                    case ("Dexterity"):
                        player.setDexterity(player.getDexterity() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getDexterity()));
                    case ("Constitution"):
                        player.setConstitution(player.getConstitution() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getConstitution()));
                    case ("Intelligence"):
                        player.setIntelligence(player.getIntelligence() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getIntelligence()));
                    case ("Wisdom"):
                        player.setWisdom(player.getWisdom() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getWisdom()));
                    case ("Charisma"):
                        player.setCharisma(player.getCharisma() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getCharisma()));
                        skillpoints.setText(String.valueOf(player.getAvailableSkillPoints()));
                }
            }
        }
    }

    public PlayerStatWindow(Player player) {
        super("Player statistics");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());

        attrs = new JPanel(new GridLayout());
        strength = new JButton("Strength");
        strength.addActionListener(new Listener());
        attrs.add(strength);
        strengthValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(strengthValue);
        dexterity = new JButton("Dexterity");
        dexterity.addActionListener(new Listener());
        attrs.add(dexterity);
        dexterityValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(dexterityValue);
        constitution = new JButton("Constitution");
        constitution.addActionListener(new Listener());
        attrs.add(constitution);
        constitutionValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(constitutionValue);
        intelligence = new JButton("Intelligence");
        intelligence.addActionListener(new Listener());
        attrs.add(intelligence);
        intelligenceValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(intelligenceValue);
        wisdom = new JButton("Wisdom");
        wisdom.addActionListener(new Listener());
        attrs.add(wisdom);
        wisdomValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(wisdomValue);
        charisma = new JButton("Charisma");
        charisma.addActionListener(new Listener());
        attrs.add(charisma);
        charismaValue = new JButton(String.valueOf(player.getStrength()));
        attrs.add(charismaValue);

        add(attrs);
        JLabel skillpoints = new JLabel(String.valueOf(player.getAvailableSkillPoints()));
        add(skillpoints);

        setVisible(true);

    }

}
