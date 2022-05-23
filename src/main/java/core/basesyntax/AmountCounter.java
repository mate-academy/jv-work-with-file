package core.basesyntax;

public class AmountCounter {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public String count(String[] data, String name) {
        for (String value : data) {
            if (value.split(",")[0].equals(name)) {
                counter += Integer.parseInt(value.split(",")[1]);
            }
        }
        return new StringBuilder().append(name).append(",").append(counter).toString();
    }
}
