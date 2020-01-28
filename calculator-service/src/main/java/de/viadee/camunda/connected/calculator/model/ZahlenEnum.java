package de.viadee.camunda.connected.calculator.model;

public enum ZahlenEnum {

    Null(0),
    Eins(1),
    Zwei(2),
    Drei(3),
    Vier(4),
    Fünf(5),
    Sechs(6),
    Sieben(7),
    Acht(8),
    Neun(9);

    final int value;

    ZahlenEnum(int value) {
        this.value = value;
    }

    public static ZahlenEnum getZahlByValue(int value) {
        for (ZahlenEnum zahl : values()) {
            if (value == zahl.value) {
                return zahl;
            }
        }
        return ZahlenEnum.Null;
    }
}
