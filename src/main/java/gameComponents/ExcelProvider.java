package gameComponents;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExcelProvider {

    private static final ArrayList<Result> results;

    static {
        try {
            results = readFromExcel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Result> getResults() {
        return results;
    }

    /**
     * Метод, записывающий в excel файл отсортированные по убыванию результаты
     *
     * @param results - отсортированная коллекция из объектов result, включающих в себя имя и очки игрока
     * @throws IOException вызывается при невозможности найти excel файл
     */

    public static void writeToExcel(ArrayList<Result> results) throws IOException {
        var path = Paths.get(System.getProperty("user.dir"), "Results.xlsx");
        FileInputStream in = new FileInputStream(path.toFile());

        try (XSSFWorkbook book = new XSSFWorkbook(in)) {
            XSSFSheet sheet = book.getSheetAt(0);

            for (int i = 0; i < Math.min(10, results.size()); i++) {
                Result result = results.get(i);
                XSSFRow row = sheet.getRow(i + 1);
                if (row == null) {
                    row = sheet.createRow(i + 1);
                }
                if (row.getCell(2) == null || row.getCell(2).getNumericCellValue() < result.getPoints()) {
                    row.createCell(0, CellType.NUMERIC).setCellValue(i + 1);
                    row.createCell(1, CellType.STRING).setCellValue(result.getName());
                    row.createCell(2, CellType.NUMERIC).setCellValue(result.getPoints());
                }
            }
            book.write(new FileOutputStream(path.toFile()));
            in.close();
        }
    }

    /**
     * Метод, считывающий данные из excel файла и записывающий эти данные в коллекцию объектов result
     *
     * @return коллекция объектов result
     * @throws IOException при невозможности найти excel файл
     */
    public static ArrayList<Result> readFromExcel() throws IOException {
        var path = Paths.get(System.getProperty("user.dir"), "Results.xlsx");
        if (!path.toFile().exists()) {
            path.toFile().createNewFile();
            FileOutputStream file = new FileOutputStream(path.toFile());
            var book = new XSSFWorkbook();
            XSSFSheet sheet = book.createSheet();

            book.write(file);
            book.close();
            file.close();
        }
        FileInputStream in = new FileInputStream(path.toFile());
        try (XSSFWorkbook book = new XSSFWorkbook(in)) {
            XSSFSheet sheet = book.getSheetAt(0);
            ArrayList<Result> results = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                results.add(new Result(sheet.getRow(i).getCell(1).getStringCellValue(),
                        (int) sheet.getRow(i).getCell(2).getNumericCellValue()));
            }
            return results;
        } catch (NullPointerException e) {
            System.out.println("Empty file of records");
        }
        return null;
    }
}