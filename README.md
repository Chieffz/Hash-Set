# Hash-Set

# Custom Hashing Set

- A Data Structure that allows the user to hash any n amount of words into an array
- Uses Closed Addressing to store hashed words and the linked lists are set to be a maximum average length of 20 before being upsized

# Installation

1. Clone or Download the Repository

2. Add in any random input file
- The input file can be of course of any length
- Make sure input file is in the same directory as 'HashSetTester'

3. Run on the Command Line, the arguments are "java HashSetTester <input.txt> <Initial Array Length>

- Please note that if you wish to use as little memory as possible than make sure your initial array length is as large you want since that will mean less upsizing, which means less used memory
- Also, currently on larger input files this can get quite slow, I'm talking 50 Million Words, so please bare in mind, additionally your heap space might run out for larger files as well.
