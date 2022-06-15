package com.perrymacmurray.swingy;

import javax.swing.*;
import java.awt.*;

public class SwingyWindow
{
    public static final int CANVAS_SIZE = 10;
    private boolean isInitialized = false;

    private SwingyActionListener listener;
    private SwingyReportedEvent pendingEvent = null;
    private boolean isWaiting;

    private SwingyButton[][] canvas;
    private JTextArea console;
    private JTextField consoleInput;
    private JScrollPane consoleScrollPane;

    public SwingyWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            javax.swing.SwingUtilities.invokeAndWait(this::initialize);

            SwingyUtil.changeDefaultOutput(console);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setCanvasText(int x, int y, String text) {
        canvas[x][y].setText(text);
    }

    public void fillCanvasRow(int x, String color) {
        for (int i = 0; i < CANVAS_SIZE; i++)
            setCanvasColor(x, i, color);
    }

    public void fillCanvasColumn(int y, String color) {
        for (int i = 0; i < CANVAS_SIZE; i++)
            setCanvasColor(i, y, color);
    }

    public void fillCanvas(String color) {
        for (int i = 0; i < CANVAS_SIZE; i++) {
            for (int j = 0; j < CANVAS_SIZE; j++)
                setCanvasColor(i, j, color);
        }
    }

    public void setCanvasColor(int x, int y, String color) {
        JButton button = canvas[x][y];

        switch (color.toLowerCase())
        {
            case "red":
                button.setBackground(Color.RED);
                break;
            case "green":
                button.setBackground(Color.GREEN);
                break;
            case "blue":
                button.setBackground(Color.BLUE);
                break;
            case "yellow":
                button.setBackground(Color.YELLOW);
                break;
            case "purple":
                button.setBackground(new Color(112, 41, 99));
                break;
            case "black":
                button.setBackground(Color.BLACK);
                break;
            case "white":
                button.setBackground(Color.WHITE);
                break;
            case "orange":
                button.setBackground(Color.ORANGE);
                break;
            case "cyan":
                button.setBackground(Color.CYAN);
                break;
            case "pink":
                button.setBackground(Color.PINK);
                break;
            case "grey": case"gray":
                button.setBackground(Color.GRAY);
                break;
            default:
                System.err.println("Invalid color specified");
        }
    }

    public SwingyUserInput getUserInput() {
        isWaiting = true;
        SwingyUserInput output;

        while (true) {
            try {
                if (pendingEvent != null && pendingEvent instanceof SwingyUserInput) {
                    output = (SwingyUserInput) pendingEvent;
                    break;
                }

                Thread.sleep(20);
            }
            catch (InterruptedException ignored) {}
        }

        isWaiting = false;
        pendingEvent = null;
        return output;
    }

    public SwingyClick getClick() {
        isWaiting = true;
        SwingyClick output;

        while (true) {
            try {
                if (pendingEvent != null && pendingEvent instanceof SwingyClick) {
                    output = (SwingyClick) pendingEvent;
                    break;
                }

                Thread.sleep(20);
            }
            catch (InterruptedException ignored) {}
        }

        isWaiting = false;
        pendingEvent = null;
        return output;
    }

    public SwingyReportedEvent getAnyEvent() {
        isWaiting = true;
        SwingyReportedEvent output;

        while (true) {
            try {
                if (pendingEvent != null) {
                    output = pendingEvent;
                    break;
                }

                Thread.sleep(20);
            }
            catch (InterruptedException ignored) {}
        }

        isWaiting = false;
        pendingEvent = null;
        return output;
    }

    protected void reportEvent(SwingyReportedEvent e) {
        if (e == null || !isWaiting)
            return;

        if (pendingEvent != null)
            System.err.println("Warning: inputs too fast. May have lost data!");

        pendingEvent = e;
    }

    private void initialize() {
        listener = new SwingyActionListener(this);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new JPanel());
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel canvasPanel = new JPanel(new GridLayout(CANVAS_SIZE, CANVAS_SIZE));

        canvas = new SwingyButton[CANVAS_SIZE][CANVAS_SIZE];
        for (int y = 9; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                SwingyButton button = new SwingyButton(x, y);
                button.setPreferredSize(new Dimension(60, 60));
                button.setContentAreaFilled(false);
                button.setOpaque(true);

                button.addActionListener(listener);

                canvas[x][y] = button;
                canvasPanel.add(button);
            }
        }

        JPanel consolePanel = new JPanel();
        consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));

        console = new JTextArea() {
            @Override
            public void append(String str)
            {
                super.append(str);

                if (consoleScrollPane != null) {
                    SwingUtilities.invokeLater(() -> {
                        JScrollBar scroll = consoleScrollPane.getVerticalScrollBar();
                        scroll.setValue(scroll.getMaximum());
                    });
                }
            }
        };
        console.setLineWrap(true);
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.BOLD, 14));
        consoleScrollPane = new JScrollPane(console);
        consoleScrollPane.setPreferredSize(new Dimension(600, 200));
        consolePanel.add(consoleScrollPane);

        consoleInput = new JTextField();
        consoleInput.setPreferredSize(new Dimension(600, 30));
        consoleInput.setBackground(new Color(230, 230, 230));
        consoleInput.addActionListener(listener);
        SwingUtilities.invokeLater(() -> {
            consoleInput.requestFocusInWindow();
        });
        consolePanel.add(consoleInput);

        frame.getContentPane().add(canvasPanel);
        frame.getContentPane().add(consolePanel);

        frame.pack();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        if (frame.getSize().height > screen.height) {
            int newHeight = (int) (0.9 * screen.height);
            int newWidth = (int) (frame.getSize().width * (newHeight / (double) frame.getPreferredSize().height));
            frame.setSize(newWidth, newHeight);
        }

        frame.setVisible(true);

        isInitialized = true;
    }

    protected boolean getInitialized() {
        return isInitialized;
    }
}
