import java.util.Scanner;

public class EventRoom extends Room
{
    public EventRoom (int r, int c)
    {
        super(r,c);
    }

    public void action(Character p)
    {
        Scanner kb = new Scanner(System.in);
        if (getBeenBefore() == false)
        {
            int eventNum = (int)(Math.random()*2);
            boolean eventRunning = true;
            switch(eventNum)
            {
                case 0:
                System.out.println("You see a statue of a sword covered in blood. Scattered around ");
                System.out.println("are old skeletons of adventurers just like you. On the statue ");
                System.out.println("is a plaque that reads \"Accept my blessing and I will grant");
                System.out.println("you power for a price.\"\n");
                while (eventRunning)
                {
                    System.out.print("Do you accept the statue's offer? (yes/no) ");
                    String choice = kb.next();
                    if (choice.equalsIgnoreCase("yes"))
                    {
                        System.out.println("YES! SOMEONE WHO TAKES MY POWER!");
                        p.setDmgMult(p.getDmgMult()+5);
                        p.changeHealth(1);
                        System.out.println("You feel a rush of power surge through your body but the strain put on you brings you near death.\n");
                        eventRunning = false;
                    }
                    else if (choice.equalsIgnoreCase("no"))
                    {
                        System.out.println("Very well.\n");
                        eventRunning = false;
                    }
                    else
                    {
                        System.out.println("Stop wasting my time and answer yes or no already!\n");
                    }
                }
                break;
                case 1:
                System.out.println("You come across a chest sitting in the middle of the room. It's a bit dusty but it seems good enough!\n");
                while (eventRunning)
                {
                    System.out.print("Do you wish to open the chest? (yes/no) ");
                    String choice = kb.next();
                    if (choice.equalsIgnoreCase("yes"))
                    {
                        int mimicChance = (int)(Math.random()*10);
                        if (mimicChance == 9)
                        {
                            Items itm = ItemPool.spawnItem();
                            System.out.println("You found a " + itm.getItemName() + "!");
                            System.out.print("Do you wish to take it? (yes/no) ");
                            String choice2 = kb.next();
                            if (choice2.equalsIgnoreCase("yes"))
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
                                eventRunning = false;
                            }
                            else if (choice2.equalsIgnoreCase("no"))
                            {
                                System.out.println("You leave the chest as it disappears into the dark.\n");
                                eventRunning = false;
                            }
                            else
                            {
                                System.out.println("Would you stop being so indecisive and pick yes or no already?");
                            }
                        }
                        else
                        {
                            System.out.println("The chest turns out to be a mimic and bites you back!");
                            p.changeHealth(p.getHealth()-5);
                            System.out.println("You took 5 damage and now have " + p.getHealth() + " health.\n");
                            eventRunning = false;
                        }
                    }
                    else if (choice.equalsIgnoreCase("no"))
                    {
                        System.out.println("You leave the chest as it disappears into the dark.\n");
                        eventRunning = false;
                    }
                    else
                    {
                        System.out.println("Follow the directions and pick yes or no please!");
                    }
                }
                break;
            }
        }
        setBeenBefore(true);
    }
}