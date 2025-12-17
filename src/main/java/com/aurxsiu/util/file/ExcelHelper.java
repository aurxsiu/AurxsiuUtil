package com.aurxsiu.util.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelHelper {
    public static void main(String[] args) throws IOException {
        changeXlsToXlsxExample();
    }
    private static void readExample() throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get("D:/data.xlsx")); ReadableWorkbook wb = new ReadableWorkbook(is)) {
            Sheet sheet = wb.getFirstSheet();
            List<Row> rows = sheet.openStream().collect(Collectors.toList());
            System.out.println("rows.size() = " + rows.size());
            rows = rows.subList(1, rows.size() - 1);
            for (Row r : rows) {
                int id = r.getCellAsNumber(0).orElse(new BigDecimal(0)).intValue();
                double k = r.getCellAsNumber(3).orElse(new BigDecimal(0)).doubleValue();
                System.out.println("k = " + k);
                System.out.println("id = " + id);
            }
        }
    }

    private static void changeXlsToXlsxExample() throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get("D:/a.xls"));
             Workbook oldWb = new HSSFWorkbook(is);
             Workbook newWb = new XSSFWorkbook()) {

            for (int i = 0; i < oldWb.getNumberOfSheets(); i++) {
                org.apache.poi.ss.usermodel.Sheet oldSheet = oldWb.getSheetAt(i);
                org.apache.poi.ss.usermodel.Sheet newSheet = newWb.createSheet(oldSheet.getSheetName());

                for (org.apache.poi.ss.usermodel.Row oldRow : oldSheet) {
                    org.apache.poi.ss.usermodel.Row newRow = newSheet.createRow(oldRow.getRowNum());
                    for (org.apache.poi.ss.usermodel.Cell oldCell : oldRow) {
                        Cell newCell = newRow.createCell(oldCell.getColumnIndex());
                        newCell.setCellValue(oldCell.toString());
                    }
                }
            }

            try (OutputStream os = Files.newOutputStream(Paths.get("D:/a.xlsx"))) {
                newWb.write(os);
            }
        }

    }
}
