package joao.alarcon.tests.kabum.analytics.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import joao.alarcon.tests.kabum.analytics.model.MerchandiseLine;

@Component
public class VisualAnalyticsBO 
{
	private static final String fileLocation = "src/main/resources/dataAnalytics/ecommerceSales.xls";
	
	//private static final String CLASS_NAME_LOG = "[VisualAnalyticsBO] ";
	
	private static final List<Integer> disregardLines = List.of(0, 1, 2, 4);
	private static List<String> merchandiseLineNames = null;
	private static List<String> years = null;
	private static List<String> columnsTotal = null;
	private static List<String> columnsEcommerce = null;
	
	public List<MerchandiseLine> readXLSData()
	{
		List<MerchandiseLine> merchandiseLine = null;
		
		merchandiseLineNames = new ArrayList<String>();
		years = new ArrayList<String>();           
		columnsTotal = new ArrayList<String>();    
		columnsEcommerce = new ArrayList<String>();
		
		try 
		{
			FileInputStream fileInputStream = new FileInputStream(new File(fileLocation));
			
			try(HSSFWorkbook workBook = new HSSFWorkbook(fileInputStream))
			{
				HSSFSheet sheet = workBook.getSheetAt(0);
				Integer iteratorLine = Integer.valueOf(0);
				Integer iteratorColumn = Integer.valueOf(0);
				
				for(Row row : sheet)
				{
					iteratorLine = Integer.valueOf(row.getRowNum());
					
					
					if(!disregardLines.contains(iteratorLine))
					{
						for(Cell cell : row)
						{
							iteratorColumn = Integer.valueOf(cell.getColumnIndex());
							
							switch(cell.getCellType())
							{
								case STRING:
									String textCellValue = cell.getRichStringCellValue().getString();
									textCellValue = cleanValue(textCellValue);
									
									fillListOfMerchandiseLineNamesAfterValidation(iteratorLine, iteratorColumn, textCellValue);
									
									fillListOfYearAfterValidation(iteratorLine, iteratorColumn, textCellValue);
									
									fillListOfColumnsAfterValidations(iteratorLine, iteratorColumn, textCellValue);
									
									break;
								case NUMERIC:
									String numericCellValue = cell.getNumericCellValue() + "";
									numericCellValue = cleanValue(numericCellValue);
									
									fillListOfYearAfterValidation(iteratorLine, iteratorColumn, numericCellValue);
									
									fillListOfColumnsAfterValidations(iteratorLine, iteratorColumn, numericCellValue);
									
									break;
								default:
									break;
							}
							
							
						}
						
						if(isEndOfLineContent(iteratorLine))
						{
							break;
						}
					}
				}
				
				years.remove(years.size() - 1);
				
				//Populate Sales Class and MerchandiseLine Class
				merchandiseLine = VisualAnalyticsUtils.createListOfMerchandiseLine(merchandiseLineNames, years, columnsTotal, columnsEcommerce);
				
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return merchandiseLine;
	}
	
	private void fillListOfMerchandiseLineNamesAfterValidation(Integer iteratorLine, Integer iteratorColumn, String cellValue)
	{
		if(VisualAnalyticsUtils.columnIsMerchandiseLineName(iteratorLine, iteratorColumn))
		{
			VisualAnalyticsUtils.addValueToListOfString(merchandiseLineNames, cellValue);
		}
	}
	
	private void fillListOfYearAfterValidation(Integer iteratorLine, Integer iteratorColumn, String cellValue)
	{
		if(VisualAnalyticsUtils.lineAndColumnIsYear(iteratorLine, iteratorColumn))
		{
			//Line and Column is Year
			if(!years.contains(cellValue))
			{
				VisualAnalyticsUtils.addValueToListOfString(years, cellValue);
			}
		}
	}
	
	private void fillListOfColumnsAfterValidations(Integer iteratorLine, Integer iteratorColumn, String cellValue)
	{
		if(iteratorLine < Integer.valueOf(5) ||
				iteratorColumn == Integer.valueOf(0))
		{
			return;
		}
		
		if(!VisualAnalyticsUtils.containsPercentageCharacter(cellValue))
		{
			
			if(VisualAnalyticsUtils.columnIsTotal(iteratorColumn))
			{
				//Is Total Column
				VisualAnalyticsUtils.addValueToListOfString(columnsTotal, cellValue);
			}
			else
			{
				//Is E-commerce Column
				VisualAnalyticsUtils.addValueToListOfString(columnsEcommerce, cellValue);
			}
		}
	}
	
	private Boolean isEndOfLineContent(Integer iteratorLine)
	{
		if(!(iteratorLine >= 19))
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	private String cleanValue(String cellValue)
	{
		return VisualAnalyticsUtils.cleanValue(cellValue);
	}
}
