package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final LifeHackCalculus lifeHackCalculus = new LifeHackCalculus();

    public void getStatistic(String fromFileName, String toFileName) {
        lifeHackCalculus.getCalculations(fromFileName);

        String text = "supply," + lifeHackCalculus.getSupply() + System.lineSeparator()
                + "buy," + lifeHackCalculus.getBuy() + System.lineSeparator()
                + "result," + lifeHackCalculus.getResult();

        writeInFile(text, toFileName);
    }

    private void writeInFile(String text, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write", e);
        }
    }
}
