public class StartingRoom extends Room
{
    private int row;
    private int col;

    public StartingRoom(int r, int c)
    {
        super(r,c);
    }

    // As of right now it's a bit vague but I'm thinking it should give a slight tutorial
    // while keeping it's vagueness.
    public void action(Character p)
    {
        if (getBeenBefore() == false)
        {
            System.out.println("Try getting to the end of the dungeon through any means. I heard the final boss is hard so... good luck!");
            setBeenBefore(true);
        }
    }
}