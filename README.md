# Hash-Set

# Custom Hashing Set

- A Data Structure that allows the user to hash any n amount of words into an array
- Uses Closed Addressing to store hashed words and the linked lists are set to be a maximum average length of 20 before being upsized

# Planned Features
- Generic Implementation is currently in progress to allow for this Hashset to not only hash strings but any data type. Only if that data type extends Comparable and is easily comparable
- A more streamlined/user friendly tester, currently the tester is pretty barebones and doesn't allow for more custom testing. I plan on changing that to allow for more dynamic testing
- Making the algorithm faster, it's not as fast as I'd currently like on some machines. It might be due to the fact I might be doing too much redundent operations & need to implement hueristics.

# Installation

1. Clone or Download the Repository

2. Add in any random input file
- The input file can be of course of any length
- Make sure input file is in the same directory as 'HashSetTester'

3. Run on the Command Line, the arguments are "java HashSetTester <input.txt> <Initial Array Length>

- Please note that if you wish to use as little memory as possible than make sure your initial array length is as large you want since that will mean less upsizing, which means less used memory
- Please note that currently you cannot insert additional strings into the hash set from the command line or by additional files. Additionally, the feature to retrieve Strings from the set will be implemented soon.
