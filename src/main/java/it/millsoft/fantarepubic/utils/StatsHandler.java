package it.millsoft.fantarepubic.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class StatsHandler
{

    private static final String FILE_TO_READ = "C:\\Users\\a.benvenuto\\Desktop\\PERSONALI\\Statistiche.xlsx";
    private static final String FILE_TO_WRITE = "C:\\Users\\a.benvenuto\\Desktop\\PERSONALI\\AstaTS.xlsx";
    private static final String SHEET_NAME_TO_READ = "Difensori";
    private static final String SHEET_NAME_TO_WRITE = "Difensori";

    public static void main(String[] args)
    {
        try
        {
            // Read Stats
            FileInputStream excelToRead = new FileInputStream(new File(FILE_TO_READ));
            Workbook workbookToRead = new XSSFWorkbook(excelToRead);
            Sheet sheetToRead = workbookToRead.getSheet(SHEET_NAME_TO_READ);
            Set<PlayerStats> playerStats = StatsReader.readStats(sheetToRead);

            // Write Stats
            FileInputStream excelToWrite = new FileInputStream(new File(FILE_TO_WRITE));
            Workbook workbookToWrite = new XSSFWorkbook(excelToWrite);
            Sheet sheetToWrite = workbookToWrite.getSheet(SHEET_NAME_TO_WRITE);
            int updatedStats = StatsWriter.writeStats(sheetToWrite,new ArrayList(playerStats));
            FileOutputStream outputStream = new FileOutputStream(FILE_TO_WRITE);
            workbookToWrite.write(outputStream);
            workbookToWrite.close();


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

