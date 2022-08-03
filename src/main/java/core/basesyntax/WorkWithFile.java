package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    private int[] result = new int[2];
    private int totalSupply = result[0];
    private int totalBuy = result[1];

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitedLine = line.split(",");
                if (splitedLine[0].equals("supply")) {
                    totalSupply += Integer.parseInt(splitedLine[1]);
                } else {
                    totalBuy += Integer.parseInt(splitedLine[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        writeToFile(formReportFrom(result), toFileName);
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
                + totalSupply
                + System.lineSeparator()
                + "buy,"
                + totalBuy
                + System.lineSeparator()
                + "result,"
                + (totalSupply - totalBuy)
                + System.lineSeparator();
    }
}
