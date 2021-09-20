package rizni.citybookshop.reuseable;

import java.sql.Date;

public class Invoice {
	
	private int     IID;
	private int     IQuantity;
	private Date    IDate;
	private int     BID;
	private int     EID;
	private Double  Total;
	private String  BName;
	private String  CName;
	private String  Ename;

	public String   getEname()                  {return Ename;}
	public void     setEname(String ename)      {Ename = ename;}
	public Double   getTotal()                  {return Total;}
	public void     setTotal(Double total)      {Total = total;}
	public int      getIID()                    {return IID;}
	public void     setIID(int iID)             {IID = iID;}
	public int      getIQuantity()              {return IQuantity;}
	public void     setIQuantity(int iQuantity) {IQuantity = iQuantity;}
	public Date     getIDate()                  {return IDate;}
	public void     setIDate(Date date)         {IDate = date;}
	public int      getBID()                    {return BID;}
	public void     setBID(int bID)             {BID = bID;}
	public int      getEID()                    {return EID;}
	public void     setEID(int eID)             {EID = eID;}
	public String   getBName()                  {return BName;}
	public void     setBName(String bName)      {BName = bName;}
	public String   getCName()                  {return CName;}
	public void     setCName(String cName)      {CName = cName;}
	
	@Override
	public String toString() {
		return "Invoice [IID=" + IID + ", IQuantity=" + IQuantity + ", IDate=" + IDate + ", BID=" + BID + ", EID=" + EID
				+ ", Total=" + Total + ", BName=" + BName + ", CName=" + CName + ", Ename=" + Ename + "]";
	}
}