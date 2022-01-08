package Token;

import java.util.Optional;

public final class BracketToken extends AbstractToken<BracketToken.BracketType>
{
    public enum BracketType { LEFT, RIGHT }

    public BracketToken (String expr)
    {
        super(expr);
    }

    public BracketToken (BracketType data)
    {
        super(data);
    }

    @Override

    public Optional<Pair<String, BracketType>> FromExpr(String expr) {
        Optional<Pair<String, BracketType>> data = Optional.empty();
        char first_character = expr.charAt(0);
        switch (first_character) {
            case '(' -> data = Optional.of(new Pair ("(", BracketType.LEFT));
            case ')' -> data = Optional.of(new Pair (")", BracketType.RIGHT));
        }
        return data;
    }

    @Override
    public String ToExpr(BracketType data) {
        String expr = "";
        switch (data) {
            case LEFT -> expr = "(";
            case RIGHT -> expr = ")";
        }
        return expr;
    }
}
