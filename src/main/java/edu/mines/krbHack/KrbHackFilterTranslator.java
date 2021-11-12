package edu.mines.krbHack;

import edu.mines.krbHack.search.Operand;
import edu.mines.krbHack.search.Operator;
import org.identityconnectors.common.StringUtil;
import org.identityconnectors.framework.common.objects.AttributeUtil;
import org.identityconnectors.framework.common.objects.filter.*;


/* https://docs.oracle.com/cd/E21764_01/apirefs.1111/e24834/org/identityconnectors/framework/common/objects/filter/package-summary.html
* https://docs.oracle.com/cd/E21764_01/apirefs.1111/e24834/overview-summary.html */

public class KrbHackFilterTranslator extends AbstractFilterTranslator<Operand> {
    @Override
    protected Operand createEqualsExpression(final EqualsFilter filter,
                                             final boolean not) {
        if (filter == null) {
            return null;
        }
        String value = AttributeUtil.getAsStringValue(filter.getAttribute());
        if (StringUtil.isBlank(value)) {
            return null;
        }
        return new Operand(Operator.EQ,
                filter.getAttribute().getName(), value, not);
    }

    @Override
    protected Operand createStartsWithExpression(final StartsWithFilter filter,
                                                 final boolean not) {
        if (filter == null) {
            return null;
        }
        return new Operand(Operator.SW,
                filter.getName(), filter.getValue(), not);
    }

    @Override
    protected Operand createEndsWithExpression(final EndsWithFilter filter,
                                               final boolean not) {
        if (filter == null) {
            return null;
        }

        return new Operand(Operator.EW,
                filter.getName(), filter.getValue(), not);
    }

    @Override
    protected Operand createContainsExpression(final ContainsFilter filter,
                                               final boolean not) {
        if (filter == null) {
            return null;
        }

        return new Operand(Operator.C,
                filter.getName(), filter.getValue(), not);
    }

    @Override
    protected Operand createOrExpression(
            final Operand leftExpression, final Operand rightExpression) {
        if (leftExpression == null || rightExpression == null) {
            return null;
        }

        return new Operand(Operator.OR, leftExpression, rightExpression);
    }

    @Override
    protected Operand createAndExpression(final Operand leftExpression,
                                          final Operand rightExpression) {
        if (leftExpression == null || rightExpression == null) {
            return null;
        }

        return new Operand(Operator.AND, leftExpression, rightExpression);
    }

//    private String checkSearchValue(String value) {
//        if (StringUtil.isEmpty(value)) {
//            return null;
//        }
//        if (value.contains("*") || value.contains("&") || value.contains("|")) {
//            throw new IllegalArgumentException(
//                    "Value of search attribute contains illegal character(s).");
//        }
//        return value;
//    }
}
