package com.winnipegapp.examples;

import android.app.Application;

/**
 * Created by Christian on 8/9/2016.
 */
public class GlobalVariable extends Application
{
    private String someVariable;

    public String variableGetter()
    {
        return someVariable;
    }

    public void variableSetter(String someVariable)
    {
        this.someVariable = someVariable;
    }
}
