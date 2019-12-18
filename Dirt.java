public class Dirt extends PassiveItems
{
    /*
     * The whole point of the dirt item is to create a prevention in the case that someone the player gains EVERY ITEM in the game.
     * Any event where the player gains an item after obtaining all of them will result in the player gaining dirt. It does
     * absolutely nothing but congrats to the person who managed to get all the items.
     */
    
    public Dirt()
    {
        super("Dirt","It's just dirt.",false,false);
    }
    
    public void effect(Character p)
    {
        // Yes Dirt does absolutely nothing.
    }
}