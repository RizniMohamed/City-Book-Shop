package rizni.citybookshop.reuseable;

public class Books {

	private int 	BID 		= 0;
	private String 	BName 		= "";
	private double 	BUnitPrice 	= 0.0;
	private int 	BStock 		= 0; 	
	
	private int		CID			= 0;
	private String 	CName 		= "";
	
	public int 		getBID() 							{return BID;}
	public void 	setBID(int bID) 					{BID = bID;}
	public String 	getBName() 							{return BName;}
	public void 	setBName(String bName) 				{BName = bName;}
	public double 	getBUnitPrice() 					{return BUnitPrice;}
	public void 	setBUnitPrice(double bUnitPrice)	{BUnitPrice = bUnitPrice;}
	public int 		getBStock() 						{return BStock;}
	public void 	setBStock(int bStock) 				{BStock = bStock;}
	public int	 	getCID() 							{return CID;}
	public void 	setCID(int cID) 					{CID = cID;}
	public String 	getCName() 							{return CName;}
	public void 	setCName(String cName) 				{CName = cName;}
	
	@Override
	public String toString() {
		return "Books [BID=" + BID + ", BName=" + BName + ", BUnitPrice=" + BUnitPrice + ", BStock=" + BStock + ", CID="
				+ CID + ", CName=" + CName + "]";
	}	
}
