package core.basesyntax;

public class ArrGenerator {
    private static final String SPECIFIED_CHARACTER = "\n";

    public String[] arrGenerator(String s) {
        return s.split(SPECIFIED_CHARACTER);
    }
}
