import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Character extends Entities
{
    private static int row;
    private static int col;
    private static int dmgMult = 2; // This is variable for damage multiplier.
    private static int gold = 0;
    //private ActiveItem activeItem;
    private ArrayList<Weapons> weaponsList = new ArrayList<Weapons>();
    private ArrayList<PassiveItems> passivesList = new ArrayList<PassiveItems>();

    public Character(int r, int c)
    {
        super(30,15);
        row = r;
        col = c;
    }

    public static int getRow()
    {
        return row;
    }

    public static int getCol()
    {
        return col;
    }

    public ArrayList<Weapons> getWeaponsList()
    {
        return weaponsList;
    }
    
    public ArrayList<PassiveItems> getPassivesList()
    {
        return passivesList;
    }
    
    public static int getDmgMult()
    {
        return dmgMult;
    }
    
    public static void setDmgMult(int newDmgMult)
    {
        dmgMult = newDmgMult;
    }
    
    public static int getGold()
    {
        return gold;
    }
    
    public static void setGold(int g)
    {
        gold = g;
    }

    public static void goLeftRoom()
    {
        if (Map.checkLeft(getRow(),getCol()))
        {
            col = getCol()-1;
        }
        else
        {
            System.out.println("You stare at a wall and question your sanity.");
        }
    }

    public static void goRightRoom()
    {
        if (Map.checkRight(getRow(),getCol()))
        {
            col = getCol()+1;
        }
        else
        {
            System.out.println("You stare at a wall and question your sanity.");
        }
    }

    public static void goUpRoom()
    {
        if (Map.checkUp(getRow(),getCol()))
        {
            row = getRow()-1;
        }
        else
        {
            System.out.println("You stare at a wall and question your sanity.");
        }
    }

    public static void goDownRoom()
    {
        if (Map.checkDown(getRow(),getCol()))
        {
            row = getRow()+1;
        }
        else
        {
            System.out.println("You stare at a wall and question your sanity.");
        }
    }

    // This method allows the player to navigate through the floor by letting the player change rooms
    public static void changeRoom()
    {
        Scanner kb = new Scanner(System.in);
        int choice = 0;
        boolean isDone = false;

        while (!isDone)
        {
            System.out.println("Which direction would you like to head to?");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Left");
            System.out.println("4. Right");
            System.out.print("Input choice: ");

            try
            {
                choice = kb.nextInt();
            }
            catch(InputMismatchException e)
            {
                String input = kb.nextLine();
                if (input.equalsIgnoreCase("Exit"))
                {
                    Launcher.exitGame();
                    break;
                }
                else if (input.equalsIgnoreCase("Mr. Stark, I don't feel so good"))
                {
                    System.out.println("I'm sorry.");
                    Launcher.exitGame();
                    break;
                }
                else if (input.equalsIgnoreCase("gimmeTheMoney"))
                {
                    System.out.println("Here's some gold!");
                    gold = 9999;
                }
                else if (input.equalsIgnoreCase("Die die die"))
                {
                    dmgMult = 9999;
                }
                else if (input.equalsIgnoreCase("fullVision"))
                {
                    Map.setFullVision(true);
                }
            }
            if (choice<1 || choice>4)
            {
                System.out.println("That's not an option. Choose a direction using 1-4.");
            }
            else
            {
                isDone = true;
            }
        }
        switch(choice)
        {
            case 1:
            goUpRoom();
            break;
            case 2:
            goDownRoom();
            break;
            case 3:
            goLeftRoom();
            break;
            case 4:goRightRoom();
            break;
        }
    }

    // addWeapon method adds another weapon and puts it in the beginning of the list so that new items show up on top during combat
    public void addWeapon(Weapons w)
    {
        weaponsList.add(0,w);
        w.changeHasSeen(true);
    }
    
    public void addPassive(PassiveItems p)
    {
        passivesList.add(0,p);
        p.changeHasSeen(true);
    }

    public void weaponSelection()
    {
        System.out.println("Which weapon shall you choose?");
        for (int i=0; i<weaponsList.size(); i++)
        {
            System.out.println(i+1 + ". " + weaponsList.get(i).getItemName()); 
        }
    }

    public void attack(Monster mob, int choice)
    {
        mob.changeHealth(mob.getHealth() - ((weaponsList.get(choice-1)).getDmg()*dmgMult));
    }

}