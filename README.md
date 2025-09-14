# discogs-label-generator
Java Program to generate PDF Labels with information about each record in a collection.

# Usage
Compile using `export CLASSPATH=./libs/*:./ && javac *.java`  
Run using `java DiscogsLabelGenerator <Path to Discogs CSV>`  
Flags  
    - `-name <NAME>` will render the title as 'Name's Record Collection'  
    - `-line1 <LINE1>` will replace the placeholder 'Established 2025' with text of your choice   
    - `-line2 <LINE2>` will add a line of text below line1  
    - `-line3 <LINE3>` will add a line of text below line2  
    - `-filterdate <Date in format YYYY-MM-DD>` will only print labels of records added to collection on or after the supplied date.  