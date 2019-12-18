import java.util.Scanner;

public class ItemRoom extends Room
{
    private Items itm;

    public ItemRoom(int r, int c)
    {
        super(r,c);
        itm = ItemPool.spawnItem();
    }

    public void action(Character p)
    {
        Scanner kb = new Scanner(System.in);
        boolean eventRunning = true;

        while (eventRunning)
        {
            System.out.println("You found a " + itm.getItemName() + "!");
            System.out.print("Do you wish to take it? (yes/no) ");
            String choice = kb.next();
            if (choice.equalsIgnoreCase("yes"))
            {
                if (itm instanceof Weapons)
                {
                    p.addWeapon((Weapons)itm);
                    System.out.println(p.getWeaponsList().get(0).getItemDesc()+"\n");
                }
                else if (itm instanceof PassiveItems)
                {
                    p.addPassive((PassiveItems)itm);
                    if (p.getPassivesList().get(0).getConstantUse() == false)
                    {
                        p.getPassivesList().get(0).effect(p);
                    }
                    System.out.println(p.getPassivesList().get(0).getItemDesc()+"\n");
                }
                setBeenBefore(true);
                eventRunning = false;
            }
            // This allows the player to leave the room and come back to
            // pickup the item if they so wish to do so.
            else if (choice.equalsIgnoreCase("no"))
            {
                System.out.println("You decided to pass on the item.\n");
                eventRunning = false;
            }
            else
            {
                System.out.println("Just pick yes or no already and move on!");
            }
        }
    }
}