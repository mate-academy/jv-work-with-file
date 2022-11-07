package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    private static final int INDEX_OF_NUMBER = 1;

    public int[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        int result;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());

                if (value.startsWith("buy")) {
                    buy += Integer.parseInt(value.substring(value.indexOf(",") + INDEX_OF_NUMBER));
                } else if (value.startsWith("supply")) {
                    supply += Integer.parseInt(value.substring(value.indexOf(",")
                            + INDEX_OF_NUMBER));
                }
                value = reader.readLine();
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }

        int []dataForReport = new int[]{supply, buy, result};
        return dataForReport;
    }
}
