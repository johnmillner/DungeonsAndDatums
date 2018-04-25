package com.dungeonsanddatums.dungeonsanddatums.Holders;

/**
 * Created by matth on 4/23/2018.
 */

public class EquimentChoice
{
    private String choice1;
    private String choice2;

    public EquimentChoice(String choice1, String choice2)
    {
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public void setChoice1(String choice) { choice1 = choice; }

    public void setChoice2(String choice) { choice2 = choice; }
}
