package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String report = this.generateReport(fromFileName);
        this.writeToFile(report, toFileName);
        System.out.println(report);
    }

    private String generateReport(final String fromFileName) {
        File file = new File("./" + fromFileName);
        StringBuilder result = new StringBuilder();

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)

        ) {
            String nextString = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;

            while (nextString != null) {
                String[] nextStringArray = nextString.split(",");
                int amount = Integer.parseInt(nextStringArray[1]);

                if (nextStringArray[0].equals("supply")) {
                    supply += amount;
                } else {
                    buy += amount;
                }
                nextString = bufferedReader.readLine();
            }

            result.append("supply")
                    .append(",")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy")
                    .append(",")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(",")
                    .append(supply - buy);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file: File not Found", e);
        }
        return result.toString();
    }

    private void writeToFile(final String report, final String toFileName) {
        try (
                FileWriter fileWriter = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: File not Found", e);
        }
    }
}


