package common;

public enum Size {
    BigSize("big",100 * 1024 * 1024),
    MidSize("mid", 50 * 1024 * 1024),
    SmallSize("small", 10 * 1024 * 1024);

    private final String name;
    private final int value;

    private Size(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }


}
