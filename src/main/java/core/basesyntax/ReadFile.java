package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private int supply;
    private int buy;
    private int result;

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int readFile(String fromFileName) {
        File file = new File(fromFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String word;
            int indexOf;

            while ((line = br.readLine()) != null) {
                indexOf = line.indexOf(',');
                word = line.substring(0, indexOf);
                if (word.equals("supply")) {
                    supply += Integer.parseInt(line.substring(indexOf + 1, line.length()));
                } else if (word.equals("buy")) {
                    buy += Integer.parseInt(line.substring(indexOf + 1, line.length()));
                }

            }
            return result = supply - buy;

        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fromFileName, e);
        }
    }
}
