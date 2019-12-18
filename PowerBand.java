public class PowerBand extends PassiveItems
{
    public PowerBand()
    {
        super("Power Band","You're getting stronger!",false,false);
    }
    
    public void effect(Character p)
    {
        p.setDmgMult(p.getDmgMult()+1);
    }
}