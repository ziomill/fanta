package it.millsoft.fantarepubic.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StatsReader
{
    private static final int START_ROW_INDEX = 2;

    private static final int GIOCATORE_COLUMN_INDEX = 2;
    private static final int SQUADRA_COLUMN_INDEX = 3;
    private static final int PRESENZE_COLUMN_INDEX = 4;
    private static final int FANTAMEDIA_COLUMN_INDEX = 6;
    private static final int GOAL_COLUMN_INDEX = 7;
    private static final int ASSISTS_COLUMN_INDEX = 13;
    private static final int AMMONIZIONE_COLUMN_INDEX = 15;
    private static final int ESPULSIONI_COLUMN_INDEX = 16;

    public static Set<PlayerStats> readStats(Sheet sheet)
    {
        Set<PlayerStats> result = new HashSet<PlayerStats>();

        int currentRowIndex = 0;
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext())
        {
            Row currentRow = iterator.next();

            if(currentRowIndex >= START_ROW_INDEX)
            {
                Iterator<Cell> cellIterator = currentRow.iterator();

                String giocatore = currentRow.getCell(GIOCATORE_COLUMN_INDEX).getStringCellValue().trim().toUpperCase();
                String squadra = currentRow.getCell(SQUADRA_COLUMN_INDEX).getStringCellValue().trim().toUpperCase();
                int presenze = ((Double)currentRow.getCell(PRESENZE_COLUMN_INDEX).getNumericCellValue()).intValue();
                int goals = ((Double)currentRow.getCell(GOAL_COLUMN_INDEX).getNumericCellValue()).intValue();
                int assists = ((Double)currentRow.getCell(ASSISTS_COLUMN_INDEX).getNumericCellValue()).intValue();
                int ammonizioni = ((Double)currentRow.getCell(AMMONIZIONE_COLUMN_INDEX).getNumericCellValue()).intValue();
                int espulsioni = ((Double)currentRow.getCell(ESPULSIONI_COLUMN_INDEX).getNumericCellValue()).intValue();
                double fantamedia = currentRow.getCell(FANTAMEDIA_COLUMN_INDEX).getNumericCellValue();

                PlayerStats playetStats = new PlayerStats(giocatore,
                                                          squadra,
                                                          presenze,
                                                          goals,
                                                          assists,
                                                          ammonizioni,
                                                          espulsioni,
                                                          fantamedia);
                result.add(playetStats);

                System.out.println(playetStats);
            }
            currentRowIndex++;
        }
        return result;
    }

}
