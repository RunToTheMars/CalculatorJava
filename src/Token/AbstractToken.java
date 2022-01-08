package Token;

import java.util.Optional;

public abstract class AbstractToken<DataType> {
    private String m_expr;
    private DataType m_data;
    private Boolean m_is_ok = false;

    class Pair <T1, T2>
    {
        public T1 m_first;
        public T2 m_second;

        public Pair (T1 first, T2 second)
        {
            m_first = first;
            m_second = second;
        }
    }

    public AbstractToken (String expr)
    {
        m_expr = expr;
        Optional<Pair<String, DataType>> data = FromExpr(expr);
        m_is_ok = !data.isEmpty ();
        if (m_is_ok)
        {
            m_expr = data.get ().m_first;
            m_data = data.get ().m_second;
        }

    }

    public AbstractToken (DataType data)
    {
        m_data = data;
        m_is_ok = true;
        m_expr = ToExpr(data);
    }

    public abstract Optional<Pair<String, DataType>> FromExpr (String expr);
    public abstract String ToExpr (DataType data);

    public final String Expression ()
    {
        return m_expr;
    }

    public final DataType Data ()
    {
        return m_data;
    }

    public final Boolean IsOk ()
    {
        return m_is_ok;
    }
}
