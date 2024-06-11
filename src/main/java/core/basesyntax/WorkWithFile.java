package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            String report = generateReport(data);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        data = data.replaceAll("\\[|\\]", "");
        String[] stringArray = data.split(", ");
        System.out.println(data);
        report.append("supply,").append(Integer.parseInt(stringArray[0]))
                .append(System.lineSeparator());
        report.append("buy,").append(Integer.parseInt(stringArray[1]))
                .append(System.lineSeparator());
        report.append("result,").append(Integer.parseInt(stringArray[2]))
                .append(System.lineSeparator());
        return report.toString();
    }

    private String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            int[] array = new int[3];
            int supply = 0;
            int buy = 0;
            int result = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] separatedLine = line.split(",");
                if (separatedLine[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(separatedLine[1]);
                } else if (separatedLine[0].equals(BUY)) {
                    buy += Integer.parseInt(separatedLine[1]);
                }
                line = reader.readLine();
            }
            result = supply - buy;
            array[0] = supply;
            array[1] = buy;
            array[2] = result;
            return Arrays.toString(array);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void writeToFile(String fileName, String report) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
