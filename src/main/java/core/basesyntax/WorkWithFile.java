package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeDataToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder inputString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputString.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return inputString.toString();
    }

    private String createReport(String data) {
        final String BU = "buy";
        final String SU = "supply";
        final int ZERO = 0;
        final int ONE = 1;
        int buy = 0;
        int supply = 0;
        String[] stringsFirst = data.split("\n");
        for (String s : stringsFirst) {
            String[] stringsSecond = s.split(",");
            if (stringsSecond[ZERO].equals(BU)) {
                buy += Integer.parseInt(stringsSecond[1]);
            } else if (stringsSecond[ZERO].equals(SU)) {
                supply += Integer.parseInt(stringsSecond[ONE]);
            }
        }
        int result = supply - buy;
        return String.format("supply,%s\nbuy,%s\nresult,%s", supply, buy, result);
    }

    private void writeDataToFile(String dataResult, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(dataResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
