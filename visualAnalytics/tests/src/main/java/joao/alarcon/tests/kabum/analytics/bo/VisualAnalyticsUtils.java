package joao.alarcon.tests.kabum.analytics.bo;

import java.util.ArrayList;
import java.util.List;

import joao.alarcon.tests.kabum.analytics.model.MerchandiseLine;
import joao.alarcon.tests.kabum.analytics.model.SalesPerYear;

public class VisualAnalyticsUtils 
{
	public static Boolean columnIsMerchandiseLineName(Integer iteratorLine, Integer iteratorColumn)
	{
		if(iteratorLine < Integer.valueOf(5) ||
				iteratorColumn != Integer.valueOf(0))
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	public static Boolean lineAndColumnIsYear(Integer iteratorLine, Integer iteratorColumn)
	{
		if(iteratorLine != Integer.valueOf(3) ||
				iteratorColumn == Integer.valueOf(0))
		{
			return Boolean.FALSE;
		}
		
		//Line is Year
		return Boolean.TRUE;
	}
	
	public static Boolean containsPercentageCharacter(String columnValue)
	{
		if(!columnValue.contains("%"))
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	public static Boolean columnIsTotal(Integer iteratorColumn)
	{
		if(iteratorColumn == 1)
		{
			//Is Total Column
			return Boolean.TRUE;
		}
		else if(iteratorColumn == 2)
		{
			//Is E-commerce Column
			return Boolean.FALSE;
		}
		
		
		if(iteratorColumn % Integer.valueOf(2) != Integer.valueOf(0))
		{
			//Is E-commerce Column
			return Boolean.FALSE;
		}
		
		//Is Total Column
		return Boolean.TRUE;
	}
	
	public static void addValueToListOfString(List<String> listOfString, String valueToAdd)
	{
		listOfString.add(valueToAdd);
	}
	
	public static List<MerchandiseLine> createListOfMerchandiseLine(List<String> merchandiseLineNames,
			List<String> years, List<String> columnsTotal, List<String> columnsEcommerce)
	{
		List<MerchandiseLine> merchandiseLines = new ArrayList<MerchandiseLine>();
		int iteratorForColumns = 0;
		
		for(String merchandiseLineName : merchandiseLineNames)
		{
			List<SalesPerYear> salesPerYears = new ArrayList<SalesPerYear>();
			for(int iteratorForYear = 0; iteratorForYear < years.size(); iteratorForYear++)
			{
				String year = years.get(iteratorForYear);
				String total = columnsTotal.get(iteratorForColumns);
				String ecommerce = columnsEcommerce.get(iteratorForColumns);
				iteratorForColumns++;
				
				SalesPerYear salesPerYear = new SalesPerYear(year, total, ecommerce);
				salesPerYears.add(salesPerYear);
			}
			
			merchandiseLines.add(new MerchandiseLine(merchandiseLineName, salesPerYears));
		}
		
		return merchandiseLines;
	}
	
	public static String cleanValue(String valueToClean)
	{
		String valueCleaned = valueToClean.replaceAll("[^A-Za-z0-9-(-)-, ]", " ").trim();
		valueCleaned = removeLastCharacter(valueCleaned);
		
		return valueCleaned;
	}
	
	private static String removeLastCharacter(String valueToClean)
	{
		int lastIndex = valueToClean.length() - 1;
		String lastChar = String.valueOf(valueToClean.charAt(lastIndex));
		
		if(lastChar.contains("0"))
		{
			valueToClean = valueToClean.substring(0, lastIndex);
		}
		
		return valueToClean;
	}
}
