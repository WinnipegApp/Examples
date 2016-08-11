package com.winnipegapp.examples;

import android.app.Application;

/**
 * Created by Christian on 8/9/2016.
 */
public class GlobalVariable extends Application
{
    private String sVariable;
    private boolean bVariable;

    public String svariableGetter()
    {
        return sVariable;
    }

    public void svariableSetter(String sVariable)
    {
        this.sVariable = sVariable;
    }

    public boolean bvariableGetter()
    {
        return bVariable;
    }

    public void bvariableSetter(Boolean bVariable)
    {
        this.bVariable = bVariable;
    }


}
