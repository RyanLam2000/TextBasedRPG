public class Mushroom extends PassiveItems
{
    public Mushroom()
    {
        super("Mushroom","Something to eat and bring through pipes!",false,false);
    }

    public void effect(Character p)
    {
        // if (getUsed()==false)
        // {
        p.changeMaxHealth(p.getMaxHealth()+5);
        // setUsed(true);
        // }
    }
}