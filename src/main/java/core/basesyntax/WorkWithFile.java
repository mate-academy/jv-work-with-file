package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private int supply;
    private int buy;
    private String[] split;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't  create a file", e);
        }
        File fileName = new File(toFileName);
        try {
            fileName.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't  create a file, e");
        }
        WorkWithFile work = new WorkWithFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                System.out.println(value);
                value = reader.readLine();
            }
            split = builder.toString().split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        String report = work.createReport(split);
        File resultReport = work.writeToFile(fileName, report);
        try {
            resultReport.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't  create a file, e");
        }
    }

    public String createReport(String[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i += 2) {
            if (array[i].equals("supply")) {
                supply += Integer.parseInt(array[i + 1]);
            } else if (array[i].equals("buy")) {
                buy += Integer.parseInt(array[i + 1]);
            }
        }

        return stringBuilder
                .append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy).toString();
    }

    public File writeToFile(File file, String string) {
        try {
            Files.write(file.toPath(), string.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
        return file;
    }
}
