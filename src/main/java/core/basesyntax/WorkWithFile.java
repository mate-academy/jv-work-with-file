package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] result = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitedLine = line.split(",");
                if (splitedLine[0].equals("supply")) {
                    result [0] += Integer.parseInt(splitedLine[1]);
                } else {
                    result [1] += Integer.parseInt(splitedLine[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        writeToFile(report(result), toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);

        }
    }

    private String report(int[] result) {
        return "supply,"
                + result[0]
                + System.lineSeparator()
                + "buy,"
                + result[1]
                + System.lineSeparator()
                + "result,"
                + (result[0] - result[1])
                + System.lineSeparator();
    }
}
