package Token;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberToken extends AbstractToken<Double>
{
    public NumberToken (String expr)
    {
        super(expr);
    }

    public NumberToken (double data)
    {
        super(data);
    }

    @Override
    public Optional<Pair<String, Double>> FromExpr(String expr) {
        Optional<Pair<String, Double>> data = Optional.empty();

        Pattern pattern = Pattern.compile("^\\d+\\.?(\\d+)?");

        Matcher matcher = pattern.matcher(expr);
        boolean result = matcher.find(0);
        if (result)
        {
            int end = matcher.end (0);
            String value_str = expr.substring (0, end);
            //System.out.println("END: " + end + "VALUE_STR: " + value_str);
            data = Optional.of(new Pair (value_str, Double.parseDouble(value_str)));
        }

        return data;
    }

    @Override
    public String ToExpr(Double data) {
        return data.toString ();
    }
}
