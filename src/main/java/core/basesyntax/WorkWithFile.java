package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private final List<String> strlist = new ArrayList<>();
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeResult(toFileName);
    }

    public void readFromFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                strlist.add(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found. ", e);
        }
    }

    public int getBuy() {
        String[] array;
        for (int i = 0; i < strlist.size(); i++) {
            array = strlist.get(i).split("\\w+");
            for (String s : array) {
                if (s.equals(BUY)) {
                    buy += Integer.parseInt(array[array.length - 1]);
                    break;
                }
            }
        }
        return buy;
    }

    public int getSupply() {
        String[] arr;
        for (int i = 0; i < strlist.size(); i++) {
            arr = strlist.get(i).split("\\w+");
            for (String c : arr) {
                if (c.equals(SUPPLY)) {
                    supply += Integer.parseInt(arr[arr.length - 1]);
                    break;
                }
            }
        }
        return supply;
    }

    public int getResult() {
        return getSupply() - getBuy();
    }

    public void writeResult(String toFileName) {
        File file = new File(toFileName);
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("File is created.");
            }
        } catch (IOException e) {
            throw new RuntimeException("This file is created", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(SUPPLY + "," + getSupply() + System.lineSeparator());
            bufferedWriter.write(BUY + "," + getBuy() + System.lineSeparator());
            bufferedWriter.write(RESULT + "," + getResult());
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
