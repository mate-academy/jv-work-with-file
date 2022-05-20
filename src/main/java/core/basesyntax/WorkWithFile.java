package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String RESULT = "result,";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    private final StringBuilder dataFromFile = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        this.readFile(fromFileName);
        String[][] csv = fileToCvs(dataFromFile);
        String report = createReport(csv);
        writeFile(toFileName, report);
    }

    private void readFile(String fileName) {
        final File fromFile = new File(fileName);
        String value;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private void writeFile(String fileName, String data) {
        final File toFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String[][] fileToCvs(StringBuilder data) {
        String[] temp = data.toString().split(System.lineSeparator());
        String[][] csv = new String[temp.length][2];
        for (int i = 0; i < temp.length; i++) {
            String[] tempo = temp[i].split(",");
            csv[i][0] = tempo[0];
            csv[i][1] = tempo[1];
        }
        return csv;
    }

    private String createReport(String[][] csv) {
        int supply = 0;
        int buy = 0;
        int result;
        String report;

        for (String[] strings : csv) {
            if (strings[0].equals(SUPPLY)) {
                supply += Integer.parseInt(strings[1]);
            } else if (strings[0].equals(BUY)) {
                buy += Integer.parseInt(strings[1]);
            }
        }
        result = supply - buy;
        report = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + result;
        return report;
    }
}
