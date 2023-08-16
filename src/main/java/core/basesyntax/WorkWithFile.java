package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIAL_CHAR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private StringBuilder getFileStat(String fromFileName) {
        File fileToRead = new File(fromFileName);
        String[] text;
        String value;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            int supply = 0;
            int buy = 0;
            int result = 0;
            while ((value = bufferedReader.readLine()) != null) {
                text = value.split(SPECIAL_CHAR);
                for (String s : text) {
                    if (s.equals(SUPPLY)) {
                        supply += Integer.valueOf(text[1]);
                    } else if (s.equals(BUY)) {
                        buy += Integer.valueOf(text[1]);
                    }
                }
            }
            result = supply - buy;
            stringBuilder.append("supply," + supply + System.lineSeparator())
                    .append("buy," + buy + System.lineSeparator())
                    .append("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return stringBuilder;
    }

    private void getWrittenFile(StringBuilder stringBuilder, String toFileName) {
        File fileToWrite = new File(toFileName);
        String[] stats = stringBuilder.toString().split(System.lineSeparator());
        if (fileToWrite.exists()) {
            fileToWrite.delete();
        }
        try {
            fileToWrite.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create this file", e);
        }
        for (String stat : stats) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(fileToWrite, true))) {
                bufferedWriter.write(stat + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write this file",e);
            }
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = getFileStat(fromFileName);
        getWrittenFile(stringBuilder,toFileName);
    }
}
