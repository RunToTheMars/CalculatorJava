import Calculator.*;
import Token.*;

import java.util.ArrayDeque;

public class Main
{
    public static void main (String[] args)
    {
        String input_expr = "";
        for (int i = 0; i < args.length; i++)
            input_expr += args[i];

        System.out.println("INPUT : " + input_expr);
        NumberToken number = Calculator.Calculate (input_expr);
        System.out.println("RESULT : " + number.Expression());

        Tests.Do_Tests ();
    }
}
