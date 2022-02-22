package joao.alarcon.tests.kabum.analytics.model;

import java.io.Serializable;
import java.util.List;

public class MerchandiseLine implements Serializable
{
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = -7299532193474213294L;
	private String name;
	private List<SalesPerYear> salesPerYear;
	
	
	public MerchandiseLine() {}
	
	public MerchandiseLine(String name, List<SalesPerYear> salesPerYear)
	{
		this.salesPerYear = salesPerYear;
		this.name = name;
	}
	
	
	public List<SalesPerYear> getMerchandiseLines() 
	{
		return salesPerYear;
	}
	public void setMerchandiseLines(List<SalesPerYear> salesPerYear) 
	{
		this.salesPerYear = salesPerYear;
	}

	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	@Override
	public String toString() {
		return "MerchandiseLine [name=" + name + ", salesPerYear=" + salesPerYear + "]";
	}
	
	
}
