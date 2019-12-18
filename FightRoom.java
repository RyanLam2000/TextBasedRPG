import java.util.Scanner;

public class FightRoom extends Room
{
    private Monster mon;
    /* 
     * moveCount determines if a player or monster gets more than one move before
     * the other entity can make a move. This is determined based on the speeds of
     * each entity. For example if the player has double the monster speed, the player
     * can make two moves before the monster is able to make one move.
     */
    private int moveCount;
    private int turnCount;
    private int choice;
    private boolean isBossRoom = false;

    public FightRoom(int r, int c)
    {
        super(r,c);
    }

    public void setIsBossRoom(boolean b)
    {
        isBossRoom = b;
    }

    public void action(Character p)
    {
        if (getBeenBefore() == false)
        {
            if (isBossRoom == false)
            {
                mon = MonsterPool.spawnMob();
            }
            else
            {
                mon = MonsterPool.spawnBoss();
            }
            System.out.println("You found a " + mon.getMonName() + "! Get ready, it's coming for you!");
            System.out.println(mon.getMonName() + " has " + mon.getHealth() + " health.");
            fight:
            while (p.getHealth()>0 || mon.getHealth()>0)
            {
                // Player is attacking first since player is faster.
                if (p.getTempSpeed()>=mon.getTempSpeed())
                {
                    moveCount = p.getTempSpeed()/mon.getTempSpeed();
                    while (moveCount>0)
                    {
                        playerMove(p);
                        System.out.println(mon.getMonName() + " has " + mon.getHealth() + " health.");
                        moveCount--;
                        if (mon.getHealth()<=0)
                        {
                            System.out.println(mon.getMonName() + " has been defeated!");
                            break fight;
                        }
                    }
                    p.setTempSpeed(p.getSpeed());
                    mon.attack(p);
                    System.out.println("You've been hit! You have " + p.getHealth() + " health left!");
                    if (p.getHealth()<=0)
                    {
                        System.out.println("Player has been slain.");
                        break fight;
                    }
                }
                // Monster is attacking first since it is faster
                else if (mon.getTempSpeed()>p.getTempSpeed())
                {
                    moveCount = mon.getTempSpeed()/p.getTempSpeed();
                    while (moveCount>0)
                    {
                        mon.attack(p);
                        System.out.println("You've been hit! You have " + p.getHealth() + " health left!");
                        moveCount--;
                        if (p.getHealth()<=0)
                        {
                            System.out.println("Player has been slain.");
                            break fight;
                        }
                    }
                    mon.setTempSpeed(mon.getSpeed());
                    playerMove(p);
                    System.out.println(mon.getMonName() + " has " + mon.getHealth() + " health.");
                    if (mon.getHealth()<=0)
                    {
                        System.out.println(mon.getMonName() + " has been defeated!");
                        break fight;
                    }
                }
                turnCount++;
            }
            if (p.getHealth()>0)
            {
                foundGold(p,mon);
                foundHealth(p,mon);
            }
            setBeenBefore(true);
            // After Boss Battle it ends the game
            if (isBossRoom == true)
            {
                Launcher.exitGame();
            }
        }
    }

    // playerMove puts the whole player weapon selection and attacking in one method
    public void playerMove(Character p)
    {
        Scanner kb = new Scanner(System.in);
        boolean selectingWeapon = true;

        while (selectingWeapon == true)
        {
            try
            {
                p.weaponSelection();
                choice = kb.nextInt();
                p.attack(mon, choice);
                selectingWeapon = false;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Stop trying to pull a weapon out of nowhere!");
            }
        }
    }

    /*
     * foundHealth randomly decides if the player finds some healing after each combat
     */
    public void foundHealth(Character p, Monster mon)
    {
        int healthChance;
        if (isBossRoom == false)
        {
            healthChance = ((int)(Math.random()*3))+1;
        }
        else
        {
            healthChance = 3;
        }
        if (healthChance==3)
        {
            System.out.println(mon.getMonName() + " seemed to drop a mysterious red orb. It looks oddly tasty so you ate it.");
            int healthGain;
            if (isBossRoom == false)
            {
                healthGain = (((int)(Math.random()*3))+3);
            }
            else
            {
                healthGain = (((int)(Math.random()*3))+9);
            }
            if (p.getHealth() + healthGain > p.getMaxHealth())
            {
                healthGain = p.getMaxHealth() - p.getHealth();
            }
            p.changeHealth(p.getHealth() + healthGain);
            System.out.println("You feel refreshed! You healed " + healthGain + " health. You now have " + p.getHealth() + " health.\n");
        }
    }

    /*
     * foundGold occurs after the fight and if the player is still alive. It is used to determine how much gold a player gets after each fight.
     */
    public void foundGold(Character p, Monster mon)
    {
        System.out.println(mon.getMonName() + " left behind some gold!");
        int goldGain;
        if (isBossRoom == true)
        {
            goldGain = (int)(Math.random()*5)+8;
        }
        else
        {
            goldGain = (int)(Math.random()*3)+2;
        }
        p.setGold(p.getGold()+goldGain);
        System.out.println("You picked up " + goldGain + " gold. You now have " + p.getGold() + " gold.\n");
    }
}