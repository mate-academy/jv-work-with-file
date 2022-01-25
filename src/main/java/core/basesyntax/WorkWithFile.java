package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(countData(readFromFile(fromFileName)), toFileName);
    }

    private void writeToFile(String[] sortInfo, String toFileName) {
        File file1 = new File(toFileName);
        try (BufferedWriter bufWrite = new BufferedWriter(new FileWriter(file1));) {
            for (String writeLine : sortInfo) {
                bufWrite.write(writeLine + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("boo");
        }
    }

    private String[] countData(String allData) {
        String[] allInfo = allData.split(" ");
        Arrays.sort(allInfo);
        String[] sortInfo = new String[3];
        String lineInfo = allInfo[0].substring(0, (allInfo[0].indexOf(",")));
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < allInfo.length; i++) {
            if (lineInfo.equals(allInfo[i].substring(0, (allInfo[i].indexOf(","))))) {
                count1 = Integer.parseInt(allInfo[i].substring(allInfo[i].indexOf(",")
                        + 1, allInfo[i].length())) + count1;
            } else {
                count2 = Integer.parseInt(allInfo[i].substring(allInfo[i].indexOf(",")
                        + 1, allInfo[i].length())) + count2;
            }
        }
        sortInfo[(count1 > count2) ? 0 : 1] = (allInfo[0].substring(0,
                (allInfo[0].indexOf(","))) + "," + count1);
        sortInfo[(count1 > count2) ? 1 : 0] = (allInfo[allInfo.length - 1].substring(0,
                    (allInfo[allInfo.length - 1].indexOf(","))) + "," + count2);
        sortInfo[2] = "result," + Math.abs(count1 - count2);
        return sortInfo;
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder fileInfo = new StringBuilder();
        try {
            BufferedReader bufRead = new BufferedReader(new FileReader(file));
            String readLine = bufRead.readLine();
            while (readLine != null) {
                fileInfo.append(readLine).append(" ");
                readLine = bufRead.readLine();
            }
            bufRead.close();
        } catch (IOException e) {
            throw new RuntimeException("boo");
        }
        return fileInfo.toString();
    }
}
