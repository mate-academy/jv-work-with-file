package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int countLinesInFile = 0;

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
        int supplyTotal = 0;
        int buyTotal = 0;
        final int linesInReport = 3;
        String [] resultFields = new String[linesInReport];
        for (int i = 0; i < countLinesInFile; i++) {
            if (baseString.split(",")[i * 2].equals(oneOfTwo)) {
                supplyTotal += parseInt(baseString.split(",")[i * 2 + 1]);
            } else {
                buyTotal += parseInt(baseString.split(",")[i * 2 + 1]);
            }
            resultFields[0] = "supply," + supplyTotal;
            resultFields[1] = "buy," + buyTotal;
            resultFields[2] = "result," + (supplyTotal - buyTotal);
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
