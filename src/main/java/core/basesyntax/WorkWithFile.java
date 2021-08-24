package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatisticToTheFile(readFile(fromFileName), toFileName);
    }

    private String readFile(String fileName) {
        int supply = 0;
        int buy = 0;
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
            String[] split = builder.toString().split(" ");
            for (String string : split) {
                String[] splitString = string.split(",");
                if (splitString[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(splitString[1]);
                } else {
                    buy += Integer.parseInt(splitString[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + (supply - buy);
    }

    private void writeStatisticToTheFile(String statistic, String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
            Files.write(file.toPath(), statistic.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't create or write file ", e);
        }
    }
}


