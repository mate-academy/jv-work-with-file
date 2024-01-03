package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = getStringFromFile(fromFileName);
        String[] lines = getArrayFromString(fileContent);

        int supply = getAmountOfElement(lines, SUPPLY);
        int buy = getAmountOfElement(lines, BUY);

        String result = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);

        writeStringToFile(result, toFileName);
    }

    private String getStringFromFile(String fromFile) {
        try {
            return Files.readString(Paths.get(fromFile));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
    }

    private String[] getArrayFromString(String string) {
        return string.split("\\n");
    }

    private int getAmountOfElement(String[] strings, String element) {
        int amount = 0;
        for (String line : strings) {
            String[] split = line.split(",");
            if (element.equals(split[0])) {
                amount += Integer.parseInt(split[1]);
            }
        }
        return amount;
    }

    private void writeStringToFile(String content, String toFileName) {
        Path filePath = Paths.get(toFileName);
        try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
