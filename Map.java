/*
 * This class creates the dungeon for each floor and puts
 */
public class Map
{
    public static Room[][] floor = new Room[5][5];
    private static int startingRow;
    private static int startingCol;
    private static boolean fullVision = false;

    public static int getStartingRow()
    {
        return startingRow;
    }

    public static int getStartingCol()
    {
        return startingCol;
    }

    public static boolean getFullVision()
    {
        return fullVision;
    }

    public static void setFullVision(boolean b)
    {
        fullVision = b;
    }

    /*
     *           ROOM KEY:
     *               Does not exist
     *               Starting room
     *               Fight room
     *               Event room
     *               Shop
     *               Item room
     *               Boss room
     */

    // This method randomly creates the flooor for each level.
    public static void createFloor()
    {
        // Creates starting room
        startingRow = (int)(Math.random()*3)+1; 
        startingCol = (int)(Math.random()*3)+1;
        floor[startingRow][startingCol]=new StartingRoom(startingRow,startingCol);
        int nonSpecialRoom = 0;
        int bossRoom = 0;
        int itemRoom = 0;
        int shopRoom = 0;
        int tempRow = 0;
        int tempCol = 0;
        int adjacentRooms = 0;

        // Creates Non-special Rooms (Starting Room, Fight Room, and Event Room)
        while (nonSpecialRoom<8)
        {
            tempRow = (int)(Math.random()*5);
            tempCol = (int)(Math.random()*5);
            if (isAdjacent(tempRow,tempCol) && floor[tempRow][tempCol]==null)
            {
                if ((int)(Math.random()*2)==0)
                {
                    floor[tempRow][tempCol] = new FightRoom(tempRow,tempCol);
                }
                else
                {
                    floor[tempRow][tempCol] = new EventRoom(tempRow,tempCol);
                }
                nonSpecialRoom++;
            }
        }
        // Creates Special Rooms (Boss Room, Item Room, Shop)
        while (bossRoom==0)
        {
            tempRow = (int)(Math.random()*5);
            tempCol = (int)(Math.random()*5);
            if (adjacentCount(tempRow,tempCol)==1 && !(isAdjacentStarter(tempRow,tempCol)) && floor[tempRow][tempCol]==null)
            {
                floor[tempRow][tempCol] = new BossRoom(tempRow,tempCol);
                bossRoom++;
            }
        }
        while (itemRoom==0)
        {
            tempRow = (int)(Math.random()*5);
            tempCol = (int)(Math.random()*5);
            if (adjacentCount(tempRow,tempCol)==1 && !(isAdjacentStarter(tempRow,tempCol)) && !(isAdjacentSpecial(tempRow,tempCol)) && floor[tempRow][tempCol]==null)
            {
                floor[tempRow][tempCol] = new ItemRoom(tempRow,tempCol);
                itemRoom++;
            }
        }
        while (shopRoom==0)
        {
            tempRow = (int)(Math.random()*5);
            tempCol = (int)(Math.random()*5);
            if (adjacentCount(tempRow,tempCol)==1 && !(isAdjacentStarter(tempRow,tempCol)) && !(isAdjacentSpecial(tempRow,tempCol)) && floor[tempRow][tempCol]==null)
            {
                floor[tempRow][tempCol] = new Shop(tempRow,tempCol);
                shopRoom++;
            }
        }
    }

    // Checks if there is a valid room above the given room
    public static boolean checkUp(int row, int col)
    {
        if (row==0)
        {
            return false;
        }
        else
        {
            return (floor[row-1][col]!=null);
        }
    }

    //Checks if there is a valid room under the given room
    public static boolean checkDown(int row, int col)
    {
        if (row==4)
        {
            return false;
        }
        else
        {
            return (floor[row+1][col]!=null);
        }
    }

    // Checks if there is a valid room on the left of the given room
    public static boolean checkLeft(int row, int col)
    {
        if (col==0)
        {
            return false;
        }
        else
        {
            return (floor[row][col-1]!=null);
        }
    }

    // Checks if there is a valid room on the right of the given room
    public static boolean checkRight(int row, int col)
    {
        if (col==4)
        {
            return false;
        }
        else
        {
            return (floor[row][col+1]!=null);
        }
    }

    public static Room getUp(int r, int c)
    {
        if (r!=0)
        {
            return (floor[r-1][c]);
        }
        else
        {
            return null;
        }
    }

    public static Room getDown(int r, int c)
    {
        if (r!=4)
        {
            return (floor[r+1][c]);
        }
        else
        {
            return null;
        }
    }

    public static Room getLeft(int r, int c)
    {
        if (c!=0)
        {
            return (floor[r][c-1]);
        }
        else
        {
            return null;
        }
    }

    public static Room getRight(int r, int c)
    {
        if (c!=4)
        {
            return (floor[r][c+1]);
        }
        else
        {
            return null;
        }
    }

    // isAdjacentStarter is used to check if it is adjacent to the starting room. 
    //Used to spawn special rooms away from starting room.
    public static boolean isAdjacentStarter(int row, int col)
    {
        boolean adjacentStarter = false;
        if (checkUp(row,col))
        {
            if (floor[row-1][col] instanceof StartingRoom)
            {
                adjacentStarter = true;
            }
        }
        if (checkDown(row,col))
        {
            if (floor[row+1][col] instanceof StartingRoom)
            {
                adjacentStarter = true;
            }
        }
        if (checkLeft(row,col))
        {
            if (floor[row][col-1] instanceof StartingRoom)
            {
                adjacentStarter = true;
            }
        }
        if (checkRight(row,col))
        {
            if (floor[row][col+1] instanceof StartingRoom)
            {
                adjacentStarter = true;
            }
        }
        return adjacentStarter;
    }

    public static boolean isAdjacentSpecial(int row, int col)
    {
        boolean adjacentSpecial = false;
        if (checkUp(row,col))
        {
            if (floor[row-1][col] instanceof BossRoom || floor[row-1][col] instanceof ItemRoom || floor[row-1][col] instanceof Shop)
            {
                adjacentSpecial = true;
            }
        }
        if (checkDown(row,col))
        {
            if (floor[row+1][col] instanceof BossRoom || floor[row+1][col] instanceof ItemRoom || floor[row+1][col] instanceof Shop)
            {
                adjacentSpecial = true;
            }
        }
        if (checkLeft(row,col))
        {
            if (floor[row][col-1] instanceof BossRoom || floor[row][col-1] instanceof ItemRoom || floor[row][col-1] instanceof Shop)
            {
                adjacentSpecial = true;
            }
        }
        if (checkRight(row,col))
        {
            if (floor[row][col+1] instanceof BossRoom || floor[row][col+1] instanceof ItemRoom || floor[row][col+1] instanceof Shop)
            {
                adjacentSpecial = true;
            }
        }
        return adjacentSpecial;
    }

    public static boolean isAdjacentBossRoom(int row, int col)
    {
        boolean adjacentBoss = false;
        if (checkUp(row,col))
        {
            if (floor[row-1][col] instanceof BossRoom)
            {
                adjacentBoss = true;
            }
        }
        if (checkDown(row,col))
        {
            if (floor[row+1][col] instanceof BossRoom)
            {
                adjacentBoss = true;
            }
        }
        if (checkLeft(row,col))
        {
            if (floor[row][col-1] instanceof BossRoom)
            {
                adjacentBoss = true;
            }
        }
        if (checkRight(row,col))
        {
            if (floor[row][col+1] instanceof BossRoom)
            {
                adjacentBoss = true;
            }
        }
        return adjacentBoss;
    }

    public static boolean isAdjacentItemRoom(int row, int col)
    {
        boolean adjacentItem = false;
        if (checkUp(row,col))
        {
            if (floor[row-1][col] instanceof ItemRoom)
            {
                adjacentItem = true;
            }
        }
        if (checkDown(row,col))
        {
            if (floor[row+1][col] instanceof ItemRoom)
            {
                adjacentItem = true;
            }
        }
        if (checkLeft(row,col))
        {
            if (floor[row][col-1] instanceof ItemRoom)
            {
                adjacentItem = true;
            }
        }
        if (checkRight(row,col))
        {
            if (floor[row][col+1] instanceof ItemRoom)
            {
                adjacentItem = true;
            }
        }
        return adjacentItem;
    }

    public static boolean isAdjacentShop(int row, int col)
    {
        boolean adjacentShop = false;
        if (checkUp(row,col))
        {
            if (floor[row-1][col] instanceof Shop)
            {
                adjacentShop = true;
            }
        }
        if (checkDown(row,col))
        {
            if (floor[row+1][col] instanceof Shop)
            {
                adjacentShop = true;
            }
        }
        if (checkLeft(row,col))
        {
            if (floor[row][col-1] instanceof Shop)
            {
                adjacentShop = true;
            }
        }
        if (checkRight(row,col))
        {
            if (floor[row][col+1] instanceof Shop)
            {
                adjacentShop = true;
            }
        }
        return adjacentShop;
    }

    public static boolean isAdjacent(int row, int col)
    {
        return (checkUp(row,col)||checkDown(row,col)||checkLeft(row,col)||checkRight(row,col));
    }

    public static int adjacentCount(int row, int col)
    {
        int count = 0;
        if (checkUp(row,col))
        {
            count++;
        }
        if (checkDown(row,col))
        {
            count++;
        }
        if (checkLeft(row,col))
        {
            count++;
        }
        if (checkRight(row,col))
        {
            count++;
        }
        return count;
    }

    public static void printFloor(Character player)
    {
        // This version of printing the floor is only here for testing purposes. It'll either
        // be removed or kept in as a cheat code later as it shows the whole floor.
        for (int r=0; r<floor.length; r++)
        {
            for (int c=0; c<floor[0].length; c++)
            {
                if (fullVision == true)
                {
                    if (floor[r][c] == floor[player.getRow()][player.getCol()])
                    {
                        System.out.print("P ");
                    }
                    else if (floor[r][c] instanceof StartingRoom)
                    {
                        System.out.print("S ");
                    }
                    else if (floor[r][c] instanceof BossRoom)
                    {
                        System.out.print("B ");
                    }
                    else if (floor[r][c] instanceof ItemRoom)
                    {
                        System.out.print("I ");
                    }
                    else if (floor[r][c] instanceof Shop)
                    {
                        System.out.print("$ ");
                    }
                    else if (floor[r][c] instanceof FightRoom)
                    {
                        System.out.print("F ");
                    }
                    else if (floor[r][c] instanceof EventRoom)
                    {
                        System.out.print("E ");
                    }
                    else
                    {
                        System.out.print("  ");
                    }
                }
                else
                {
                    if (floor[r][c] == floor[player.getRow()][player.getCol()])
                    {
                        System.out.print("P ");
                    }
                    else if (floor[r][c] instanceof StartingRoom)
                    {
                        System.out.print("S ");
                    }
                    else if ((isAdjacentBossRoom(player.getRow(),player.getCol())==true && floor[r][c] instanceof BossRoom) || (floor[r][c] instanceof BossRoom && floor[r][c].getBeenBefore()==true))
                    {
                        System.out.print("B ");
                    }
                    else if ((isAdjacentItemRoom(player.getRow(),player.getCol())==true && floor[r][c] instanceof ItemRoom) || (floor[r][c] instanceof ItemRoom && floor[r][c].getBeenBefore()==true))
                    {
                        System.out.print("I ");
                    }
                    else if ((isAdjacentShop(player.getRow(),player.getCol())==true && floor[r][c] instanceof Shop) || (floor[r][c] instanceof Shop &&floor[r][c].getBeenBefore()==true))
                    {
                        System.out.print("$ ");
                    }
                    else if (floor[r][c] instanceof FightRoom && floor[r][c].getBeenBefore()==true)
                    {
                        System.out.print("F ");
                    }
                    else if (floor[r][c] instanceof EventRoom && floor[r][c].getBeenBefore()==true)
                    {
                        System.out.print("E ");
                    }
                    else if (isAdjacent(player.getRow(),player.getCol())==true && floor[r][c] instanceof FightRoom || floor[r][c] instanceof EventRoom)
                    {
                        System.out.print("? ");
                    }
                    else
                    {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }
}