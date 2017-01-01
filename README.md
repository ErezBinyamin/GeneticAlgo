# GeneticAlgo
Proof Of Concept Genetic Algorithm

The purpose of this project, was to learn about how genetic algorithms work, and how I could implement one to print "Hello, World!".
In Main.java, I experimented with using a population of only one string and mutating that string slightly each generation to produce "offspring".

The program works by equating fitness of a given String (char array) with its similarity to a "GOAL" String (In this case "Hello, World).
Each character in a String is treated as a gene or trait.
A String is compared character by character (trait by trait) to the GOAL String, to determine fitness.
Each generation a given character has a chance of being mutated.
If the new mutated String is "more fit" It survives, and becomes the template for the next generation.
This will occur until a "Goal Fitness is achieved".
This process mimics the natural process of asexual reproduction seen in many Archaea, Bacteria, plants and fungi.

The obvious next step was to expand this model to not just mimic asexual reproduction, but also sexual reproduction.
In the second file; Populations.java, the fields popSize, horniness (children made), and mutations were added.
This allowed for a population of greater size and therefore a chance to do some random mating.
STEPS:

1. A population of "popSize" random Strings of length "GOAL.length()" are made
2. Two parent Strings are randomly chosen.
3. Genes are randomly selected one at a time from one of the parents.
4. Each gene has some chance of being mutated in one of "mutations" different ways.
5. Steps 2-4 are repeated "horniness" many times.
6. Only the "popSize" fittest members are kept (the rest are deleted and die)
7. All of this is repeated until the goal is acheived.
