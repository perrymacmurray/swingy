package com.perry.swingy;

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

        if (e.getSource() instanceof SwingyButton source) {
            SwingyClick click = new SwingyClick(source);
            click.setX(source.getXCoord());
            click.setY(source.getYCoord());

            reportedEvent = click;
        }
        else if (e.getSource() instanceof JTextField source) {
            SwingyUserInput input = new SwingyUserInput(source);
            input.setInput(source.getText());

            source.setText("");
            System.out.println("-> " + input.getInputString());

            reportedEvent = input;
        }

        this.parent.reportEvent(reportedEvent);
    }
}
