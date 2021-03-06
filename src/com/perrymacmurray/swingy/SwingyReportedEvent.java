package com.perrymacmurray.swingy;

public abstract class SwingyReportedEvent
{
    private final Object source;

    protected SwingyReportedEvent(Object source) {
        this.source = source;
    }

    public Object getSource()
    {
        return source;
    }
}
