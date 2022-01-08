package Parser;

import Exceptions.Exceptions;
import Token.AbstractToken;
import Token.BinaryOperationToken;
import Token.BracketToken;
import Token.NumberToken;

import java.util.ArrayDeque;

public class Parser {

    public static ArrayDeque<AbstractToken> Parse (String str)
    {
        String str_without_spaces = str.replaceAll("\\s+","");
        ArrayDeque<AbstractToken> res_deque = new ArrayDeque<> ();

        int start;
        int length = str_without_spaces.length();

        for (start = 0; start < length;)  {
            AbstractToken new_token = Extract (str_without_spaces, start);
            if (!new_token.IsOk())
                break;

            start += new_token.Expression().length();
//            System.out.println("EXPR:" + new_token.Expression());
            res_deque.add (new_token);
        }

        return res_deque;
    }


    private static AbstractToken Extract (String str, int start)
    {
        AbstractToken token;

        if ((token = new BracketToken (str.substring(start))).IsOk()) {
//            System.out.println("BRACKET: " + token.Expression());
            return token;
        }

        if ((token = new NumberToken(str.substring(start))).IsOk())
        {
//            System.out.println("Number: " + token.Expression());
            return token;
        }

        if ((token = new BinaryOperationToken (str.substring(start))).IsOk())
        {
//            System.out.println("Operator: " + token.Expression());
            return token;
        }

        throw new Exceptions.InvalidTokenException(str.substring(start));
    }

}