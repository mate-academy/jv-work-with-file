package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String ROW_SUPPLY = "supply";
    static final String ROW_BUY = "buy";
    static final String ROW_RESULT = "result";
    static final String SEPARATOR = ",";
    static final int RESET_DATA = 0;
    static final int INDEX_FIRST = 0;
    static final int INDEX_SECOND = 1;
    private int supply = 0;
    private int buy = 0;

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName, createReport());
    }

    public void readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String strings;
            while ((strings = bufferedReader.readLine()) != null) {
                preReport(strings);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
    }

    public void preReport(String string) {
        int supply = getSupply();
        int buy = getBuy();
        String[] dataRow = string.split(SEPARATOR);
        if (dataRow[INDEX_FIRST].equals(ROW_SUPPLY)) {
            supply += Integer.parseInt(dataRow[INDEX_SECOND]);
            setSupply(supply);
        } else {
            buy += Integer.parseInt(dataRow[INDEX_SECOND]);
            setBuy(buy);
        }
    }

    public String[] createReport() {
        int supply = getSupply();
        int buy = getBuy();
        String[] result = {ROW_SUPPLY + "," + supply + System.lineSeparator(),
                ROW_BUY + "," + buy + System.lineSeparator(),
                ROW_RESULT + "," + (supply - buy)};
        return result;
    }

    public void createFile(String toFileName) {
        File file = new File(toFileName);
        if (file.isFile()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can`t create file: " + toFileName, e);
            }
        }
    }

    public void writeFile(String toFileName, String[] result) {
        createFile(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String row : result) {
                bufferedWriter.write(row);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
        setBuy(RESET_DATA);
        setSupply(RESET_DATA);
    }
}
