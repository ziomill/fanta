package it.millsoft.fantarepubic.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class StatsHandler
{

    public static void main(String[] args)
    {
        try
        {
            /**** Initialize ****/
            System.out.println("###### Loading Configuration ...");
            String fileToReadPath = loadConfig().getProperty("file.to.read.path");
            String fileToWritePath = loadConfig().getProperty("file.to.write.path");
            String difensoriSheetToReadName = loadConfig().getProperty("difensori.sheet.name.to.read");
            String difensoriSheetToWriteName = loadConfig().getProperty("difensori.sheet.name.to.write");
            String centrocampistiSheetToReadName = loadConfig().getProperty("centrocampisti.sheet.name.to.read");
            String centrocampistiSheetToWriteName = loadConfig().getProperty("centrocampisti.sheet.name.to.write");
            String attaccantiSheetToReadName = loadConfig().getProperty("attaccanti.sheet.name.to.read");
            String attaccantiSheetToWriteName = loadConfig().getProperty("attaccanti.sheet.name.to.write");
            System.out.println("###### Ok,Configuration loaded!");


            /**** Read Stats ****/
            FileInputStream excelToRead = new FileInputStream(new File(fileToReadPath));
            Workbook workbookToRead = new XSSFWorkbook(excelToRead);

            // Difensori
            System.out.println("###### Reading 'DIFENSORI' stats ...");
            Sheet difensoriSheetToRead = workbookToRead.getSheet(difensoriSheetToReadName);
            Set<PlayerStats> difensoriStats = StatsReader.readStats(difensoriSheetToRead);
            System.out.println("###### Ok," + difensoriStats.size() + "'DIFENSORI' stats readed!");

            // Centrocampisti
            System.out.println("###### Reading 'CENTROCAMPISTI' stats ...");
            Sheet centrocampistiSheetToRead = workbookToRead.getSheet(centrocampistiSheetToReadName);
            Set<PlayerStats> centrocampistiStats = StatsReader.readStats(centrocampistiSheetToRead);
            System.out.println("###### Ok," + centrocampistiStats.size() + "'CENTROCAMPISTI' stats readed!");

            // Centrocampisti
            System.out.println("###### Reading 'ATTACCANTI' stats ...");
            Sheet attaccantiSheetToRead = workbookToRead.getSheet(attaccantiSheetToReadName);
            Set<PlayerStats> attaccantiStats = StatsReader.readStats(attaccantiSheetToRead);
            System.out.println("###### Ok," + attaccantiStats.size() + "'ATTACCANTI' stats readed!");



            /**** Write Stats ****/
            FileInputStream excelToWrite = new FileInputStream(new File(fileToWritePath));
            Workbook workbookToWrite = new XSSFWorkbook(excelToWrite);

            // Difensori
            System.out.println("###### Writing 'DIFENSORI' stats ...");
            Sheet difensoriSheetToWrite = workbookToWrite.getSheet(difensoriSheetToWriteName);
            int difensoriUpdatedStats = StatsWriter.writeStats(difensoriSheetToWrite,new ArrayList(difensoriStats));
            System.out.println("###### Ok," + difensoriUpdatedStats + "'DIFENSORI' stats wrote!");

            // Centrocampisti
            System.out.println("###### Writing 'CENTROCAMPISTI' stats ...");
            Sheet centrocampistiSheetToWrite = workbookToWrite.getSheet(centrocampistiSheetToWriteName);
            int centrocampistiUpdatedStats = StatsWriter.writeStats(centrocampistiSheetToWrite,new ArrayList(centrocampistiStats));
            System.out.println("###### Ok," + centrocampistiUpdatedStats + "'CENTROCAMPISTI' stats wrote!");

            // Attaccanti
            System.out.println("###### Writing 'ATTACCANTI' stats ...");
            Sheet attaccantiSheetToWrite = workbookToWrite.getSheet(attaccantiSheetToWriteName);
            int attaccantiUpdatedStats = StatsWriter.writeStats(attaccantiSheetToWrite,new ArrayList(attaccantiStats));
            System.out.println("###### Ok," + attaccantiUpdatedStats + "'ATTACCANTI' stats wrote!");



            /**** Finalize ****/
            System.out.println("###### Finalizing ops ...");
            FileOutputStream outputStream = new FileOutputStream(fileToWritePath);
            workbookToWrite.write(outputStream);
            workbookToWrite.close();
            System.out.println("###### Ok,all done!");
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

    private static Properties loadConfig() throws IOException
    {
        Properties result = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("config.properties");
        result.load(in);
        in.close();
        return result;
    }

}

