package it.millsoft.fantarepubic.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.util.Iterator;
import java.util.List;

public class StatsWriter
{

    private static final int START_ROW_INDEX = 2;

    private static final int GIOCATORE_COLUMN_INDEX = 1;
    private static final int PRESENZE_COLUMN_INDEX = 4;
    private static final int GOALS_COLUMN_INDEX = 5;
    private static final int ASSISTS_COLUMN_INDEX = 6;
    private static final int AMMONIZIONI_COLUMN_INDEX = 7;
    private static final int ESPULSIONI_COLUMN_INDEX = 8;
    private static final int FANTAMEDIA_COLUMN_INDEX = 9;

    public static int writeStats(Sheet sheet,List<PlayerStats> stats)
    {
        int updated = 0;

        int currentRowIndex = 0;
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext())
        {
            Row currentRow = iterator.next();

            if (currentRowIndex >= START_ROW_INDEX)
            {
                String giocatore = currentRow.getCell(GIOCATORE_COLUMN_INDEX).getStringCellValue().trim().toUpperCase();
                PlayerStats playerStats = new PlayerStats(giocatore);
                int playerStatsIndex = stats.indexOf(playerStats);
                if(playerStatsIndex == -1)
                {
                    final CellStyle style = sheet.getWorkbook().createCellStyle();
                    style.setBorderBottom(BorderStyle.DOUBLE);
                    style.setBorderTop(BorderStyle.DOUBLE);
                    style.setBorderLeft(BorderStyle.DOUBLE);
                    style.setBorderRight(BorderStyle.DOUBLE);
                    currentRow.getCell(GIOCATORE_COLUMN_INDEX).setCellStyle(style);
                }
                else
                {
                    playerStats = stats.get(playerStatsIndex);

                    Cell presenzeCell = currentRow.createCell(PRESENZE_COLUMN_INDEX);
                    presenzeCell.setCellValue(playerStats.getPresenze());

                    Cell goalsCell = currentRow.createCell(GOALS_COLUMN_INDEX);
                    goalsCell.setCellValue(playerStats.getGoal());

                    Cell assistsCell = currentRow.createCell(ASSISTS_COLUMN_INDEX);
                    assistsCell.setCellValue(playerStats.getAssists());

                    Cell ammonizioniCell = currentRow.createCell(AMMONIZIONI_COLUMN_INDEX);
                    ammonizioniCell.setCellValue(playerStats.getAmmonizioni());

                    Cell espulsioniCell = currentRow.createCell(ESPULSIONI_COLUMN_INDEX);
                    espulsioniCell.setCellValue(playerStats.getEspulsioni());

                    Cell fantamediaCell = currentRow.createCell(FANTAMEDIA_COLUMN_INDEX);
                    fantamediaCell.setCellValue(playerStats.getFantaMedia());
                }
            }
            currentRowIndex++;
        }

        return updated;
    }
}
