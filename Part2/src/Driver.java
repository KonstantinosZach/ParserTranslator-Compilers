import java_cup.runtime.*;
import java.io.*;

class Driver {
    public static void main(String[] argv) throws Exception{
        Parser p = new Parser(new Scanner(new FileInputStream("test_input.txt")));
        p.parse();
    }
}
