package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile implements Readable {
    public static final String SPLIT_BY = ",";
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        write(fromFileName,toFileName);
    }

    @Override
    public String read(String fromFileName) {
        String line = "";
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            while ((line = reader.readLine()) != null) {
                String[] fruits = line.split(SPLIT_BY);
                if (fruits[FIRST_INDEX].equals("supply")) {
                    supply += Integer.parseInt(fruits[SECOND_INDEX]);
                } else {
                    buy += Integer.parseInt(fruits[SECOND_INDEX]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator()).toString();
    }

    @Override
    public void write(String fromFileName, String toFileName) {
        try {
            FileWriter writer = new FileWriter(toFileName);
            writer.append(read(fromFileName));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
