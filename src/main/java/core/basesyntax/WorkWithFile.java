package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME = 0;
    private static final int AMOUNT = 1;
    private static final String EXCEPTION_READ_TEXT = "Can't read the file";
    private static final String EXCEPTION_WRITE_TEXT = "Can't write to the file";

    public void getStatistic(String fromFileName, String toFileName) {
        String textToWrite = readFile(fromFileName);
        writeFile(textToWrite, toFileName);
    }

    private String readFile(String readFileName) {
        File fileToRead = new File(readFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String value = reader.readLine();
            while (value != null) {
                String[] splitValue = value.split(",");
                if (splitValue[NAME].equals("supply")) {
                    supply += Integer.parseInt(splitValue[AMOUNT]);
                }
                if (splitValue[NAME].equals("buy")) {
                    buy += Integer.parseInt(splitValue[AMOUNT]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_READ_TEXT, e);
        }

        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        return builder.toString();
    }

    private void writeFile(String text, String writeFileName) {
        File fileToWrite = new File(writeFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_WRITE_TEXT, e);
        }
    }

}
