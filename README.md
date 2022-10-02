# HOW TO RUN PROGRAMS

## Program 1:
- We need to go to the root directory 
- We compile the program by running the command: javac Problem1.java
- We execute the program by running the command: java Problem1

## Program2 
- We need 'cd' to the directory Problem2/registration
- We need to run the command: npm install
- We need to run the command: npm start
- In order to simiulate a dummy local database I am using JSON server. So in a different terminal we need to run the command: npx json-server --watch data/db.json --port 8000

## Prgram3
- We need to 'cd' to the directory Program3
- We need to make sure we have an existing .csv file in our current directory that our program is going to read
- We compoile the program by running the command: javac Problem3.java
- We pass an argument to our program specifying the name of the .csv file we are going to process. So We execute the program by running the command: java Problem3 <filename.csv> 
