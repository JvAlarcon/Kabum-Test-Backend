package joao.alarcon.tests.kabum.analytics.model;

import java.io.Serializable;

public class SalesPerYear implements Serializable
{
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -7827647118501914626L;
	private String year;
	private String total;
	private String ecommerce;
	
	
	public SalesPerYear(){}
	
	public SalesPerYear(String year, String total, 
			String ecommerce)
	{
		if(stringContainR(year))
		{
			this.year = year;
		}
		else
		{
			this.year = Integer.valueOf(Double.valueOf(year).intValue()) + "";
		}
		
		if(stringContainSOrNA(total))
		{
			this.total = total;
		}
		else
		{
			this.total = Integer.valueOf(Double.valueOf(total).intValue()) + "";
		}
		
		if(stringContainSOrNA(ecommerce))
		{
			this.ecommerce = ecommerce;
		}
		else
		{
			this.ecommerce = Integer.valueOf(Double.valueOf(ecommerce).intValue()) + "";
		}
	}

	public String getYear() 
	{
		return year;
	}
	public void setYear(String year) 
	{
		this.year = year;
	}

	public String getTotal() 
	{
		return total;
	}
	public void setTotal(String total) 
	{
		this.total = total;
	}

	public String getEcommerce() 
	{
		return ecommerce;
	}
	public void setEcommerce(String ecommerce) 
	{
		this.ecommerce = ecommerce;
	}
	
	private Boolean stringContainR(String value)
	{
		value = value.toLowerCase();
		
		if(!value.contains("r"))
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	private Boolean stringContainSOrNA(String value)
	{
		value = value.toLowerCase();
		
		if(!value.contains("s") &&
			!value.contains("na"))
		{
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		return "SalesPerYear [year=" + year + ", total=" + total + ", ecommerce=" + ecommerce + "]";
	}
	
	
}
