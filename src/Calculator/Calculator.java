package Calculator;
import Exceptions.Exceptions;
import Parser.Parser;
import Token.*;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Stack;

public class Calculator
{
    public static void PrintDeque (ArrayDeque<AbstractToken> deque)
    {
        ArrayDeque<AbstractToken> tmp_deque = deque;
        int size = tmp_deque.size ();
        for (int i = 0; i < size; i++)
        {
            if (i < size - 1)
                System.out.print("[" + tmp_deque.pop().Expression() + "],");
            else
                System.out.print("[" + tmp_deque.pop().Expression() + "]");
        }
    }

    public static NumberToken Calculate (String str)
    {
        ArrayDeque<AbstractToken> input_deque = Parser.Parse(str);
        ArrayDeque<AbstractToken> sorted_deque = SortDeque(input_deque);
        return Calculate (sorted_deque);
    }

    public static NumberToken Calculate (ArrayDeque<AbstractToken> deque)
    {
        Stack<NumberToken> stack = new Stack<>();
        for (AbstractToken t : deque) {
            if (t instanceof NumberToken number) {
                stack.push(number);
            } else if (t instanceof BinaryOperationToken op) {
                if (stack.size () < 2)
                {
                    throw new Exceptions.InvalidOperatorImplement ();
                }

                NumberToken t1 = stack.pop();
                NumberToken t2 = stack.pop();

                Optional<NumberToken> res = op.Apply(t2, t1);
                if (res.isEmpty())
                    return new NumberToken ("ERROR");

                stack.push (res.get ());
            }
        }
        if (stack.size () != 1)
            return new NumberToken ("ERROR");

        NumberToken t = stack.pop();
        if (!t.IsOk())
            return new NumberToken ("ERROR");
        return t;
    }

    public static ArrayDeque<AbstractToken> SortDeque (ArrayDeque<AbstractToken> deque)
    {
        ArrayDeque<AbstractToken> sorted_deque = new ArrayDeque<AbstractToken> ();
        Stack<AbstractToken> stack = new Stack<>();

        for (AbstractToken t : deque) {
            if (t instanceof NumberToken) {
                sorted_deque.add(t);
            } else if (t instanceof BinaryOperationToken op1) {
                while (!stack.empty()
                        && stack.peek() instanceof BinaryOperationToken op2
                        && op2.Priority() >= op1.Priority()) {
                    sorted_deque.add(stack.pop());
                }
                stack.push(op1);
            } else if (t instanceof BracketToken tb && tb.Data() == BracketToken.BracketType.LEFT) {
                stack.push(tb);
            } else if (t instanceof BracketToken tb && tb.Data() == BracketToken.BracketType.RIGHT) {
                while (!stack.isEmpty() && !(stack.peek() instanceof BracketToken bracket && bracket.Data() == BracketToken.BracketType.LEFT)) {
                    sorted_deque.add(stack.pop());
                }
                if (stack.isEmpty())
                    throw new Exceptions.InconsistentNumberOfBracketsException();
                stack.pop();
            }
        }

        while (!stack.empty()) {
            if (stack.peek() instanceof BracketToken)
                throw new Exceptions.InconsistentNumberOfBracketsException();
            sorted_deque.add(stack.pop());
        }

        return sorted_deque;
    }
}
