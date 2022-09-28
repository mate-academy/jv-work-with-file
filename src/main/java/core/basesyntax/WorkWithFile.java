package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, resultData(read(fromFileName)));
    }

    private String[] resultData(String readedLine) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        String[] strings = readedLine.split(System.lineSeparator());
        for (String s : strings) {
            String[] tempArray = s.split(",");
            for (int i = 0; i < tempArray.length; i++) {
                if (tempArray[i].equals("supply")) {
                    supply = supply + Integer.parseInt(tempArray[VALUE_INDEX]);
                } else if (tempArray[i].equals("buy")) {
                    buy = buy + Integer.parseInt(tempArray[VALUE_INDEX]);
                }
            }
        }

        result = supply - buy;
        return new String[]{SUPPLY + SEPARATOR + supply + System.lineSeparator(),
                BUY + SEPARATOR + buy + System.lineSeparator(),
                RESULT + SEPARATOR + result + System.lineSeparator()};
    }

    private void writeToFile(String toFileName, String[] resultedArray) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + file, e);
        }

        for (String s : resultedArray) {
            try {
                Files.write(file.toPath(), s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write file" + toFileName, e);
            }
        }
    }

    private String read(String fromFileName) {
        StringBuilder tempString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                tempString.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file! " + fromFileName, e);
        }

        return tempString.toString();
    }
}

