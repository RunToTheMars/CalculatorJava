package Token;

import java.util.Optional;

import static Token.BinaryOperationToken.BinaryOperationType.ADD;

public class BinaryOperationToken extends AbstractToken<BinaryOperationToken.BinaryOperationType>
{
    public enum BinaryOperationType { ADD, SUBTRACT, MULTIPLY, DIVIDE }
    public BinaryOperationToken (String expr)
    {
        super(expr);
    }

    public BinaryOperationToken (BinaryOperationType data)
    {
        super(data);
    }

    @Override
    public Optional<Pair<String, BinaryOperationType>> FromExpr(String expr) {
        Optional<Pair<String, BinaryOperationType>> data = Optional.empty();
        char first_character = expr.charAt(0);
        switch (first_character) {
            case '+' -> data = Optional.of(new Pair("+", BinaryOperationType.ADD));
            case '-' -> data = Optional.of(new Pair("-", BinaryOperationType.SUBTRACT));
            case '*' -> data = Optional.of(new Pair("*", BinaryOperationType.MULTIPLY));
            case '/' -> data = Optional.of(new Pair("/", BinaryOperationType.DIVIDE));
        }
        return data;
    }

    @Override
    public String ToExpr(BinaryOperationType data) {
        String expr = "";
        switch (data) {
            case ADD -> expr = "+";
            case SUBTRACT -> expr = "-";
            case MULTIPLY -> expr = "*";
            case DIVIDE -> expr = "/";
        }
        return expr;
    }

    public int Priority ()
    {
        return Data().ordinal();
    }

    public Optional<NumberToken> Apply (NumberToken left, NumberToken right)
    {
        double lvalue = left.Data();
        double rvalue = right.Data();
        Optional<NumberToken> token = Optional.empty();

        if (!left.IsOk() || !right.IsOk())
        {
            return token;
        }

        switch (Data ())
        {
            case ADD      -> token = Optional.of(new NumberToken (lvalue + rvalue));
            case SUBTRACT -> token = Optional.of(new NumberToken (lvalue - rvalue));
            case MULTIPLY -> token = Optional.of(new NumberToken (lvalue * rvalue));
            case DIVIDE   -> token = Optional.of(new NumberToken (lvalue / rvalue));
        }
        return token;
    }
}
