package patinentintake;

public enum Doctor {
    jan("Jan Kovalsky"),
    ann("Anna Marii"),
    beck("Kent Beck");

    private String name;

    Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
