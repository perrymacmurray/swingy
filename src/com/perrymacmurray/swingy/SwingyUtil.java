package com.perrymacmurray.swingy;

import javax.swing.*;
import java.io.PrintStream;

public class SwingyUtil
{
    private static final PrintStream consoleOut = System.out;

    protected static void changeDefaultOutput(JTextArea console) {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void print(boolean b)
            {
                console.append(Boolean.toString(b));
                super.print(b);
            }

            @Override
            public void print(char c)
            {
                console.append(Character.toString(c));
                super.print(c);
            }

            @Override
            public void print(int i)
            {
                console.append(Integer.toString(i));
                super.print(i);
            }

            @Override
            public void print(long l)
            {
                console.append(Long.toString(l));
                super.print(l);
            }

            @Override
            public void print(float f)
            {
                console.append(Float.toString(f));
                super.print(f);
            }

            @Override
            public void print(double d)
            {
                console.append(Double.toString(d));
                super.print(d);
            }

            @Override
            public void print(char[] s)
            {
                console.append(new String(s));
                super.print(s);
            }

            @Override
            public void print(String s)
            {
                console.append(s);
                super.print(s);
            }

            @Override
            public void print(Object obj)
            {
                console.append(obj.toString());
                super.print(obj);
            }

            @Override
            public void println()
            {
                super.println();
                console.append("\n");
            }

            @Override
            public void println(boolean x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(char x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(int x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(long x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(float x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(double x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(char[] x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(String x)
            {
                super.println(x);
                console.append("\n");
            }

            @Override
            public void println(Object x)
            {
                super.println(x);
                console.append("\n");
            }
        });
    }
}
