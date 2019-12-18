public abstract class Room
{
    private int row;
    private int col;
    private boolean beenBefore; //This is to avoid fights that has already been done
    
    public Room (int r, int c)
    {
        row=r;
        col=c;
        beenBefore = false;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public boolean getBeenBefore()
    {
        return beenBefore;
    }
    
    public void setBeenBefore(boolean b)
    {
        beenBefore = b;
    }
    
    /*  Action of each subclass of room will be created in the subclasses. Actions refer to battles
     *  in fight rooms, events in event rooms, shop in shops, etc.
     */
    public abstract void action(Character p);
}