package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        String [] lineInfo;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                lineInfo = readLine.split(",");
                if (lineInfo[OPERATION].equals("buy")) {
                    buy += Integer.parseInt(lineInfo[AMOUNT]);
                }
                if (lineInfo[OPERATION].equals("supply")) {
                    supply += Integer.parseInt(lineInfo[AMOUNT]);
                }
                readLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        createReport(buy,supply,toFileName);
    }

    public void createReport(int buy, int supply, String toFileName) {
        String report = ("supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + (supply - buy));
        writeToFile(report, toFileName);
    }

    public void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter stringBuffer = new BufferedWriter(new FileWriter(file))) {
            stringBuffer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
