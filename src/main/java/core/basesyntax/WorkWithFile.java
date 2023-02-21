package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        StringBuilder builderWordsAll = new StringBuilder();
        String notWord = "\\W+";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builderWordsAll.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] datesFile = builderWordsAll.toString().split(notWord);
        for (int i = 0; i < datesFile.length; i++) {
            if (datesFile[i].equals("supply")) {
                supply = supply + Integer.parseInt(datesFile[i + 1]);
            }
            if (datesFile[i].equals("buy")) {
                buy = buy + Integer.parseInt(datesFile[i + 1]);
            }
        }
        result = supply - buy;
        String[] forReport = new String[]{"supply", String.valueOf(supply),
                "buy", String.valueOf(buy), "result", String.valueOf(result)};
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < forReport.length; i = i + 2) {
                bufferedWriter.write(forReport[i]);
                bufferedWriter.write(",");
                bufferedWriter.write(forReport[i + 1]);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write date to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }

    }
}
