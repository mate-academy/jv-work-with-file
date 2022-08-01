package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class WorkWithFile {
    private static final int OPERATION_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] strings = reedToStringArray(fromFileName);
        for (String string : strings) {
            String[] split = string.split(REGEX);
            if (split[OPERATION_COLUMN].equals(SUPPLY)) {
                supply += Integer.parseInt(split[AMOUNT_COLUMN]);
            }
            if (split[OPERATION_COLUMN].equals(BUY)) {
                buy += Integer.parseInt(split[AMOUNT_COLUMN]);
            }
        }
        writeToFile(toFileName, supply, buy);
    }

    private String[] reedToStringArray(String fromFileName) {
        File file = new File(fromFileName);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String string = bufferedReader.readLine();
            while (string != null) {
                arrayList.add(string);
                string = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        File file = new File(toFileName);
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + file, e);
        }
    }
}
