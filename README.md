# RAKE
Java based implementation of RAKE algorithm
The project aims to develop a keyword generator from abstracts of academic research papers by starting with RAKE algorithm as the base and optimizing it further to give better results


To-do list:

1)<strike>Parse PDF as raw text</strike>

2)<strike>Remove candidate keywords greater than length 3</strike> 

3) Parsing PDF:
  a)<strike> Remove numbers </strike>  
  b) Remove unwanted tables, figures
  c)<strike>Strip punctuations </strike>  

4) Using NLP to better keyword results
    Removing: s, ies, es etc from words

5) AddedTF scores for dynamic stop-word  generation
    Added TF-IDF keyword generation algorithm

5) Added parser to extract abstracts from research papers
    Thanks to https://github.com/nemausus/research-paper-parser !


  
  
Final Goal:
Writing a keyword suggesting package for Koha
