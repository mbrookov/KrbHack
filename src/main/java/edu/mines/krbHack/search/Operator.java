
package edu.mines.krbHack.search;

public enum Operator {

    EQ("="),
    SW("*"),
    EW("*"),
    OR("||"),
    AND("&&"),
    C("*");
    final private String op;

    Operator(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return op;
    }
}