package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    int countLinesInFile = 0;

    public void getStatistic(String fromFileName, String toFileName) {

        String baseString = readToString(fromFileName);
        String[] sumFields = sumTogether(baseString);
        saveToFile(sumFields, toFileName);
    }

    public String readToString(String fileName) {
        StringBuilder allInOne = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                allInOne.append(value).append(",");
                countLinesInFile++;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read data from file " + fileName, e);
        }
        return allInOne.toString();
    }

    public String [] sumTogether(String baseString) {
        String oneOfTwo = "supply";
        int supply_total = 0;
        int buy_total = 0;
        final int LINES_IN_REPORT=3;
        String [] resultFields = new String[LINES_IN_REPORT];
        for (int i = 0; i < countLinesInFile; i++) {
            if (baseString.split(",")[i*2].equals(oneOfTwo)){
                supply_total += parseInt(baseString.split(",")[i*2+1]);
            } else {
                buy_total += parseInt(baseString.split(",")[i*2+1]);
            }
            resultFields[0]="supply," + supply_total;
            resultFields[1]="buy," + buy_total;
            resultFields[2]="result," + (supply_total-buy_total);
        }
        return resultFields;
    }

    public void saveToFile(String [] finishedFields, String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file " + fileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String dat : finishedFields) {
                stringBuilder.append(dat).append(System.lineSeparator());
            }
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot add data to the file " + fileName, e);
        }
    }
}
