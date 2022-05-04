package core.basesyntax;

public class ProcessData {
    private static final String[] NAMES = new String[]{"supply", "buy", "result"};

    private int[] values = new int[]{0, 0};

    public String createReport(String sourceData) {
        if (sourceData == null || sourceData.isEmpty()) {
            return "";
        }

        for (String line : sourceData.split(System.lineSeparator())) {
            int amount;
            String[] row = line.split(",");
            try {
                amount = Integer.parseInt(row[1]);
            } catch (NumberFormatException e) {
                continue;
            }
            if (NAMES[0].equals(row[0])) {
                values[0] += amount;
            } else if (NAMES[1].equals(row[0])) {
                values[1] += amount;
            }
        }
        return getResult();
    }

    private String getResult() {
        return String.format("%s,%d%s", NAMES[0], values[0], System.lineSeparator())
                + String.format("%s,%d%s", NAMES[1], values[1], System.lineSeparator())
                + String.format("%s,%d", NAMES[2], values[0] - values[1]);
    }
}
