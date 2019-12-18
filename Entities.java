public abstract class Entities
{
    // Stats related to entities by Damage, Max Health Points, Health Points, Speed, and Temporary Speed
    private int maxHealth;
    private int healthPoints;
    private int speed;
    private int tempSpeed;
    
    public Entities(int hp, int s)
    {
        maxHealth = hp;
        healthPoints = hp;
        speed = s;
        tempSpeed = s;
    }

    public void changeMaxHealth(int health)
    {
        maxHealth = health;
    }
    
    public void changeHealth(int tempHealth)
    {
        healthPoints = tempHealth;
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    public int getHealth()
    {
        return healthPoints;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void setSpeed(int s)
    {
        speed = s;
    }
    
    public int getTempSpeed()
    {
        return tempSpeed;
    }
    
    public void setTempSpeed(int ts)
    {
        tempSpeed = ts;
    }
}