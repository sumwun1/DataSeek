This is a README file. If you can see this, then that means you are reading me, which is good. 
# DataSeek: does stuff that /ds doens't™ 
by a_farfetched_person (aka sumwun) 
First and foremost, this program is very bad. So if you want to report bugs, suggest features, or ask questions, then try posting that stuff on one of these 3 places: 
https://pokemondb.net/pokebase/user/sumwun
https://www.smogon.com/forums/members/sumwun.265729/
https://www.youtube.com/channel/UCXZv4pjoKSHU5rATMrNJYSg
(before you ask, no, "there's a type that's strong against grass, psychic, and dark" is **NOT** a bug. do **NOT** try to report it)
Of course, if you know how to program, then feel free to download this thing and change it however you want. You might even make it better. I think my worst problem is that reading the txt files takes a long time, so if you can tell me how to make it faster, then that would be great. 

Because this program is so bad, you need to do a lot of setup before it can work. In that time, you might as well read all the txt files and process them manually.  But if you really have nothing better to do with your time, then you can read these setup instructions. 
1. Download this entire repository. If you do it right, then one of your computer's attack stats should go up. 
2. Install Eclipse. It's free, but specific instructions will depend on your computer. If you have problems, then just Google your error messages and click random results until the problem goes away. 
3. Open Eclipse and click "Launch". 
4. There should be a "File" button in the top left corner. Click that, then click "Open Projects from File System...", then click "Directory". 
5. Find your downloaded repository. At this point, it's probably in your "downloads" folder. Click the folder that contains it, and then click "Finish". 
6. Click the "Window" button that should be a little to the right of the "File" button. Click it. Then click "Perspective", "Open Perspective", and "Java". If you don't see the "Java" button in that menu, then you can skip this step. 
7. On the left sidebar, you should see "DataSeek". Click the ">" next to it, then click the ">"s next to "src" and "notDefault". 
8. Double-click "Commands.java". Type whatever commands you want in the first method (between "commands() {" and "}"). Click the run button (green circle with right triangle in top bar). Wait for the commands to execute. 
If I tell you to click a button and you can't find it, then you can try sending me a screenshot of what your screen looks like right before you were told to click that button. 

This program uses Java, so when you type the name of a command, you need to type (""); right after the command's name and put your input between the quotes. For example, if you wanted to execute the ds command on movepool>=128,!learnsalltypes , then you'd have to type 
ds("movepool>=128,!learnsalltypes");
When you want to put multiple inputs into a command, separate them with ",". Also, when typing commands, don't use CAPITAL LETTERS, spaces, or mispeled words. If this program is printing weird things, then that's probably what happened. 

The ds, dt, and mean commands deal with Pokemon data. Currently, the only information I have on each Pokemon are their moves, the size of their movepools, whether or not they can learn each type of move, and whether or not they can learn every type of move. You should probably tell me if you really want this program to read something else. 
All Pokemon information is divided into two categories: statistics and truths. Statistics are numbers, and truths are yes-or-no questions. "Can it learn karate chop?" and "Can it learn a fire move?" are both examples of truths. The only statistic I store is movepool size. 
When using ds to search truths, you only need to type the name of the truth. For example, ds("karatechop"); searches for Pokemon that learn karate chop. You can type "!" in front of the truth, but only if you want to find Pokemon where that truth is false. 
When using ds to search statistics, you need to not only type the statistic's name, but also one of =, <, >, !=, <=, or >= and a number. For example, /ds("movepool<8"); searches for Pokemon that learn less than 8 moves. 
dt takes both statistics and truths. It only takes their names, no need for =, >, or any of that crap. It prints numbers for statistics and either "true" or "false" for truths. 
When using mean, the thing before the first "," must be the name of a statistic. Everything after that is read the same way as ds. 