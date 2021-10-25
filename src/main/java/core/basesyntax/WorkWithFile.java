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
    private static final String[] NAMES = new String[]{"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        String res = readFile(fromFileName);
        String[] values = res.split(" ");
        write(values, toFileName);
    }

    private void write(String[] values, String toFileName) {
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
            return getDividedInfo(values, NAMES);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private String getDividedInfo(String values, String[] names) {
        int price = 0;
        int supply = 0;
        int buy = 0;
        int resultPrice;

        StringBuilder stringBuilder = new StringBuilder();
        String[] data = values.split(" ");

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitedData = data[j].split(",");
                if (names[i].equals(splitedData[INDEX_NAME])) {
                    price += Integer.parseInt(splitedData[INDEX_PRICE]);
                }
            }
            stringBuilder.append(names[i]).append(",")
                    .append(price).append(" ");
            price = 0;
        }
        String builderToStr = stringBuilder.toString();
        String[] data2 = builderToStr.split(" ");
        for (int i = 0; i < data2.length; i++) {
            String[] splitedData = data2[i].split(",");
            if (splitedData[INDEX_NAME].equals("supply")) {
                supply = Integer.parseInt(splitedData[INDEX_PRICE]);
            } else {
                buy = Integer.parseInt(splitedData[INDEX_PRICE]);
            }
        }
        resultPrice = supply - buy;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(builderToStr)
                .append("result")
                .append(",")
                .append(resultPrice);
        return stringBuilder1.toString();
    }
}
