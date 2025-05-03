package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class WorkWithFileTest {
    private final WorkWithFile workWithFile = new WorkWithFile();
    private static final String APPLE_RESULT_FILE = "appleResult.csv";
    private static final String GRAPE_RESULT_FILE = "grapeResult.csv";
    private static final String ORANGE_RESULT_FILE = "orangeResult.csv";
    private static final String BANANA_RESULT_FILE = "bananaResult.csv";

    public WorkWithFileTest() {
    }

    @After
    public void clearResults() {
        try {
            Files.deleteIfExists(Path.of("appleResult.csv"));
            Files.deleteIfExists(Path.of("grapeResult.csv"));
            Files.deleteIfExists(Path.of("orangeResult.csv"));
            Files.deleteIfExists(Path.of("bananaResult.csv"));
        } catch (IOException var2) {
            throw new RuntimeException("Can't correctly clear result files after test ", var2);
        }
    }

    @Test
    public void getStatisticAboutApple() {
        this.workWithFile.getStatistic("apple.csv", "appleResult.csv");
        String actualResult = this.readFromFile("appleResult.csv").trim();
        String var10000 = System.lineSeparator();
        String expectedResult = "supply,188" + var10000 + "buy,115" + System.lineSeparator() + "result,73";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutOrange() {
        this.workWithFile.getStatistic("orange.csv", "orangeResult.csv");
        String actualResult = this.readFromFile("orangeResult.csv").trim();
        String var10000 = System.lineSeparator();
        String expectedResult = "supply,295" + var10000 + "buy,154" + System.lineSeparator() + "result,141";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutGrape() {
        this.workWithFile.getStatistic("grape.csv", "grapeResult.csv");
        String actualResult = this.readFromFile("grapeResult.csv").trim();
        String var10000 = System.lineSeparator();
        String expectedResult = "supply,352" + var10000 + "buy,352" + System.lineSeparator() + "result,0";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutBanana() {
        this.workWithFile.getStatistic("banana.csv", "bananaResult.csv");
        String actualResult = this.readFromFile("bananaResult.csv").trim();
        String var10000 = System.lineSeparator();
        String expectedResult = "supply,491" + var10000 + "buy,293" + System.lineSeparator() + "result,198";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getStatisticAboutBananaRepeatedMethodCall() {
        this.workWithFile.getStatistic("banana.csv", "bananaResult.csv");
        this.workWithFile.getStatistic("banana.csv", "bananaResult.csv");
        String actualResult = this.readFromFile("bananaResult.csv").trim();
        String var10000 = System.lineSeparator();
        String expectedResult = "supply,491" + var10000 + "buy,293" + System.lineSeparator() + "result,198";
        Assert.assertEquals("Calling the getStatistic() method repeatedly returned incorrect results.", expectedResult, actualResult);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException var3) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, var3);
        }
    }
}
