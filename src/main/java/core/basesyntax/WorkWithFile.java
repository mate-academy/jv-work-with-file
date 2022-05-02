package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class WorkWithFile {
    static final int MAX_LINES = 100;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] rawFields = readToTable(fromFileName);
        String[] sumFields = sumTogether(rawFields);
        saveToFile(sumFields, toFileName);
    }

    public String [] readToTable(String fileName) {
        String [] temporaryFields = new String[MAX_LINES];
        int count = 0;
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                temporaryFields[count] = value;
                count++;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read", e);
        }
        //CLEAN FROM NULLS
        String [] resultingFields = new String [count];
        for (int i = 0; i < count; i++) {
            resultingFields [i] = temporaryFields[i];
        }
        Arrays.sort(resultingFields, Collections.reverseOrder());
        return resultingFields;
    }

    public String [] sumTogether(String [] dataFields) {
        int currentId = 0;
        String [] temporaryFields = new String[dataFields.length];
        for (int i = 0; i < dataFields.length; i++) {
            int isIt = getExistingIndex(temporaryFields, dataFields[i].split(",")[0]);
            if (isIt >= 0) {
                temporaryFields[isIt] = dataFields[i].split(",")[0] + ","
                        + (parseInt(temporaryFields[isIt].split(",")[1])
                        + parseInt(dataFields[i].split(",")[1]));
            } else {
                temporaryFields[currentId] = dataFields[i];
                currentId++;
            }
        }
        //CLEAN FROM NULLS
        String [] resultingFields = new String [currentId + 1];
        for (int i = 0; i <= currentId; i++) {
            resultingFields [i] = temporaryFields[i];
        }
        resultingFields[currentId] = "result," 
            + Math.abs(parseInt(temporaryFields[0].split(",")[1]) 
            - parseInt(temporaryFields[1].split(",")[1]));
        return resultingFields;
    }

    public void saveToFile(String [] finishedFields, String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file...", e);
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            StringBuilder stringBuilder = new StringBuilder();
            for (String dat : finishedFields) {
                stringBuilder.append(dat).append(System.lineSeparator());
            }
            bufferedWriter.write(stringBuilder.toString());
            System.out.println(stringBuilder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot add to the file...", e);
        }
    }

    public int getExistingIndex(String [] tempTable, String value) {
        int re = -1;
        for (int i = 0; i < tempTable.length; i++) {
            if (tempTable[i] != null && value.equals(tempTable[i].split(",")[0])) {
                re = i;
            }
        }
        return re;
    }
}
