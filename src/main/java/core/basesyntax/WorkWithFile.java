package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String SUPPLY = "supply";
    private static String BUY = "buy";
    private static String RESULT = "result";
    private static int OPERATION_TYPE_INDEX = 0;
    private static int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readLinesFromFile(fromFileName);
        String report = makeReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readLinesFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String linesFromFile) {
        int supply = 0;
        int buy = 0;
        String[] oneString = new String[2];
        String[] linesArray = linesFromFile.split(" ");
        for (int i = 0; i < linesArray.length; i++) {
            oneString = linesArray[i].split(",");
            if (oneString[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(oneString[AMMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(oneString[AMMOUNT_INDEX]);
            }
        }
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append(SUPPLY + ",").append(supply).append(System.lineSeparator())
                .append(BUY + ",").append(buy).append(System.lineSeparator())
                .append(RESULT + ",").append(supply - buy);
        return resultStringBuilder.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
