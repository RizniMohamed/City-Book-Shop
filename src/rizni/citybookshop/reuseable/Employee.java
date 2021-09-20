package rizni.citybookshop.reuseable;

public class Employee {

	private int 	EID			= 0;
	private String 	EName		= "";
	private String 	EMail		= "";
	private String 	Photo		= "";
 
	private int 	LID			= 0;
	private String 	LUsername	= "";
	private String 	LPassword	= "";
	private boolean LRemeber	= false;

	private int 	RID			= 0;
	private String 	Rname		= "";

	public int 		getEID()						{return EID;}
	public void 	setEID(int eID) 				{EID = eID;}
	public String 	getEName() 						{return EName;}
	public void 	setEName(String eName)			{EName = eName;}
	public String 	getEMail() 						{return EMail;}
	public void 	setEMail(String eMail)			{EMail = eMail;}
	public String 	getPhoto()						{return Photo;}
	public void 	setPhoto(String photo) 			{Photo = photo;}
	public int 		getLID()						{return LID;}
	public String 	getLUsername()					{return LUsername;}
	public void 	setLUsername(String lUsername) 	{LUsername = lUsername;}
	public String 	getLPassword()					{return LPassword;}
	public void 	setLPassword(String lPassword) 	{LPassword = lPassword;}
	public boolean 	isLRemeber() 					{return LRemeber;}
	public void 	setLRemeber(boolean lRemeber) 	{LRemeber = lRemeber;}
	public int 		getRID() 						{return RID;}
	public String 	getRname() 						{return Rname;}
	public void 	setRname(String rname) 			{Rname = rname;}

	@Override
	public String toString() {
		return "Employee [EID=" + EID + ", EName=" + EName + ", EMail=" + EMail + ", Photo=" + Photo + ", LID=" + LID
				+ ", LUsername=" + LUsername + ", LPassword=" + LPassword + ", LRemeber=" + LRemeber + ", RID=" + RID
				+ ", Rname=" + Rname + "]";
	}
}
