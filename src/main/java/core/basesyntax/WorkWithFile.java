package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeToFile(report, toFileName);
    }

    private String createReport(String fromFileName) {
        String report;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            report = calculateData(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return report;
    }

    private String calculateData(BufferedReader bufferedReader) throws IOException {
        String inputLine;
        int result;
        int supply = 0;
        int buy = 0;
        while ((inputLine = bufferedReader.readLine()) != null) {
            String[] splitArray = inputLine.split(",");
            switch (splitArray[FIRST_POSITION]) {
                case "supply":
                    supply += Integer.parseInt(splitArray[SECOND_POSITION]);
                    break;
                case "buy":
                    buy += Integer.parseInt(splitArray[SECOND_POSITION]);
                    break;
                default:
                    System.out.println("There is no acceptable option");
            }
        }
        result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
