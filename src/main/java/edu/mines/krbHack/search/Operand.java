package edu.mines.krbHack.search;

public class Operand {
    private Operator operator = null;

    private String attributeName = null;
    private String attributeValue = null;
    private boolean not = false;

    private Operand firstOperand = null;
    private Operand secondOperand = null;

    public Operand (final Operator operator, final String name, final String value, final boolean not) {
        this.operator = operator;
        this.attributeName = name;
        this.attributeValue = value;
        this.not = not;
    }

    public Operand (final Operator operator, final Operand firstOperand, final Operand secondOperand) {
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public final Operand getFirstOperand(){
        return firstOperand;
    }

    public final Operand getSecondOperand() {
        return secondOperand;
    }

    public final String getAttributeName() {
        return attributeName;
    }

    public final String getAttributeValue() {
        return attributeValue;
    }

//    public final boolean isUid() {
//        return (attributeName.equalsIgnoreCase(Uid.NAME) || attributeName.equalsIgnoreCase(Name.NAME));
//    }

    public final boolean isNot() {
        return not;
    }

    public final Operator getOperator() {
        return operator;
    }
}
