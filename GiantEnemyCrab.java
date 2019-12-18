public class GiantEnemyCrab extends Monster
{
    public GiantEnemyCrab()
    {
        super("Giant Enemy Crab", 4, 20, 8);
    }

    public void attack(Character player)
    {
        boolean angry = false;
        if (getHealth()<(int)(getMaxHealth()*0.5))
        {
            if (angry == false)
            {
                System.out.println(getMonName() + " is getting angry. It's starting to hit harder!");
                angry = true;
            }
            player.changeHealth(player.getHealth() - 6);
        }
        else
        {
            super.attack(player); 
        }
    }
}