import java.io.InputStream;
import java.io.IOException;

class Calculator{

    private final InputStream INPUT;
    private int lookAhead;

    public Calculator(InputStream in) throws IOException{
        this.INPUT = in;
        System.out.print("Your input:");
        lookAhead = INPUT.read();
    }

    //comsumes one symbol of the input
    private void consume(int symbol) throws IOException, ParseError{
        if (lookAhead == symbol)
            lookAhead = INPUT.read();
        else
            throw new ParseError();
    }

    //checks whether the symbol is a digit
    private boolean isDigit(int c){
        return ('0' <= c && c <= '9');
    }

    //returns the value of the symbol
    private int evalDigit(int c){
        return c - '0';
    }

    //Starting point of our grammar
    //returns the value of the expression
    public int goal() throws IOException, ParseError{
        int value = expr();
        
        //EOF if lookAhead is -1
        if (lookAhead != -1 && lookAhead != '\n')
            throw new ParseError();
        
        System.out.print("Result:");
        return value;
    }

    private int expr() throws IOException, ParseError{
        return expr2(term());
    }

    private int term() throws IOException, ParseError{
        return term2(factor());
    }

    private int term2(int leftDigit) throws IOException, ParseError{
        //if the lookAhead is not '&'' we dont consume the input
        if(lookAhead == '&'){
            consume(lookAhead);
            return term2(leftDigit & factor());
        }
        //just return the current symbol
        return leftDigit;
    }

    private int expr2(int leftDigit) throws IOException, ParseError{
        //if the lookAhead is not '^'' we dont consume the input
        if(lookAhead == '^'){
            consume(lookAhead);
            return expr2(leftDigit ^ term());
        }
        //just return the current symbol
        return leftDigit;
    }

    //Checks whether lookAhed is a digit or a parethenses
    //will return the lookAhead only if it is valid
    //if the input is false will throw error
    private int factor() throws IOException, ParseError{
        int digit;
        if(isDigit(lookAhead)){
            digit = evalDigit(lookAhead);
            consume(lookAhead);
            return digit;
        }
        else if(lookAhead == '('){
            consume(lookAhead);
            digit = expr();
            if(lookAhead == ')'){
                consume(lookAhead);
                return digit;
            }
        }
        
        throw new ParseError();
    }
}
