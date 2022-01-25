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
        dataWrite(toFileName, dataRead(fromFileName));
    }

    private void dataWrite(String toFileName, String dataRead) {
        File file1 = new File(toFileName);
        try {
            BufferedWriter bufWrite = new BufferedWriter(new FileWriter(file1));
            String readInfo = "";
            String [] allInfo = dataRead.split(" ");
            Arrays.sort(allInfo);
            String lineInfo = allInfo [0].substring(0, (allInfo [0].indexOf(",")));
            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < allInfo.length; i++) {
                if (i == allInfo.length - 1) {
                    if (lineInfo.equals(allInfo [i].substring(0, (allInfo [i].indexOf(","))))) {
                        count1 = Integer.parseInt(allInfo [i].substring(allInfo [i].indexOf(",")
                                + 1, allInfo [i].length())) + count1;
                        if (count2 <= count1) {
                            bufWrite.close();
                            BufferedReader bufRead1 = new BufferedReader(new FileReader(file1));
                            readInfo = bufRead1.readLine();
                            BufferedWriter bufWrite2 = new BufferedWriter(new FileWriter(file1));
                            bufWrite2.write(lineInfo + "," + count1 + System.lineSeparator());
                            bufWrite2.write(readInfo + System.lineSeparator());
                            bufWrite2.write("result" + ","
                                    + Math.abs(count1 - count2));
                            bufRead1.close();
                            bufWrite2.close();
                            continue;
                        }
                        bufWrite.write(lineInfo + "," + count1 + System.lineSeparator());
                        bufWrite.write("result" + ","
                                + Math.abs(count1 - count2));
                        continue;
                    } else {
                        bufWrite.write(lineInfo + "," + count1 + System.lineSeparator());
                        lineInfo = allInfo [i].substring(0, (allInfo [i].indexOf(",")));
                        count2 = count1;
                        count1 = Integer.parseInt(allInfo [i].substring(allInfo [i].indexOf(",")
                                + 1, allInfo [i].length()));
                        if (count2 < count1) {
                            bufWrite.close();
                            BufferedReader bufRead1 = new BufferedReader(new FileReader(file1));
                            readInfo = bufRead1.readLine();
                            BufferedWriter bufWrite2 = new BufferedWriter(new FileWriter(file1));
                            bufWrite2.write(lineInfo + "," + count1 + System.lineSeparator());
                            bufWrite2.write(readInfo + System.lineSeparator());
                            bufWrite2.write("result" + ","
                                    + Math.abs(count1 - count2));
                            bufRead1.close();
                            bufWrite2.close();
                            continue;
                        }
                        bufWrite.write(lineInfo + "," + count1 + System.lineSeparator());
                        bufWrite.write("result" + ","
                                + Math.abs(count1 - count2));
                        continue;
                    }

                }
                if (lineInfo.equals(allInfo [i].substring(0, (allInfo [i].indexOf(","))))) {
                    count1 = Integer.parseInt(allInfo[i].substring(allInfo[i].indexOf(",")
                            + 1, allInfo[i].length())) + count1;
                } else {
                    bufWrite.write(lineInfo + "," + count1 + System.lineSeparator());
                    lineInfo = allInfo [i].substring(0, (allInfo [i].indexOf(",")));
                    count2 = count1;
                    count1 = Integer.parseInt(allInfo [i].substring(allInfo [i].indexOf(",")
                            + 1, allInfo [i].length()));
                }
            }
            bufWrite.close();
        } catch (IOException e) {
            throw new RuntimeException("boo");
        }
    }

    private String dataRead(String fromFileName) {
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
