package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readeFromFile(fromFileName);
        String report = createReport(infoFromFile);
        writeToFile(report, toFileName);
    }

    private String readeFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String resultString = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            resultString = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("There is no such file" + e);
        }
        return resultString;
    }

    private void writeToFile(String report, String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't delete file ", e);
        }

        String[] reportArray = report.split("\\R");
        File file = new File(toFileName);
        for (String value: reportArray) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(value);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file" + e);
            }
        }
    }

    private String createReport(String resultString) {
        String[] values = resultString.split("\\R|,");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].startsWith("supply")) {
                supply += Integer.parseInt(values[i + 1]);
            }
            if (values[i].startsWith("buy")) {
                buy += Integer.parseInt(values[i + 1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }
}
