package GUI;

import Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerStatWindow extends JFrame {
    private Player player;

    JPanel attrs;
    JButton HP;
    JButton HPValue;
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

    JLabel skillPoints;

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            if (Integer.parseInt(skillPoints.getText()) > 0) {
                switch (name) {
                    case ("Strength"):
                        player.setStrength(player.getStrength() + 1);
                        player.addSkillPoints(-1);
                        strengthValue.setText(String.valueOf(player.getStrength()));
                        break;
                    case ("Dexterity"):
                        player.setDexterity(player.getDexterity() + 1);
                        player.addSkillPoints(-1);
                        dexterityValue.setText(String.valueOf(player.getDexterity()));
                        break;
                    case ("Constitution"):
                        player.setConstitution(player.getConstitution() + 1);
                        player.addSkillPoints(-1);
                        constitutionValue.setText(String.valueOf(player.getConstitution()));
                        break;
                    case ("Intelligence"):
                        player.setIntelligence(player.getIntelligence() + 1);
                        player.addSkillPoints(-1);
                        intelligenceValue.setText(String.valueOf(player.getIntelligence()));
                        break;
                    case ("Wisdom"):
                        player.setWisdom(player.getWisdom() + 1);
                        player.addSkillPoints(-1);
                        wisdomValue.setText(String.valueOf(player.getWisdom()));
                        break;
                    case ("Charisma"):
                        player.setCharisma(player.getCharisma() + 1);
                        player.addSkillPoints(-1);
                        charismaValue.setText(String.valueOf(player.getCharisma()));
                        break;
                }
                skillPoints.setText(String.valueOf(player.getAvailableSkillPoints()));
                PlayerStatWindow.this.revalidate();
                PlayerStatWindow.this.repaint();
            }
        }
    }

    public PlayerStatWindow(Player player) {
        super("Player statistics");
        this.player = player;
        setSize(280, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());

        attrs = new JPanel(new GridLayout(7, 2));
        HP = new JButton("HP");
        attrs.add(HP);
        HPValue = new JButton(String.valueOf(player.getHitPoints()));
        attrs.add(HPValue);
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

        skillPoints = new JLabel(String.valueOf(this.player.getAvailableSkillPoints()));
        skillPoints.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 47));
        skillPoints.setSize(new Dimension(100, 100));
        skillPoints.setHorizontalAlignment(SwingConstants.CENTER);
        add(skillPoints);

        this.setLocationRelativeTo(null);
    }

}
