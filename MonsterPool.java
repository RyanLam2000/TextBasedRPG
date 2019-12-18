import java.util.ArrayList;

public class MonsterPool
{
    private static ArrayList<Monster> floorOneMobs = new ArrayList<Monster>();
    private static ArrayList<Monster> floorOneBosses = new ArrayList<Monster>();
    //private static ArrayList<Monster> floorTwoMobs = new ArrayList<Monster>();
    //private static ArrayList<Monster> floorTwoBosses = new ArrayList<Monster>();

    public static void fillFloorOneMobs()
    {
        floorOneMobs.add(new Slime());
        floorOneMobs.add(new Whale());
        floorOneMobs.add(new Bandit());
    }

    public static void fillFloorOneBosses()
    {
        floorOneBosses.add(new GiantEnemyCrab());
    }

    public static Monster spawnMob()
    {
        if (Launcher.getFloor()==1)
        {
            int monsterChoice = (int)(Math.random()*floorOneMobs.size());
            Monster monsterPicked = floorOneMobs.get(monsterChoice);
            if (monsterPicked instanceof Slime)
            {
                return new Slime();
            }
            else if (monsterPicked instanceof Whale)
            {
                return new Whale();
            }
            else if (monsterPicked instanceof Bandit)
            {
                return new Bandit();
            }
        }
        return new MissingMob();
    }

    public static Monster spawnBoss()
    {
        if (Launcher.getFloor()==1)
        {
            int bossChoice = (int)(Math.random()*floorOneBosses.size());
            Monster monsterPicked = floorOneBosses.get(bossChoice);
            return monsterPicked;
        }
        else
        {
            return new MissingMob();
        }
    }
}