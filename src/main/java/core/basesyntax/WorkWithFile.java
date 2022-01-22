package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(makeReport(readFile(fromFileName)), toFileName);

    }

    private String[] readFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read", e);
        }
        return list.toArray(new String[0]);
    }

    private String makeReport(String[] arrOfInfo) {
        int supply = 0;
        int buy = 0;

        for (String s : arrOfInfo) {
            String[] split = s.split(SEPARATOR);
            if (split[0].startsWith("s")) {
                supply = supply + Integer.parseInt(split[1]);
            } else {
                buy = buy + Integer.parseInt(split[1]);
            }
        }

        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeFile(String dataToWrite, String fileWhereWrite) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileWhereWrite))) {
            bw.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
