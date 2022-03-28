package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder stringBuilder;
    private int supply;
    private int buy;
    private int result;

    public WorkWithFile() {
        stringBuilder = new StringBuilder();
    }

    public void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
    }

    public void makeReport() {
        String[] arraySplit = stringBuilder.toString().split("\\W+");

        for (int i = 0; i < arraySplit.length; i++) {
            if (arraySplit[i].equals("supply")) {
                supply += Integer.parseInt(arraySplit[i + 1]);
            }
            if (arraySplit[i].equals("buy")) {
                buy += Integer.parseInt(arraySplit[i + 1]);
            }
            result = supply - buy;
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        makeReport();

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(toFileName, true));
            String[] resultArray = new String[]{"supply,", String.valueOf(supply)
                    + System.lineSeparator() + "buy,", String.valueOf(buy)
                    + System.lineSeparator() + "result,", String.valueOf(result)};
            for (String data : resultArray) {
                writer.write(data);
                writer.flush();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Can't write the data to the file" + toFileName, ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data to the file" + toFileName, e);
                }
            }
        }
    }
}
