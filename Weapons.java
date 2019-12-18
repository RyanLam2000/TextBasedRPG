public abstract class Weapons extends Items
{
    private int dmg;
    
    public Weapons(String name,String desc, int damage, boolean b)
    {
        super(name,desc,b);
        dmg = damage;
    }
    
    public int getDmg()
    {
        return dmg;
    }
}