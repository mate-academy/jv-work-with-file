package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    public void readFile(String fileName) {
        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(Paths.get(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value.toCharArray()[0] == 'b') {
                    setBuy(getBuy() + Integer.parseInt(value.split(",")[1]));
                } else {
                    setSupply(getSupply() + Integer.parseInt(value.split(",")[1]));
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }

    public void writeFile(String fileName) {
        try (BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(Paths.get(fileName))) {
            StringBuilder stringBuilder = new StringBuilder("supply,");
            stringBuilder.append(getSupply()).append(System.lineSeparator())
                    .append("buy,").append(getBuy()).append(System.lineSeparator())
                    .append("result,").append(getSupply() - getBuy());
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + fileName, e);
        }
    }
}
