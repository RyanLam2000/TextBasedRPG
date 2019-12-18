public class HeartContainer extends PassiveItems
{
    public HeartContainer()
    {
        super("Heart Container","Do I smash this like a pot?",false,false);
    }
    
    public void effect(Character p)
    {
        p.changeMaxHealth(p.getHealth()+3);
        p.changeHealth(p.getMaxHealth());
    }
}