import java.util.ArrayList;

public class ItemPool
{
    private static ArrayList<Weapons> weaponPool = new ArrayList<Weapons>();
    private static ArrayList<PassiveItems> passivePool = new ArrayList<PassiveItems>();

    public static void fillWeaponPool()
    {
        weaponPool.add(new StoneSword());
    }

    public static void fillPassivePool()
    {
        passivePool.add(new Mushroom());
        passivePool.add(new WingedBoots());
        passivePool.add(new PowerBand());
        passivePool.add(new HeartContainer());
    }

    /*
     * Spawns the item for either Event Rooms or Item Rooms. It takes the item
     * from a pool of items and then when it spawns, it removes the item from
     * it's associated pool so that the item can not spawn again. In the case
     * that all the pools are empty (as in someone managed to get all the items)
     * then the game spawns the passive item Dirt that does absolutely nothing
     * but acts as a placeholder to prevent the game from breaking.
     */
    public static Items spawnItem()
    {
        boolean weaponPoolEmpty = weaponPool.size()==0;
        boolean passivePoolEmpty = passivePool.size()==0;
        if (!(weaponPoolEmpty) && !(passivePoolEmpty))
        {
            int choosePool = (int)(Math.random()*2);
            if (choosePool == 0)
            {
                return spawnWep();
            }
            else if (choosePool == 1)
            {
                return spawnPassive();
            }
        }
        else if (!(weaponPoolEmpty) && passivePoolEmpty)
        {
            return spawnWep();
        }
        else if (weaponPoolEmpty && !passivePoolEmpty)
        {
            return spawnPassive();
        }
        return new Dirt();
    }

    public static Weapons spawnWep()
    {
        int weaponPoolLoc = (int)(Math.random()*weaponPool.size());
        Weapons weaponFound = weaponPool.remove(weaponPoolLoc);
        return weaponFound;
    }

    public static PassiveItems spawnPassive()
    {
        int passivePoolLoc = (int)(Math.random()*passivePool.size());
        PassiveItems passiveFound = passivePool.remove(passivePoolLoc);
        return passiveFound;
    }
}