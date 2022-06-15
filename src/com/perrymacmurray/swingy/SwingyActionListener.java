package com.perrymacmurray.swingy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingyActionListener implements ActionListener
{
    private final SwingyWindow parent;

    protected SwingyActionListener(SwingyWindow parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        SwingyReportedEvent reportedEvent = null;

        if (e.getSource() instanceof SwingyButton) {
            SwingyButton source = (SwingyButton) e.getSource();
            SwingyClick click = new SwingyClick(source);
            click.setX(source.getXCoord());
            click.setY(source.getYCoord());

            reportedEvent = click;
        }
        else if (e.getSource() instanceof JTextField) {
            JTextField source = (JTextField) e.getSource();
            SwingyUserInput input = new SwingyUserInput(source);
            input.setInput(source.getText());

            source.setText("");
            System.out.println("-> " + input.getInputString());

            reportedEvent = input;
        }

        this.parent.reportEvent(reportedEvent);
    }
}
