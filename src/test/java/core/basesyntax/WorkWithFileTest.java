package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFileTest {
    private WorkWithFile workWithFile = new WorkWithFile();

    @Test
    public void getStatisticAboutApple() {
        workWithFile.getStatistic("apple.csv", "appleResult.csv");

        String actualResult = readFromFile("appleResult.csv").trim();
        String expectedResult = "supply,188" + System.lineSeparator()
                + "buy,115" + System.lineSeparator()
                + "result,73";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutOrange() {
        workWithFile.getStatistic("orange.csv", "orangeResult.csv");

        String actualResult = readFromFile("orangeResult.csv").trim();
        String expectedResult = "supply,295" + System.lineSeparator()
                + "buy,154" + System.lineSeparator()
                + "result,141";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutGrape() {
        workWithFile.getStatistic("grape.csv", "grapeResult.csv");

        String actualResult = readFromFile("grapeResult.csv").trim();
        String expectedResult = "supply,352" + System.lineSeparator()
                + "buy,352" + System.lineSeparator()
                + "result,0";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutBanana() {
        workWithFile.getStatistic("banana.csv", "bananaResult.csv");

        String actualResult = readFromFile("bananaResult.csv").trim();
        String expectedResult = "supply,491" + System.lineSeparator()
                + "buy,293" + System.lineSeparator()
                + "result,198";
        Assert.assertEquals(expectedResult, actualResult);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }
}