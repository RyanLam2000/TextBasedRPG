import java.util.Scanner;

public class Launcher
{
    /* 
     * My final project is a rogue-like video game.
     * It was inspired by many rogue-like games such as The Binding of Isaac, Slay the Spire,
     * and Faster Than Light. The combat was inspired by Pokemon and Digimon games.
     * 
     *  TO DO LIST: (in order of priority more or less)
     *  More Items
     *  More Monsters
     *  Status Effects!
     *  Fix up how stats work (specifically changing stats during combat and then making sure they reset to their normal stats before combat)
     *  Add a second floor w/ ramped difficulty
     *  Graphics???
     */

    private static boolean gameRunning = true;
    private static int floor = 1;

    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        createPools();
        Map.createFloor();
        Character player = new Character(Map.getStartingRow(),Map.getStartingCol());
        player.addWeapon(new Fist());

        while (gameRunning)
        {
            Map.floor[player.getRow()][player.getCol()].action(player);
            if (player.getHealth()<=0)
            {
                System.out.println("You died. Game over.");
                exitGame();
                break;
            }
            Map.printFloor(player);
            player.changeRoom();
        }
    }

    public static int getFloor()
    {
        return floor;
    }
    
    // This creates every item and monster within the game so the game could pick monsters/items randomly
    public static void createPools()
    {
        ItemPool.fillWeaponPool();
        ItemPool.fillPassivePool();
        MonsterPool.fillFloorOneMobs();
        MonsterPool.fillFloorOneBosses();
    }
    
    public static void exitGame()
    {
        gameRunning = false;
    }
    
    
}