import java.util.ArrayList;
import java.util.Scanner;

public class Shop extends Room
{
    private static ArrayList<Items> itemsForSale = new ArrayList<Items>();
    private static boolean itemSpawned = false;

    public Shop(int r, int c)
    {
        super(r,c);
    }

    public void action(Character p)
    {
        Scanner kb = new Scanner(System.in);
        boolean eventRunning = true;

        if (itemSpawned == false)
        {
            for (int i=0; i<3; i++)
            {
                itemsForSale.add(ItemPool.spawnItem());
            }
            itemSpawned = true;
        }

        while (eventRunning)
        {
            System.out.println("Welcome to the shop! Here are some items for sale.");
            System.out.println("You have " + p.getGold() + ".");
            System.out.println("0. Exit Shop");
            for (int i=0; i<itemsForSale.size(); i++)
            {
                System.out.println(i+1 + ". " + itemsForSale.get(i).getItemName() + " 15 Gold");
            }
            try
            {
                System.out.print("Which would you like? ");
                int choice = kb.nextInt();
                if (choice == 0)
                {
                    System.out.println("Thank you and come again!");
                    eventRunning = false;
                }
                else
                {
                    if (p.getGold()<15)
                    {
                        System.out.println("What are you trying to do? Rob me? Come back when you have more gold!");
                    }
                    else if (itemsForSale.get(choice-1) instanceof Weapons)
                    {
                        p.setGold(p.getGold()-15);
                        System.out.println("You received " + itemsForSale.get(choice-1).getItemName() + ".");
                        System.out.println("You now have " + p.getGold() + ".");
                        p.addWeapon((Weapons)itemsForSale.remove(choice-1));
                    }
                    else if (itemsForSale.get(choice-1) instanceof PassiveItems)
                    {
                        p.setGold(p.getGold()-15);
                        System.out.println("You received " + itemsForSale.get(choice-1).getItemName() + ".");
                        System.out.println("You now have " + p.getGold() + ".");
                        p.addPassive((PassiveItems)itemsForSale.remove(choice-1));
                    }
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("What are you looking at? That's not for sale!");
            }
        }
        setBeenBefore(true);
    }
}