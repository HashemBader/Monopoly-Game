# 2023F CS2910 Project Report
**Group Members**
1. Zeyad Awad (362192)
2. Hashem Bader (362311)

# Summary of Game
_A summary description of the game that you chose.  This should include the name of the game, 
the goal of the game and a brief description of the key objects in the game. Please provide a link to the game and/or its rules online. (100 words)_
The game is Monopoly and it is a boardgame of 2 to 8 players, which each player
has an initial money of $1500, a name and a strategy.
The player will throw the dice and will move around the board then an action will be
taken when a player lands on a square in the board. Players will start to lose money
eventually after few rounds and whoever loses his all money will be out of the game,
To win the game, there should be only one player left that still has money.
For more information, here is the link to the rules(https://www.hasbro.com/common/instruct/00009.pdf).

# Experiment Report
## Player Strategies
1. Greedy: 
    - Buys properties 70% of the time regardless of its type.
    - Builds a house 90% of the time if possible.
2. Stingy:
    - Buys properties 20% of the time regardless of its type.
    - Builds a house 10% of the time if possible.
3. Default:
    - Buys properties 50% of the time regardless of its type.
    - Builds a house 30% of the time if possible.
4. Station Guy: 
    - Buys properties 40% of the time, with an exception for stations, 90%.
    - Builds a house 30% of the time if possible.
5. Utility Guy: 
    - Buys properties 40% of the time, with an exception for utilities, 90%.
    - Builds a house 30% of the time if possible.

## Procedure
In the experiment, a game is created given the list of players with strategies and the board map. The winner of each trial is stored in a hash map which then exports the following data: strategy, money countries, utilities, stations, and totalLandsOwned of the winner into a CSV file for data analysis. When the simulation runs, information about each player is displayed before and after rolling the dice to give an understanding about what is happening. 

After the specified number of trials, e.g., 60, the program displays the number of games won per strategy. 

It is important to note that in a 2-player monopoly game, it is believed that 12% of the games in a simulation would not end (which matches our simulation!) according to this paper: [ESTIMATING THE PROBABILITY THAT THE GAME OF MONOPOLY NEVER ENDS](https://www.informs-sim.org/wsc09papers/036.pdf?fbclid=IwAR3kaMbPMHUb0MXR8hD9u8_w49IYBTQWYrQHd0auXGnQ3ssjdTwqRIlIDEs). For that reason, the program displays the number of non-ending games (which we defined to be when a player has more than 100,000$).
We then collect the CSV data for further data analysis in Python (mainly Pandas library). We compared a variety of games, namely:
- Greedy VS Stingy (2 players)
- Station Guy VS Utility Guy (2 players)
- All 5 strategies (5 players)

The results were interesting.

## Results

### Greedy vs Stingy:

| strategy | money | countries | utilities | stations | totalLandsOwned |
|----------|-------|-----------|-----------|----------|-----------------|
| GREEDY   | 3489.5| 16.0      | 2.0       | 3.0      | 20.0            |
| STINGY   | 1475.0| 3.0       | 0.0       | 1.0      | 4.0             |

Table 1.0: Player Strategies and Holdings

| Strategy |  Win Rate   |
|----------|-------------|
| GREEDY   | 0.582788    |
| STINGY   | 0.417212    |


Table 1.1: Win Rate Per Strategy


![Distribution of means](image-1.png)
![Distribution of difference in means](image-2.png)
![Boxplot: money vs strategy](image-3.png)
![Boxplot: lands owned vs strategy](image-4.png)

### Station Guy vs Utility Guy:

| strategy     | money | countries | utilities | stations | totalLandsOwned |
|--------------|-------|-----------|-----------|----------|-----------------|
| STATION_GUY  | 4906.0| 11.0      | 1.0       | 3.0      | 14.0            |
| UTILITY_GUY  | 9017.0| 11.0      | 1.0       | 1.0      | 14.0            |



Table 2.0: Player Strategies and Holdings



| Strategy     | Win Rate    |
|--------------|-------------|
| STATION_GUY  | 0.624718    |
| UTILITY_GUY  | 0.375282    |



Table 2.1: Win Rate Per Strategy


![Distribution of means](image-5.png)
![Distribution of difference in means](image-6.png)
![Boxplot: money vs strategy](image-7.png)
![Boxplot: lands owned vs strategy](image-8.png)

### All 5 Strategies (5 players):

| strategy     | money    | countries | utilities | stations | totalLandsOwned |
|--------------|----------|-----------|-----------|----------|-----------------|
| DEFAULT      | 40819.5  | 7.0       | 0.0       | 0.0      | 8.0             |
| GREEDY       | 21572.0  | 9.0       | 0.0       | 1.0      | 10.0            |
| STATION_GUY  | 41457.0  | 5.0       | 0.0       | 1.0      | 8.0             |
| STINGY       | 77198.5  | 4.0       | 0.0       | 0.0      | 4.5             |
| UTILITY_GUY  | 40519.0  | 5.0       | 1.0       | 0.0      | 7.0             |


Table 3.0: Player Strategies and Holdings


| Strategy     | Win Rate   |
|--------------|------------|
| GREEDY       | 0.533911   |
| DEFAULT      | 0.178932   |
| STATION_GUY  | 0.150072   |
| UTILITY_GUY  | 0.125541   |
| STINGY       | 0.011544   |


Table 3.1: Win Rate Per Strategy




![Boxplot: money vs strategy](image-9.png)
![Boxplot: lands owned vs strategy](image-10.png)

### Default vs Default (EXTRA)
![Distribution of means](image-11.png)

## Analysis
_An interpretation of your data explaining why one strategy is better than the other supported with 
data from your experiment.(500 words)_

# Reflection
_A reflection on your experiences with generative AI during this project. Provide a few sentences reflecting
on your experience with AI for each of the following prompts._ 

### What generative AI did you use, and what tasks did you use it for?
Chat gpt: supporting with the test cases to cover all the possible cases. Also helped a lot with the documentation of classes and methods to cover all the details.

### How did you learn about the tools used by your group (delete ones that don't apply)?
_Please describe where and how you learned about the tools_
most of the tools that have been used were learned in the class. Some of the tools we had to search for it in google.

### Reflecting on your experience:
_Write a short reflection on your use of generative AI in this project, including if you did not use it. 
You may use the prompts as headings if you wish. (500 words)_  
Generative AI was a really helpful, it helped with having ideas for making test cases and documentaion for the code to cover all the details and the errors in the code.
In another hand, it was not helpful in making codes that helps with calculating rents.
Overall, the generative AI helped with improving the test cases and the documentation, which made the code more effecient,
maybe adding some complicated functions that matches with the reality of the game
could you have made the project turned out better, other than that, most of the game was covered effeciently.

**Prompts to think about in writing your reflections if you worked with generative AI:**
- What went well using generative AI in this project?
- What went well using generative AI?
- What didn’t go well using generative AI?
- Were there any limitations you encountered using generative AI?
- How did your solution change/evolve/improve/degrade because of the generative AI?
- What could you have done so the project turned out better?

**Prompts to think about in writing your reflections if you didn't use generative AI:**
- Why did you choose not to use generative AI in this project?
- Were there particular problems that you encountered where you think generative AI would have helped?
- Were there particular things you were glad you learned to do yourself without the use of generative AI?
- What could you have done so the project would turn out better?

# Bonus Consideration:
If you have aspects of your project you would like considered for the available bonus.