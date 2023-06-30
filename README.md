# Java-Assignment-Indexing-Pages-

Write a program in Java which will read the pages of a book and create an index of words giving the list of pages on which each word is present.

Test data :
There are 3 files Page1.txt, Page2.txt and Page3.txt containing some text. The file exclude-words.txt contains common words like 'and' which should not be indexed.

The program should read the Page1.txt, Page2.txt, Page3.txt and the exclude-words.txt file. It should generate output in file index.txt in following format :

word : Comma separated list of pages on which word is present.

Eg. 
If word 'network' is present in page 1 and page 2 then the entry for the word in index.txt should be :
network : 1,2

If word 'network' is present in page 1 only then
network : 1

The words should be sorted in index.txt alphabetically A - Z.
If a word is present in a page multiple times, then the page number should be present only once - i.e unique page numbers should be present.

The code should be object oriented with well defined classes.

The candidate is expected to write the object design in Design.txt before starting code.

Time : The candidate can take maximum 3 hours for the assignment.

The candidate can refer javadocs on internet.
