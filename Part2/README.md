## 2022 Project 1

### Προσωπικά στοιχεία

__Όνομα__: Γεώργιος-Κωνσταντίνος Ζαχαρόπουλος

__Α.Μ.__: sdi1900061

### Documentation
#### Part 2

#### Πρόβλημα
In the second part of this homework you will implement a parser and translator for a language supporting string operations. The language supports concatenation (+) and "reverse" operators over strings, function definitions and calls, conditionals (if-else i.e, every "if" must be followed by an "else"), and the following logical expression:

is-prefix-of (string1 prefix string2): Whether string1 is a prefix of string2.
All values in the language are strings.

The precedence of the operator expressions is defined as: precedence(if) < precedence(concat) < precedence(reverse).

Your parser, based on a context-free grammar, will translate the input language into Java. You will use JavaCUP for the generation of the parser combined either with a hand-written lexer or a generated-one (e.g., using JFlex, which is encouraged).

You will infer the desired syntax of the input and output languages from the examples below. The output language is a subset of Java so it can be compiled using javac and executed using Java or online Java compilers.

There is no need to perform type checking for the argument types or a check for the number of function arguments. You can assume that the program input will always be semantically correct.

As with the first part of this assignment, you should accept input programs from stdin and print output Java programs to stdout. For your own convenience you can name the public class "Main", expecting the files that will be created by redirecting stdout output to be named "Main.java".. In order to compile a file named Main.java you need to execute the command: javac Main.java. In order to execute the produced Main.class file you need to execute: java Main.

To execute the program successfully, the "Main" class of your Java program must have a method with the following signature: public static void main(String[] args), which will be the main method of your program, containing all the translated statements of the input program. Moreover, for each function declaration of the input program, the translated Java program must contain an equivalent static method of the same name. Finally, keep in mind that in the input language the function declarations must precede all statements.

---

#### Εκτέλεση:
* Mπορείτε να εκτελέσετε το πρόγραμμα τρέχοντας `make compile` και μετά `make execute` μέσα στον φάκελο src. Η εντολή ` make execute` κάνει ανακατεύθυνση εισόδου στο αρχείο Main.java το πρόγραμμα που παράγει ο parser, το οποίο εμφανίζεται στο terminal με την εντολή cat. Μετά εκτελείται αυτόματα το αρχείο java με την χρήση του javac.

---

#### Σχόλια
 * Αμα για οποιαδήποτε λόγο το πρόγραμμα δεν τερματήσει σωστά, σε αυτό συμπεριλαμβάνεται και το να παραχτεί λάθος java πρόγραμμα, __πρέπει__ να κάνουμε `make clean` και ξάνα  `make compile`, `make execute`. Θα πρέπει να έχετε κατεβάσει το jflex κάνοντας `sudo apt install jflex`.
 * Πάνω από την γραμματική υπάρχει συνοπτική επεξήγηση των κανόνων της γραμματικής.

---

 #### Παρακάτω παραθέτω μερικές περιπτώσεις της γλώσσας και πως τις αντιμετώπισα: 
 * Στο σώμα τον functions αναγκαστικά μία μοναδική έκφραση.

         valid:
   
            func(){
               if("foo" prefix "f")
                  "foo"
               else
                  "bar"
            }

         invalid(syntax error):

            func(){
               "bla" + "blah"
               if("foo" prefix "f")
                  "foo"
               else
                  "bar"
            }

         invalid(syntax error):

            func(){}

 * Η έκφραση που βρίσκεται στις παρεθένσεις του if πρέπει αναγκαστικά να περιέχει το identifier prefix επειδή αλλίως δεν θα βγει boolean value
         
         valid:

            if("foo" prefix "f")
               "okey"
            else
               "bad"
         
         invalid:
            
            if("foo" + "bar")
               "blah"
            else
               "blah"

 * Εξω από τις συναρτήσεις, δηλαδή μετά τις δηλώσεις των συναρτήσεων, μπορούμε να έχουμε πολλαπλές εκφράσεις.
   * Στα if-else που βρίσκονται εκτός συναρτήσεων, μπορούν να ακολουθούν και άλλες εκφράσεις μετά, οι οποίες είναι ανεξάρτητες του if-else. Φυσικά άμα μετά το if ή το else δεν υπάρχει μια έκφραση τότε αυτό θα οδηγήσει σε syntax error.
   
         valid:

            func(){
               "bar"
            }

            reverse "foo"
            func()
            if("bar" prefix "b")
               "valid"
            else
               "not valid"
            "foo" + reverse "blah"

            output:
            oof
            bar
            valid
            foohalb

         invalid:

            if("bar" prefix "b")
               "valid"

            "foo" + reverse "blah"

            output: syntax error

 * Για να διαχωρίσω τα arguments σε επίπεδο ορισμού και κλήσης της συνάρτησης χρησιμοποίησα ως token το ){ το οποίο ονόμασα LBRACKET. Επίσης το επίπεδο κλήσης της συνάρτησης χωρίζεται και αυτό σε άλλες 2 περιπτώσεις, άμα η συνάρτηση καλείται μέσα σε άλλη συνάρτηση (επιτρέπονται expressions με identifiers και string literals) και στην περίπτωση που καλείται έξω από το σώμα των συναρτήσεων (επιτρέπονται μόνο expression με string literals). Στο επίπεδο ορισμού επιτρέπονται μόνο identifiers.

         valid:
            
            func(x,y,z){
               x+y+z
            }

            foo(x){
               func("a","b",x)
            }

            foo("c")

         invalid:

            func("x"){
               x
            }

            func(x)
