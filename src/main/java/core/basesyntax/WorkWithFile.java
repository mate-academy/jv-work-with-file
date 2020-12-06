package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String[] values = {SUPPLY, BUY, RESULT};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromOutput = getLinesFromFile(fromFileName);
        HashMap<String, Integer> readyData = getFormattedInformation(dataFromOutput);
        putInformationInNewFile(readyData, toFileName);
    }

    private List<String> getLinesFromFile(String fileName) {
        List<String> listOfInformation = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                listOfInformation.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("problem with read file. Try again", e);
        }
        return listOfInformation;
    }

    private HashMap<String, Integer> getFormattedInformation(List<String> data) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String line : data) {
            String[] tempData = line.split(",");
            result.merge(tempData[0], Integer.parseInt(tempData[1]), Integer::sum);
        }
        result.put(RESULT, result.get(SUPPLY) - result.get(BUY));
        return result;
    }

    private void putInformationInNewFile(HashMap<String, Integer> data, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String information : values) {
                StringBuilder dataToFile = new StringBuilder(information).append(",")
                        .append(data.get(information)).append(System.lineSeparator());
                writer.write(dataToFile.toString());
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Problem to write new file.", e);
        }
    }
}
