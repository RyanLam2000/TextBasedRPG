public abstract class PassiveItems extends Items
{   
    private boolean constantUse; // constantUse means that the passive item is continuously added unlike a stat change
    //private boolean used = false; // if the passive item is a stat change, used changes from false to true so it can't be used multiple times
    
    public PassiveItems(String name,String desc, boolean cU, boolean b)
    {
        super(name,desc,b);
        constantUse = cU;
    }
    
    public boolean getConstantUse()
    {
        return constantUse;
    }
    
    public void setConstantUse(boolean b)
    {
        constantUse = b;
    }
    
    // // public boolean getUsed()
    // // {
        // // return used;
    // // }
    
    // public void setUsed(boolean b)
    // {
        // used = b;
    // }
    
    public abstract void effect(Character p);
}