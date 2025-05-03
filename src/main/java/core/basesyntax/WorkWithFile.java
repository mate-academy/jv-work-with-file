package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);

        EntitySupplyBuy entitySupplyBuy = createAndFillEntity(dataFromFile);

        String report = makeReport(
                entitySupplyBuy.getBuy(),
                entitySupplyBuy.getSupply(),
                entitySupplyBuy.getResult()
        );

        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null) {
                contentBuilder
                        .append(line)
                        .append(',');

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }

        return contentBuilder.toString();
    }

    private void writeToFile(String fileName, String content) {
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }
    }

    private EntitySupplyBuy createAndFillEntity(String dataFromFile) {
        String[] dataFormFileArr = dataFromFile.split(",");
        EntitySupplyBuy entitySupplyBuy = new EntitySupplyBuy(0, 0);

        for (int i = 0; i < dataFormFileArr.length; i += 2) {
            entitySupplyBuy.changeEntityState(
                    dataFormFileArr[i],
                    Integer.parseInt(dataFormFileArr[i + 1]));
        }

        return entitySupplyBuy;
    }

    private String makeReport(int buy, int supply, int result) {
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder
                .append("supply,")
                .append(supply)
                .append('\n')
                .append("buy,")
                .append(buy)
                .append('\n')
                .append("result,")
                .append(result);

        return reportBuilder.toString();
    }
}
