package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class XLUtils {
    public static Workbook wb;

    public static int getRowCount(String xlFile, String xlSheet) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlFile)) {
            wb = WorkbookFactory.create(fi);
            Sheet ws = wb.getSheet(xlSheet);
            return ws.getLastRowNum();
        }
    }

    public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlFile)) {
            wb = WorkbookFactory.create(fi);
            Sheet ws = wb.getSheet(xlSheet);
            Row row = ws.getRow(rowNum);
            return row.getLastCellNum();
        }
    }

    public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlFile)) {
            wb = WorkbookFactory.create(fi);
            Sheet ws = wb.getSheet(xlSheet);
            Row row = ws.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            if (cell == null)
                return ""; // Or handle as needed
            return cell.toString();
        }
    }

    public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlFile); FileOutputStream fo = new FileOutputStream(xlFile)) {
            wb = WorkbookFactory.create(fi);
            Sheet ws = wb.getSheet(xlSheet);
            Row row = ws.getRow(rowNum);
            if (row == null)
                row = ws.createRow(rowNum);
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);
            wb.write(fo);
        }
    }
}
