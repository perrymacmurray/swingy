package com.perrymacmurray.swingy;

public class SwingyClick extends SwingyReportedEvent
{
    private int x, y;

    protected SwingyClick(Object source)
    {
        super(source);
    }

    public int getX()
    {
        return x;
    }

    protected void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    protected void setY(int y)
    {
        this.y = y;
    }
}
