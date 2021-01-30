package kata7.app;

import kata7.control.*;
import kata7.model.Block;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {

    private Block block;
    private BlockPanel blockDisplay;
    private BlockPresenter blockPresenter;
    private Map<String, Command> commands;

    public static void main(String[] args) {
	    new Main().exectute();
    }

    public Main(){
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700,722);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.commands = createCommands();
    }

    private JMenuBar toolbar() {
        JMenuBar result = new JMenuBar();
        result.setLayout(new FlowLayout(FlowLayout.CENTER));
        result.add(button("Down"));
        result.add(button("Left"));
        result.add(button("Up"));
        result.add(button("Right"));
        return result;
    }

    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(e -> commands.get(command).execute());
        return button;
    }

    private void exectute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }

    private Map<String, Command> createCommands() {
        Map<String , Command> commands = new HashMap<>();
        commands.put("Up",new UpCommand(block));
        commands.put("Down",new DownCommand(block));
        commands.put("Left",new LeftCommand(block));
        commands.put("Right",new RightCommand(block));
        return commands;
    }

}

