import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Erez on 12/16/2016.
 */
public class Populations
{
    public static final String GOAL = "Hello, World!";
    public static final int popSize = 1;
    public static final int horniness = 100;
    public static final int mutations = 5;
    public static int goalFitness = getFitness(GOAL);
    public static int generation = 0;

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        ArrayList<String> population = new ArrayList<>();
        String newChild;
        for (int i=0; i<popSize; i++)
        {
            newChild = makeRandom();
            population.add(newChild);
        }

        String orig = population.get(0);
        System.out.println(evoloution(population));

        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");

        System.out.println("\n The String: "+ orig
        + "\n was a member of the initial population of size: "+ popSize
        + "\n that produced "+horniness+" children each generation"
        + "\n the mutation rate was at a level of " + mutations);
        System.out.println("Execution time was " + formatter.format((end - start) / 1000d) + " seconds");
    }

    public static String evoloution(ArrayList<String> population)
    {
        //SORT Weak -> Strong
        Collections.sort(population, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return getFitness(o1) - getFitness(o2);
            }
        });

        //INITIALIZE
        String mostFit = population.get(popSize-1);
        System.out.println(mostFit+" Generation:"+generation);
        generation++;
        ArrayList<String> newPop = new ArrayList<>();
        newPop.addAll(population);

        //CHECK FITTEST MEMBER
        if(getFitness(mostFit) == goalFitness)
        {
            return mostFit;
        }

        //MAKE CHILDREN
        for(int i=0; i<horniness; i++)
        {
            newPop.add(makeChild(population));
        }

        //SORT NEW POPULATION
        Collections.sort(newPop, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return getFitness(o1) - getFitness(o2);
            }
        });

        //KILL WEAKEST MEMBERS
        while(newPop.size()>popSize)
        {
            newPop.remove(newPop.size() -1);
        }

        //RECURSE
        return evoloution(newPop);
    }

    /**
     * TODO RANDOMLY SELECT PARENTS
     * Then send to mitosis to get child
     * @param population
     * @return
     */
    public static String makeChild(ArrayList<String> population)
    {
        String child;
        int mom = new Random().nextInt(population.size());
        int dad= new Random().nextInt(population.size());

        child = mitosis(population.get(mom), population.get(dad));
        return child;
    }

    /**
     * TODO RANDOMLY SELECT MUTATION FOR EACH GENE
     * Then return mutated child to makeChild()
     * @param mom
     * @param dad
     * @return
     */
    public static String mitosis(String mom, String dad)
    {
        String child = "";
        int whichParent;
        int mutator;
        int mutation;
        char gene;
        boolean done;
        for(int i = 0; i<GOAL.length(); i++)
        {
            done  = false;
            whichParent = new Random().nextInt(2);   //0-1
            mutator = new Random().nextInt(2);       //0-1
            mutation = new Random().nextInt(mutations);      //0-1

            //WHICH PARENT GIVES TRAIT???
            if(whichParent == 0){gene = mom.charAt(i);}
            else{gene = dad.charAt(i);}

            //MUTATE??
            if(mutator == 0)
            {
                child += gene;
                done = true;
            }
            //WHAT KINDA MUTATION
            else if (!done)
            {
                int tempVal = ((int) gene);
                switch (mutation)
                {
                    case 0:
                        tempVal++;
                        break;
                    case 1:
                        tempVal--;
                        break;
                    case 2:
                        tempVal += 5;
                        break;
                    case 3:
                        tempVal -= 5;
                        break;
                    case 4:
                        tempVal *= 2;
                        break;
                    case 5:
                        tempVal /= 2;
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                }
                gene = ((char) tempVal);
                child += gene;
            }
        }
        return child;
    }

    public static String makeRandom()
    {
        String randomString = "";
        int randomInt;

        for(int i=0; i<GOAL.length(); i++)
        {
            //RANDOM INTS 95-32 because of ASCII table
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
