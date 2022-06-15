package com.perrymacmurray.swingy;

public class SwingyUserInput extends SwingyReportedEvent
{
    private String input;

    protected SwingyUserInput(Object source)
    {
        super(source);
    }

    public String getInputString()
    {
        return input;
    }

    protected void setInput(String input)
    {
        this.input = input;
    }
}
