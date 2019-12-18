public class WingedBoots extends PassiveItems
{
    public WingedBoots()
    {
        super("Winged Boots", "Lighter feet", false, false);
    }
    
    public void effect(Character p)
    {
        p.setSpeed(p.getSpeed()+3);
    }
}