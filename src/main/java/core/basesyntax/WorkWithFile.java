package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int INDEX_NAME = 0;
    private static final int INDEX_PRICE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFile(fromFileName);
        write(readData, toFileName);
    }

    private void write(String readData, String toFileName) {
        String[] values = readData.split(" ");

        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        for (String value : values) {
            try {
                Files.write(file.toPath(), (value + System.lineSeparator()).getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
            String values = stringBuilder.toString();
            return getDividedInfo(values);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private String getDividedInfo(String values) {
        int supply = 0;
        int buy = 0;

        StringBuilder stringBuilder = new StringBuilder();
        String[] data = values.split(" ");

        for (int j = 0; j < data.length; j++) {
            String[] splitedData = data[j].split(",");
            if ("supply".equals(splitedData[INDEX_NAME])) {
                supply += Integer.parseInt(splitedData[INDEX_PRICE]);
            } else {
                buy += Integer.parseInt(splitedData[INDEX_PRICE]);;
            }
        }

        stringBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy).append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);

        return stringBuilder.toString();
    }
}
