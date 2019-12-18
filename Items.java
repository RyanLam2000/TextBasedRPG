import java.util.ArrayList;

public abstract class Items
{
    private String itemName;
    private String itemDesc;
    private boolean hasSeen;// Used to prevent an item from showing up twice in a run

    public Items(String name,String desc,boolean b)
    {
        itemName = name;
        itemDesc = desc;
        hasSeen = b;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public String getItemDesc()
    {
        return itemDesc;
    }

    public boolean getHasSeen()
    {
        return hasSeen;
    }

    public void changeHasSeen(boolean b)
    {
        hasSeen = b;
    }
}