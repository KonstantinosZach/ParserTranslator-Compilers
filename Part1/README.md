## 2022 Project 1

### Προσωπικά στοιχεία

__Όνομα__: Γεώργιος-Κωνσταντίνος Ζαχαρόπουλος

### Documentation
#### Part 1

#### Πρόβλημα

For the first part of this homework you should implement a simple calculator. The calculator should accept expressions with the bitwise AND(&) and XOR(^) operators, as well as parentheses. The grammar (for single-digit numbers) is summarized in:

exp -> num | exp op exp | (exp)

op -> ^ | &

num -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

You need to change this grammar to support priority between the two operators, to remove the left recursion for LL parsing, etc.

This part of the homework is divided in two parts:

For practice, you can write the FIRST+ & FOLLOW sets for the LL(1) version of the above grammar. In the end you will summarize them in a single lookahead table (include a row for every derivation in your final grammar). This part will not be graded.

You have to write a recursive descent parser in Java that reads expressions and computes the values or prints "parse error" if there is a syntax error. You don't need to identify blank space or multi-digit numbers. You can read the symbols one-by-one (as in the C getchar() function). The expression must end with a newline or EOF.

#### Εκτέλεση:
* Πέρα από το αυτόματο build and run που παρέχει το vscode μπορείτε να εκτελέσετε το πρόγραμμα τρέχοντας `make` και μετά `make run` μέσα στον φάκελο src. Τότε μπορείτε να εισάγετε μέσω του terminal την συμβολοσειρά που θέλετε να υπολογίσετε. Τέλος `make clean` για να διαγράψετε τα .class αρχεία.

Παραδείγματα συμβολοσειρών και τα αποτελέσματα τους:

* 2^8&1^(6&1)=2
* 2&(3^1&(1^3&3))=2
* 2^3&&1 -> parse error
* 4 ^1&3 -> parse error (whites spaces leads to parse error)

#### LL(1) Grammar

    The LL(1) grammar for Part 1

    Goal -> Expr
    Expr -> Term Expr2
    Expr2 -> ^ Term Expr2 
          |e
    Term  -> Factor Term2
    Term2 -> & Factor Term2
          |e
    Factor -> 0
          |1 
          |2 
          |3 
          |4 
          |5 
          |6 
          |7 
          |8 
          |9 
          |(Expr)

#### Σχόλια
* Το πρόγραμμα δέχεται μια είσοδο κάθε φορά.




