package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] result = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitedLine = line.split(",");
                if (splitedLine[INDEX].equals("supply")) {
                    result[0] += Integer.parseInt(splitedLine[AMMOUNT_INDEX]);
                } else {
                    result[1] += Integer.parseInt(splitedLine[AMMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        writeToFile(formReportFrom(result),toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String formReportFrom(int[] result) {
        return "supply,"
                + result[SUPPLY_INDEX]
                + System.lineSeparator()
                + "buy,"
                + result[BUY_INDEX]
                + System.lineSeparator()
                + "result,"
                + (result[SUPPLY_INDEX] - result[BUY_INDEX])
                + System.lineSeparator();
    }
}
