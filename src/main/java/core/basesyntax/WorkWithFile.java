package core.basesyntax;

public class WorkWithFile {
    static final int OPERATION_TYPE = 0;
    static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();
        String[] dataLine = fileReader.readFile(fromFileName);
        for (String split:dataLine) {
            String[] data = split.split(",");
            if (data[OPERATION_TYPE].equals("supply")) {
                supply += Integer.parseInt(data[VALUE]);
            } else if (data[OPERATION_TYPE].equals("buy")) {
                buy += Integer.parseInt(data[VALUE]);
            }
        }
        String[] report = {"supply," + supply, "buy," + buy, "result," + (supply - buy)};
        fileWriter.writeToFile(toFileName, report);
    }
}
