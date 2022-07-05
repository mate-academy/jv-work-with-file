package core.basesyntax;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static FileReader fileReader = new FileReader();
    private static FileWriter fileWriter = new FileWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] data = fileReader.readFile(fromFileName);
        for (String line : data) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_INDEX].equals("supply")) {
                supply += Integer.parseInt(splittedLine[QUANTITY_INDEX]);
            } else if (splittedLine[OPERATION_INDEX].equals("buy")) {
                buy += Integer.parseInt(splittedLine[QUANTITY_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + (supply - buy));
        fileWriter.writeToFile(toFileName, reportBuilder.toString());
    }
}
