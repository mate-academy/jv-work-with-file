package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringsInCsv = gatherDataFromFile(fromFileName);
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(calculateData(stringsInCsv));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not write to file",e);
        }
    }

    private List<String> gatherDataFromFile(String fromFileName) {
        List<String> stringsInCsv;
        try {
            stringsInCsv = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file", e);
        }
        return stringsInCsv;
    }

    private String calculateData(List<String> data) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String operation : data) {
            if (operation.split(",")[0].equals("supply")) {
                supply += Integer.parseInt(operation.split(",")[1]);
            } else if (operation.split(",")[0].equals("buy")) {
                buy += Integer.parseInt(operation.split(",")[1]);
            }
        }
        result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
