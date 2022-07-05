package core.basesyntax;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static FileReader fileReader = new FileReader();
    private static FileWriter fileWriter = new FileWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] dataLine = fileReader.readFile(fromFileName);
        for (String split:dataLine) {
            String[] data = split.split(",");
            if (data[OPERATION_INDEX].equals("supply")) {
                supply += Integer.parseInt(data[QUANTITY_INDEX]);
            } else if (data[OPERATION_INDEX].equals("buy")) {
                buy += Integer.parseInt(data[QUANTITY_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + (supply - buy));
        fileWriter.writeToFile(toFileName, report.toString());
    }
}
