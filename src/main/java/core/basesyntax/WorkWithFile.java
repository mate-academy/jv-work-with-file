package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String ROW_SUPPLY = "supply";
    static final String ROW_BUY = "buy";
    static final String ROW_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String strings;
            while ((strings = bufferedReader.readLine()) != null) {
                String[] dataRow = strings.split(",");

                if (dataRow[0].equals(ROW_SUPPLY)) {
                    supply += Integer.parseInt(dataRow[1]);
                } else {
                    buy += Integer.parseInt(dataRow[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close file", e);
                }
            }
        }

        System.out.println("supply," + supply + "\nbuy," + buy + "\nresult," + (supply - buy));

        File file = new File(toFileName);
        if (file.isFile()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can`t create file: " + toFileName, e);
            }
        }

        String[] result = {ROW_SUPPLY + "," + supply + System.lineSeparator(),
                ROW_BUY + "," + buy + System.lineSeparator(),
                ROW_RESULT + "," + (supply - buy)};

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String row : result) {
                bufferedWriter.write(row);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
    }
}
