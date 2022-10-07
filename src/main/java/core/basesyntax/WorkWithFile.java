package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFile(fromFileName)));
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        int buy = 0;
        int supply = 0;
        String[] dataArray = data.split(",");
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals(SUPPLY)) {
                supply += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals(BUY)) {
                buy += Integer.parseInt(dataArray[i + 1]);
            }
        }
        int result = supply - buy;
        return (SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result);
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        char[] data2 = data.toCharArray();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        for (char s : data2) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                bufferedWriter.write(s);
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't write file" + toFileName, e);
            }
        }
    }
}



