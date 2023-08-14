package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        Path reportPath = Paths.get(toFileName);
        try {
            if (Files.exists(reportPath)) {
                Files.delete(reportPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't remove file", e);
        }
        String[] reportData = createReport(readFromFile(fromFile));
        writeToFile(reportData, toFile);
    }

    private String[] readFromFile(File inputData) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputData));
            String lineValue = bufferedReader.readLine();
            while (lineValue != null) {
                stringBuilder.append(lineValue).append(",");
                lineValue = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(",");
    }

    private String[] createReport(String[] readData) {
        String supplyString = "supply";
        String buyString = "buy";
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < readData.length; i += 2) {
            if (readData[i].equals(supplyString)) {
                supplyCount += Integer.parseInt(readData[i + 1]);
            } else {
                buyCount += Integer.parseInt(readData[i + 1]);
            }
        }
        return new String[] {supplyString + "," + supplyCount,
                             buyString + "," + buyCount,
                             "result" + "," + (supplyCount - buyCount)};
    }

    private void writeToFile(String[] reportData, File toFile) {
        for (String data : reportData) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't write file", e);
            }
        }
    }
}
