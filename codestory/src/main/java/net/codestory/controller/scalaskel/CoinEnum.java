package net.codestory.controller.scalaskel;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 12/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public enum CoinEnum {
    FOO(1),
    BAR(7),
    QIX(11),
    BAZ(21);

    private int value;
    private CoinEnum(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
