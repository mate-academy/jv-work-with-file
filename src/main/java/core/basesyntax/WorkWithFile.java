package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String lineOfData : getData(fromFileName)) {
            if (getOperationType(lineOfData).equals("supply")) {
                supply += getOperationAmount(lineOfData);
            } else {
                buy += getOperationAmount(lineOfData);
            }
        }

        int result = supply - buy;
        StringBuilder textTorWrite = new StringBuilder()
                .append("supply,").append(supply)
                .append("\nbuy,").append(buy)
                .append("\nresult,").append(result);
        createFile(toFileName);
        writeData(toFileName, textTorWrite.toString());
    }

    private static List<String> getData(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file", e);
        }
    }

    private static void writeData(String toFileName, String text) {
        try {
            Files.write(Path.of(toFileName), text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }

    private static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cant create file" + e);
        }
    }

    private String getOperationType(String lineOfData) {
        return lineOfData.split(",")[0];
    }

    private int getOperationAmount(String lineOfData) {
        return Integer.parseInt(lineOfData.split(",")[1]);
    }
}
