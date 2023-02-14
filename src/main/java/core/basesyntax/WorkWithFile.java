package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String COMMA = ",";
    private static final String SAMPLE = " ";
    private static final String SU = "su";
    private static final String BU = "bu";
    private static final String SUPPLY1 = "supply";
    private static final String BUY1 = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] array1 = readFromFile(fromFileName);
        String newString = getReport(array1);
        writeReport(newString, toFileName);
    }

    public static String[] readFromFile(String newFile) {
        File file = new File(newFile);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SAMPLE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String[] array = builder.toString().split(SAMPLE);
        return array;
    }

    public static String getReport(String[] array) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].substring(0, 2).equals(SU)) {
                int l = array[i].length();
                supply = supply + Integer.parseInt(array[i].substring(7, l));
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].substring(0, 2).equals(BU)) {
                int l1 = array[i].length();
                buy = buy + Integer.parseInt(array[i].substring(4, l1));
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY1).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY1).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return stringBuilder.toString();
    }

    public static void writeReport(String str, String toFileName) {
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(str.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}

