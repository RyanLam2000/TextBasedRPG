public class MissingMob extends Monster
{
    /*
     * Reference to Pokemon's MissingNo. In reality this mob should NEVER
     * be spawned so if it is ever spawned, something either went wrong
     * or someone broke into the files and changed stuff.
     */
    public MissingMob()
    {
        super("MissingMob.",1,1,1);
    }
}