package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SPEC_CHAR = ",";
    private static final int TYPE_POS = 0;
    private static final int AMOUNT_POS = 1;
    private static final int SUPPLY_POS = 0;
    private static final int COUNT_SUPPLY_POS = 0;
    private static final int COUNT_BUY_POS = 1;
    private static final int COUNT_RESULT_POS = 2;
    private static final String[] DEFAULT_REPORT = {"supply", "buy", "result"};

    public String readFromFile(String string) {
        File file = new File(string);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ooops! File not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Ooops! Can't read file.", e);
        }
        return getDefaultReport(DEFAULT_REPORT, getCount(builder));
    }

    private int[] getCount(StringBuilder builder) {
        int[] getCount = new int[3];
        String[] arrayOfStrings = builder.toString().split(SEPARATOR);
        String[] lowerString;
        for (String arrayOfString : arrayOfStrings) {
            lowerString = arrayOfString.split(SPEC_CHAR);
            if (lowerString[TYPE_POS].equals(DEFAULT_REPORT[SUPPLY_POS])) {
                getCount[COUNT_SUPPLY_POS] += Integer.parseInt(lowerString[AMOUNT_POS]);
            } else {
                getCount[COUNT_BUY_POS] += Integer.parseInt(lowerString[AMOUNT_POS]);
            }
        }
        getCount[COUNT_RESULT_POS] = getCount[COUNT_SUPPLY_POS] - getCount[COUNT_BUY_POS];
        return getCount;
    }

    private String getDefaultReport(String[] strings, int[] counts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]).append(SPEC_CHAR).append(counts[i]).append(SEPARATOR);
        }
        return builder.toString();
    }
}
