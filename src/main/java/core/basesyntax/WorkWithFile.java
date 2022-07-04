package core.basesyntax;

public class WorkWithFile {
    static final int OPERATION_TYPE = 0;
    static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        WriteToFile writeToFile = new WriteToFile();
        ReadFromFile readFromFile = new ReadFromFile();
        String[] dataLine = readFromFile.readFile(fromFileName);
        for (String split:dataLine) {
            String[] data = split.split(",");
            try {
                if (data[OPERATION_TYPE].equals("supply")) {
                    supply += Integer.parseInt(data[VALUE]);
                } else if (data[OPERATION_TYPE].equals("buy")) {
                    buy += Integer.parseInt(data[VALUE]);
                }
            }catch (NumberFormatException e){
                throw new RuntimeException("not a number", e);
            }
        }
        String[] report = {"supply," + supply, "buy," + buy, "result," + (supply - buy)};
        writeToFile.writeFile(toFileName, report);
    }
}
