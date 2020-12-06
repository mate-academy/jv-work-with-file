package core.basesyntax;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String FIRST_VALUE = "supply";
    private static final String SECOND_VALUE = "buy";
    private static final String RESULT = "result";
    private static final int countOfIteration = 3;
    private static final String[] values = {FIRST_VALUE, SECOND_VALUE, RESULT};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataForOutput = getLinesFromFile(fromFileName);
        HashMap<String, Integer> readyData = getFormattedInformation(dataForOutput);
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
        result.put(RESULT, result.get(FIRST_VALUE) - result.get(SECOND_VALUE));
        return result;
    }

    private void putInformationInNewFile(HashMap<String, Integer> data, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            for (String information : values) {
                StringBuffer value = new StringBuffer(information).append(",").append(data.get(information)).append(System.lineSeparator());
                writer.write(value.toString());
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem to write new file.", e);
        }

    }
}

/*

if (result.containsKey(tempData[0])) {
                result.put(tempData[0]
                        , result.get(tempData[0]) + Integer.parseInt(tempData[1]));
            } else {
                result.put(tempData[0], Integer.parseInt(tempData[1]));
            }
 */