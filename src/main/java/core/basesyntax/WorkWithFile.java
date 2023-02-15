package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    private String readFromFile(File pathFile) {
        StringBuilder res = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String line = reader.readLine();
            while (line != null) {
                res.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
        return res.toString();
    }

    private String createReport(String dataForReport) {
        StringBuilder report = new StringBuilder();
        int buy = 0;
        int supply = 0;

        for (String data : dataForReport.split(System.lineSeparator())) {
            switch (data.split(",")[OPERATION]) {
                case "buy":
                    buy += Integer.parseInt(data.split(",")[AMOUNT]);
                    break;

                case "supply":
                    supply += Integer.parseInt(data.split(",")[AMOUNT]);
                    break;

                default:
                    break;
            }
        }

        return report.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy).toString();
    }

    private void writeFile(String report, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(
                createReport(
                readFromFile(
                        new File(fromFileName)
                )
                ), new File(toFileName)
        );
    }
}
