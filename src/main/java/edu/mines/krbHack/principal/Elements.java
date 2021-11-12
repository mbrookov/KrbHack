package edu.mines.krbHack.principal;

public enum Elements {

    USERNAME(0),
    POLICY(1),
    ALLOW_TIX(2);

    private int code;
    private Elements(int c) {
        code=c;
    }
    public int getCode() {
        return code;
    }
}
