public abstract class Monster extends Entities
{
    private String monName; // Monster Name
    // private int monID; // Monster ID, used to randomly generate which monster spawns
    private int damage; // This is the monster's damage
    
    public Monster(String name, int d, int hp, int s)
    {
        super(hp,s);
        monName = name;
        //monID = ID;
        damage = d;
    }
    
    public String getMonName()
    {
        return monName;
    }
    
    public void attack(Character player)
    {
        player.changeHealth(player.getHealth() - damage);
    }
}