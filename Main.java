import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Erez on 10/30/2016.
 * First Genetic Algorithm attempt
 * Does not really fully illustrate the concept
 * This is where it all began though...
 */
public class Main
{
    public static final String GOAL = "Hello, World!";
    public static int goalFitness = getFitness(GOAL);
    public static int generation = 0;

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        String newChild;
        newChild = makeRandom();

        System.out.println(naturalSelection(newChild));

        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }

    public static String naturalSelection(String parent)
    {
        System.out.println(parent+" Generation:"+generation);
        String child = mutate(parent);
        generation ++;
        String solution;
        if(getFitness(child) != goalFitness)
        {
            return naturalSelection(child);
        }
        else
        {
            solution = child;
        }
        return solution;
    }

    public static String mutate(String parent)
    {
        String fittest = parent;
        String child = "";
        int mutator;
        int mutation;

        //SEX
        for(int c=0; c<GOAL.length(); c++)
        {
            if((int)GOAL.charAt(c) != (int)parent.charAt(c))
            {
                mutator = new Random().nextInt(2);   //0-1
                mutation = new Random().nextInt(3);   //0-2

                if(mutator == 0) //Addition mutation
                {
                    child += (char)((int)parent.charAt(c) + mutation);
                }
                if(mutator == 1) //Subtraction mutation
                {
                    child += (char)((int)parent.charAt(c) - mutation);
                }
            }
            else
            {
                child += parent.charAt(c);
            }
        }
        //COMPETITION
        if(getFitness(child) < getFitness(parent))
        {
            fittest = child;
        }
        //KILL
        return fittest;
    }

    public static String makeRandom()
    {
        String randomString = "";
        int randomInt;

        for(int i=0; i<GOAL.length(); i++)
        {
            randomInt = new Random().nextInt(95) + 32;
            randomString += (char)randomInt;
        }

        return randomString;
    }

    public static int getFitness(String s)
    {
        int fitness =0;
        int goalAsciiVal;
        for(int i=0; i<s.length(); i++)
        {
            goalAsciiVal = (int)GOAL.charAt(i);
            fitness += Math.abs(goalAsciiVal - (int)s.charAt(i));
        }
        return fitness;
    }
}