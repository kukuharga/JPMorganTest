JPMorganTest

# **Program Brief**
This program takes input CSV input file
How to run the program :
java -jar JPMorganTest.jar <input file path>
Example :
java -jar JPMorganTest.jar /Users/kukuhargaditya/Documents/input

## Input file content:
**Message type1** :
type1,[product type],[value]
Example :
type1,apple,5

**Message type2** :
type2,[product type],[value],[qty]
Example :
type1,apple,5,10

**Message type3** :
type3,[product type],[value],[operation]
Example :
type1,apple,5,add

## Note
One file may contain multiple messages. 
You can mix all message types in one file.
There is no file naming convention at the moment.

Main class name : com.jpmorgan.messaging.MessageHandler
