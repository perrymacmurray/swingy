package com.perry.swingy;

import javax.swing.*;

public class SwingyButton extends JButton
{
    private int x, y;

    protected SwingyButton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected int getXCoord()
    {
        return x;
    }

    protected int getYCoord()
    {
        return y;
    }
}
