package core.basesyntax;

public class UniqueWordsExtractor {
    public String[] extract(String[] data) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String value : data) {
            builder.append(value.split(",")[0]).append(" ");
        }
        String[] strings = builder.toString().split(" ");
        for (String string : strings) {
            if (!builder1.toString().contains(string)) {
                builder1.append(string).append(" ");
            }
        }
        return builder1.toString().split(" ");
    }
}
