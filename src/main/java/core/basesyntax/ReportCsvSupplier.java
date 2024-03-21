package core.basesyntax;

public class ReportCsvSupplier {
    private static final String BUY_KEY = "buy";
    private static final String SUPPLY_KEY = "supply";
    private static final String RESULT_KEY = "result";
    private static final int INDEX_OF_KEY = 0;
    private static final int INDEX_OF_VALUE = 1;
    private final String data;

    public ReportCsvSupplier(String data) {
        this.data = data;
    }

    public String createSummeryReport() {
        Report report = new Report();
        for (String reportLine : data.split(System.lineSeparator())) {
            ReportLine parsedReportLine = parseReportLine(reportLine);
            switch (parsedReportLine.getKey()) {
                case SUPPLY_KEY: {
                    report.addToSupply(parsedReportLine.getValue());
                    break;
                }
                case BUY_KEY: {
                    report.addToBuy(parsedReportLine.getValue());
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return SUPPLY_KEY + "," + report.getSupply() + System.lineSeparator()
                + BUY_KEY + "," + report.getBuy() + System.lineSeparator()
                + RESULT_KEY + "," + (report.getSupply() - report.getBuy());

    }

    private static ReportLine parseReportLine(String reportLine) {
        String[] splittedLine = reportLine.split(",");
        return new ReportLine(splittedLine[INDEX_OF_KEY],
                Integer.parseInt(splittedLine[INDEX_OF_VALUE]));
    }

    static class ReportLine {
        private final String key;
        private final int value;

        public ReportLine(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }
}
