package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private File fileRead;
    private File fileWrite;

    public void getStatistic(String fromFileName, String toFileName) {
        fileRead = new File(fromFileName);
        fileWrite = new File(toFileName);
        getRead(fileRead);
        getWrite(fileWrite);
    }

    public String getRead(File file) {
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] newOne = builder.toString().split("\n");
            StringBuilder builder1 = new StringBuilder();
            for (int i = 0; i < newOne.length; i++) {
                int indexOf = newOne[i].indexOf(",");
                if (indexOf == 6) {
                    String a = newOne[i].substring(indexOf + 1);
                    supply = Integer.valueOf(a.trim()) + supply;
                } else {
                    String a = newOne[i].substring(indexOf + 1);
                    buy = Integer.valueOf(a.trim()) + buy;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data file" + file, e);
        }
        int result = supply - buy;
        String supply1 = "supply," + supply;
        String buy1 = "buy," + buy;
        String result1 = "result," + result;
        String toWrite = supply1 + System.lineSeparator() + buy1 + System.lineSeparator()
                + result1 + System.lineSeparator();;
        return toWrite;
    }

    public void getWrite(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite,true))) {
            writer.write(" ");
            writer.write(getRead(fileRead));
        } catch (IOException e) {
            throw new RuntimeException("Can't write");
        }
    }
}
