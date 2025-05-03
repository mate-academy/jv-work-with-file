package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        String baseString = readToString(fromFileName);
        String[] sumFields = sumTogether(baseString);
        saveToFile(sumFields, toFileName);
    }

    private String readToString(String fileName) {
        StringBuilder fileData = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                fileData.append(value).append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read data from file " + fileName, e);
        }
        return fileData.toString();
    }

    private String [] sumTogether(String baseString) {
        String supply = "supply";
        int supplyTotal = 0;
        int buyTotal = 0;
        for (int i = 0; i < baseString.split(",").length / 2; i++) {
            if (baseString.split(",")[i * 2].equals(supply)) {
                supplyTotal += parseInt(baseString.split(",")[i * 2 + 1]);
            } else {
                buyTotal += parseInt(baseString.split(",")[i * 2 + 1]);
            }
        }
        return new String[]{"supply," + supplyTotal, "buy," + buyTotal, "result,"
                + (supplyTotal - buyTotal)};
    }

    private void saveToFile(String [] finishedFields, String fileName) {
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
