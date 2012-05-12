package com.demo.card;
public class ValidateCreditCardUtil {
	private static int tw = 181;
	private static String[] c = new String[tw];
	private static String[] cd = new String[tw];

	
	public static void main(String[] args) {
		ValidateCreditCardUtil v = new ValidateCreditCardUtil();
		System.out.println(v.creditCartCheck("4712125071951049"));
	}
	
	
	public static String creditCartCheck(String cardNumber) {
		if (cardNumber != "" && cardNumber != null) {
			String cf = cardNumber;
			String cn = chkCard(cf);
//			var clcd = chkLCD(cf);
			// var clcdt="NOT PASSED"; if (clcd) {clcdt="PASSED";}
//			boolean ccck=chkCCCksum(cf,cn);
			// var ccckt="NOT PASSED"; if (ccck) {ccckt="PASSED";}
			// var cjd="INVALID CARD NUMBER"; 
			
			StringBuffer out = new StringBuffer("");
//			if (ccck) {out.append("This card number appears to be valid.");}
			out.append(cn);
			return out.toString();
		}
		return null;
	}

	// ��鿨����
	private static String chkCard(String cdi) {
		cdi += "";
		// if (c[1]==undefined || c[1]==null || c[1]=="") {mkCClist();}
		int ccn = 0;
		String cn = "unknown";
		String cf = cdi;
		if (leftS(cf, 1) .equals("4")) {
			cf = leftS(cf, 8);
		}
		for (int i = 0; i < tw; i++) {
			String cct = c[i];
			if (leftS(cf, 1) .equals("4")) {
				cct = leftS(cct, 8);
			}
			boolean ccc = cmpPattern(cf, cct, null);
			if (ccc) {
				ccn = i;
				break;
			}
		}
		if (ccn > 0) {
			cn = cd[ccn];
		}
		return cn;
	}

	private static String leftS(String aS,int n) {
		String rS="";
		if (n>=1) {
			rS=aS.substring(0,n);
		}
		return rS;
	}
	
	
	private static boolean cmpPattern(String a, String p, String x) {
		if (x == null||x.equals("")) {
			x = "x";
		}
		x = "" + x.substring(0, 1);
		a += "";
		p += "";
		boolean r = false;
		int mc = 0;
		if (a.length() == p.length()) {
			for (int i = 1; i <= a.length(); i++) {
				String a1 = midS(a, i, 1);
				String p1 = midS(p, i, 1);
				if (a1 .equals( p1 )|| p1.equals( x)) {
					mc++;
				}
			}
		}
		if (mc == a.length()) {
			r = true;
		}
		return r;
	}

	private static String midS(String aS, int n, int n2) {
		aS += "";
		String rS = "";
		// if (n2==null || n2=="") {
		// n2=aS.length();
		// }
		n *= 1;
		n2 *= 1;
		if (n < 0) {
			n++;
		}
		rS = aS.substring(n - 1, n - 1 + n2);
		return rS;
	}

	
	private static boolean chkCCCksum(String cf,String cn) {
		boolean r=false;
		String w="21";
		String ml="";
		int j=1;
		for (int i=0;i<cf.length();i++) {
			int m=Integer.parseInt(midS(cf,i,1))*Integer.parseInt(midS(w,j,1));
			m=Integer.parseInt(sumDigits(m+"",0));
			ml+=""+m;
			j++; if (j>w.length()) {j=1;}
		}
		String ml2=sumDigits(ml,-1); 
		String ml1=(((Integer.parseInt(sumDigits(ml2,-1))*10)-(Integer.parseInt(ml2))%10))+"";
		if (ml1.equals(rightS(cf,1))) {r=true;}
		return r;
	}
	
	
	private static String sumDigits(String n,int m) {
		if (m==0) {m=1;}
		n+="";
		
		if (m>0) {
			while (n.length()>m) {
				int r=0;
				for (int i=1;i<=n.length();i++) {
					r+=1.0*Integer.parseInt(midS(n,i,1));
				}
				n=""+r;
			}
		} else {
			for (int j=1;j<=Math.abs(m);j++) {
				int r=0;
				for (int i=1;i<=n.length();i++) {
					r+=1.0*Integer.parseInt(midS(n,i,1));
				}
				n=""+r;
			}
		}
		return n;
	}
	
	
	
	private static String rightS(String aS,int n) {
		String rS="";
		if (n>=1) {
			rS=aS.substring(aS.length()-n,aS.length());
		}
		return rS;
	}
	
	

	// ��̬����飬ִ�з���
	static {
		int i = 0;
		c[i] = "3782xxxxxxxxxxx";
		cd[i] = "AmEx - Small Corporate Card";
		i++;
		c[i] = "3787xxxxxxxxxxx";
		cd[i] = "AmEx - Small Corporate Card";
		i++;
		c[i] = "37x8xxxxxxxxxxx";
		cd[i] = "AmEx Gold";
		i++;
		c[i] = "37x37xxxxxxxxxx";
		cd[i] = "AmEx Platinum";
		i++;
		c[i] = "37xxxxxxxx11xxx";
		cd[i] = "AmEx issued since 1995";
		i++;
		c[i] = "37xxxxxxxxxxxxx";
		cd[i] = "AmEx";
		i++;
		c[i] = "3xxxxxxxxxxxxxxxxxxxxx";
		cd[i] = "AmEx Gold";
		i++;
		c[i] = "30xxxxxxxxxxxx";
		cd[i] = "Diners Club";
		i++;
		c[i] = "31xxxxxxxxxxxx";
		cd[i] = "Diners Club";
		i++;
		c[i] = "35xxxxxxxxxxxx";
		cd[i] = "Diners Club";
		i++;
		c[i] = "36xxxxxxxxxxxx";
		cd[i] = "Diners Club";
		i++;
		c[i] = "38xxxxxxxxxxxx";
		cd[i] = "Carte Blanche";
		i++;
		c[i] = "35xxxxxxxxxxxxxx";
		cd[i] = "JCB (Japanese Credit Bureau)";
		i++;
		c[i] = "40240238xxxxxxxx";
		cd[i] = "Visa Gold - Bank of America";
		i++;
		c[i] = "4019xxxxxxxxxxxx";
		cd[i] = "Visa CV/Gold - Bank of America";
		i++;
		c[i] = "4024xxxxxxxxxxxx";
		cd[i] = "Visa PV - Bank of America";
		i++;
		c[i] = "4040xxxxxxxxxxxx";
		cd[i] = "Visa CV - Wells Fargo";
		i++;
		c[i] = "4048xxxxxxxxxxxx";
		cd[i] = "Visa CV";
		i++;
		c[i] = "40240071xxxxxxxx";
		cd[i] = "Visa - Wells Fargo";
		i++;
		c[i] = "4013xxxxxxxxxxxx";
		cd[i] = "Visa - Citibank";
		i++;
		c[i] = "4019xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of America";
		i++;
		c[i] = "4024xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of America";
		i++;
		c[i] = "4027xxxxxxxxxxxx";
		cd[i] = "Visa - Rockwell Federal Credit Union";
		i++;
		c[i] = "4032xxxxxxxxxxxx";
		cd[i] = "Visa - Household Bank";
		i++;
		c[i] = "4052xxxxxxxxxxxx";
		cd[i] = "Visa - First Cincinnati";
		i++;
		c[i] = "4060xxxxxxxxxxxx";
		cd[i] = "Visa - Associates National Bank";
		i++;
		c[i] = "4070xxxxxxxxxxxx";
		cd[i] = "Visa - Security Pacific";
		i++;
		c[i] = "4071xxxxxxxxxxxx";
		cd[i] = "Visa - Colonial National Bank";
		i++;
		c[i] = "4094xxxxxxxxxxxx";
		cd[i] = "Visa - A.M.C. Federal Credit Union";
		i++;
		c[i] = "4113xxxxxxxxxxxx";
		cd[i] = "Visa - Valley National Bank";
		i++;
		c[i] = "4114xxxxxxxxxxxx";
		cd[i] = "Visa - Chemical Bank";
		i++;
		c[i] = "4121xxxxxxxxxxxx";
		cd[i] = "Visa - Pennsylvania State Employees Credit Union";
		i++; // c[i]="4121 xxxx xxxx xxxx"; cd[i]="Visa CV - Signet Bank";
				// i++;
		c[i] = "4122xxxxxxxxxxxx";
		cd[i] = "Visa - Union Trust";
		i++;
		c[i] = "4125xxxxxxxxxxxx";
		cd[i] = "Visa - Marine Midland";
		i++;
		c[i] = "4128xxxxxxxxx";
		cd[i] = "Visa CV - Citibank";
		i++;
		c[i] = "4128xxxxxxxxxxxx";
		cd[i] = "Visa CV - Citibank";
		i++;
		c[i] = "4131xxxxxxxxxxxx";
		cd[i] = "Visa - State Street Bank";
		i++;
		c[i] = "4225xxxxxxxxxxxx";
		cd[i] = "Visa - Chase Manhattan Bank";
		i++;
		c[i] = "4226xxxxxxxxxxxx";
		cd[i] = "Visa - Chase Manhattan Bank";
		i++;
		c[i] = "4231xxxxxxxxxxxx";
		cd[i] = "Visa - Chase Lincoln First Classic";
		i++;
		c[i] = "4232xxxxxxxxxxxx";
		cd[i] = "Visa - Chase Lincoln First Classic";
		i++;
		c[i] = "4239xxxxxxxxxxxx";
		cd[i] = "Visa - Corestates";
		i++;
		c[i] = "4241xxxxxxxxxxxx";
		cd[i] = "Visa - National Westminster Bank";
		i++;
		c[i] = "4250xxxxxxxxxxxx";
		cd[i] = "Visa - First Chicago Bank";
		i++;
		c[i] = "4253xxxxxxxxxxxx";
		cd[i] = "Visa - Consumers Edge";
		i++;
		c[i] = "425451236000xxxx";
		cd[i] = "Visa Premier card - Security First";
		i++;
		c[i] = "425451238500xxxx";
		cd[i] = "Visa Premier card - Security First";
		i++;
		c[i] = "4254xxxxxxxxxxxx";
		cd[i] = "Visa - Security First";
		i++;
		c[i] = "4271382xxxxxxxxx";
		cd[i] = "Visa PV - Citibank";
		i++;
		c[i] = "4271xxxxxxxxxxxx";
		cd[i] = "Visa - Citibank/Citicorp";
		i++;
		c[i] = "4301xxxxxxxxxxxx";
		cd[i] = "Visa - Monogram Bank";
		i++;
		c[i] = "4302xxxxxxxxxxxx";
		cd[i] = "Visa - H.H.B.C.";
		i++;
		c[i] = "4311xxxxxxxxxxxx";
		cd[i] = "Visa - First National Bank of Louisville";
		i++;
		c[i] = "4317xxxxxxxxxxxx";
		cd[i] = "Visa - Gold Dome";
		i++;
		c[i] = "4327xxxxxxxxxxxx";
		cd[i] = "Visa - First Atlanta";
		i++;
		c[i] = "4332xxxxxxxxxxxx";
		cd[i] = "Visa - First American Bank";
		i++;
		c[i] = "4339xxxxxxxxxxxx";
		cd[i] = "Visa - Primerica Bank";
		i++;
		c[i] = "4342xxxxxxxxxxxx";
		cd[i] = "Visa - N.C.M.B. / Nations Bank";
		i++;
		c[i] = "4356xxxxxxxxxxxx";
		cd[i] = "Visa - National Bank of Delaware";
		i++;
		c[i] = "4368xxxxxxxxxxxx";
		cd[i] = "Visa - National West";
		i++;
		c[i] = "4387xxxxxxxxxxxx";
		cd[i] = "Visa - Bank One";
		i++;
		c[i] = "4388xxxxxxxxxxxx";
		cd[i] = "Visa - First Signature Bank & Trust";
		i++;
		c[i] = "4401xxxxxxxxxxxx";
		cd[i] = "Visa - Gary-Wheaton Bank";
		i++;
		c[i] = "4413xxxxxxxxxxxx";
		cd[i] = "Visa - Firstier Bank Lincoln";
		i++;
		c[i] = "4418xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Omaha";
		i++;
		c[i] = "4421xxxxxxxxxxxx";
		cd[i] = "Visa - Indiana National Bank";
		i++;
		c[i] = "4424xxxxxxxxxxxx";
		cd[i] = "Visa - Security Pacific National Bank";
		i++;
		c[i] = "4428xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Hoven";
		i++;
		c[i] = "4436xxxxxxxxxxxx";
		cd[i] = "Visa - Security Bank & Trust";
		i++;
		c[i] = "4443xxxxxxxxxxxx";
		cd[i] = "Visa - Merril Lynch Bank & Trust";
		i++;
		c[i] = "4447xxxxxxxxxxxx";
		cd[i] = "Visa - AmeriTrust";
		i++;
		c[i] = "4448020xxxxxx";
		cd[i] = "Visa Premier card";
		i++;
		c[i] = "4452xxxxxxxxxxxx";
		cd[i] = "Visa - Empire Affiliates Federal Credit Union";
		i++;
		c[i] = "4498xxxxxxxxxxxx";
		cd[i] = "Visa - Republic Savings";
		i++;
		c[i] = "4502xxxxxxxxxxxx";
		cd[i] = "Visa - C.I.B.C.";
		i++;
		c[i] = "4503xxxxxxxxxxxx";
		cd[i] = "Visa - Canadian Imperial Bank";
		i++;
		c[i] = "4506xxxxxxxxxxxx";
		cd[i] = "Visa - Belgium A.S.L.K.";
		i++;
		c[i] = "4510xxxxxxxxxxxx";
		cd[i] = "Visa - Royal Bank of Canada";
		i++;
		c[i] = "4520xxxxxxxxxxxx";
		cd[i] = "Visa - Toronto Dominion of Canada";
		i++;
		c[i] = "4537xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Nova Scotia";
		i++;
		c[i] = "4538xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Nova Scotia";
		i++;
		c[i] = "4539xxxxxxxxxxxx";
		cd[i] = "Visa - Barclays (UK)";
		i++;
		c[i] = "4543xxxxxxxxxxxx";
		cd[i] = "Visa - First Direct";
		i++;
		c[i] = "4544xxxxxxxxxxxx";
		cd[i] = "Visa - T.S.B. Bank";
		i++;
		c[i] = "4556xxxxxxxxxxxx";
		cd[i] = "Visa - Citibank";
		i++;
		c[i] = "4564xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Queensland";
		i++;
		c[i] = "4673xxxxxxxxxxxx";
		cd[i] = "Visa - First Card";
		i++;
		c[i] = "4678xxxxxxxxxxxx";
		cd[i] = "Visa - Home Federal";
		i++;
		c[i] = "4707xxxxxxxxxxxx";
		cd[i] = "Visa - Tompkins County Trust";
		i++;
		c[i] = "47121250xxxxxxxx";
		cd[i] = "Visa - IBM Credit Union";
		i++;
		c[i] = "4719xxxxxxxxxxxx";
		cd[i] = "Visa - Rocky Mountain";
		i++;
		c[i] = "4721xxxxxxxxxxxx";
		cd[i] = "Visa - First Security";
		i++;
		c[i] = "4722xxxxxxxxxxxx";
		cd[i] = "Visa - West Bank";
		i++;
		c[i] = "4726xxxxxxxxxxxx";
		cd[i] = "Visa CV - Wells Fargo";
		i++;
		c[i] = "4783xxxxxxxxxxxx";
		cd[i] = "Visa - AT&T's Universal Card";
		i++;
		c[i] = "4784xxxxxxxxxxxx";
		cd[i] = "Visa - AT&T's Universal Card";
		i++;
		c[i] = "4800xxxxxxxxxxxx";
		cd[i] = "Visa - M.B.N.A. North America";
		i++;
		c[i] = "4811xxxxxxxxxxxx";
		cd[i] = "Visa - Bank of Hawaii";
		i++;
		c[i] = "4819xxxxxxxxxxxx";
		cd[i] = "Visa - Macom Federal Credit Union";
		i++;
		c[i] = "4820xxxxxxxxxxxx";
		cd[i] = "Visa - IBM Mid America Federal Credit Union";
		i++;
		c[i] = "4833xxxxxxxxxxxx";
		cd[i] = "Visa - U.S. Bank";
		i++;
		c[i] = "4842xxxxxxxxxxxx";
		cd[i] = "Visa - Security Pacific Washington";
		i++;
		c[i] = "4897xxxxxxxxxxxx";
		cd[i] = "Visa - Village Bank of Chicago";
		i++;
		c[i] = "4921xxxxxxxxxxxx";
		cd[i] = "Visa - Hong Kong National Bank";
		i++;
		c[i] = "4929xxxxxxxxxxxx";
		cd[i] = "Visa CV - Barclay Card (UK)";
		i++;
		c[i] = "45399710xxxxxxxx";
		cd[i] = "Visa - Banco di Napoli (Italy)";
		i++;
		c[i] = "4557xxxxxxxxxxxx";
		cd[i] = "Visa - BNL (Italy)";
		i++;
		c[i] = "4908xxxxxxxxxxxx";
		cd[i] = "Visa - Carta Moneta, CARIPLO/Intesa (Italy)";
		i++;
		c[i] = "4xxx9x604015xxxx";
		cd[i] = "Visa - Carta S? Unipol Banca (Italy)";
		i++;
		c[i] = "4xxx9x144046xxxx";
		cd[i] = "Visa - Carta S? Banco di Sardegna (Italy)";
		i++;
		c[i] = "4xxx9xxx40xxxxxx";
		cd[i] = "Visa - Carta S?(Italy)";
		i++;
		c[i] = "4532xxxxxxxxxxxx";
		cd[i] = "Visa - Credito Italiano (Italy)";
		i++;
		c[i] = "45475900xxxxxxxx";
		cd[i] = "Visa Gold - bank ganadero BBV (Colombia)";
		i++;
		c[i] = "4916xxxxxxxxxxxx";
		cd[i] = "Visa - MBNA Bank";
		i++;
		c[i] = "4xxxxxxxxxxxxx";
		cd[i] = "Visa";
		i++;
		c[i] = "4xxxxxxxxxxxxxxx";
		cd[i] = "Visa";
		i++;
		c[i] = "5031xxxxxxxxxxxx";
		cd[i] = "MasterCard - Maryland of North America";
		i++;
		c[i] = "5100xxxxxxxxxxxx";
		cd[i] = "MasterCard - Southwestern States Bankard Association";
		i++;
		c[i] = "5110xxxxxxxxxxxx";
		cd[i] = "MasterCard - Universal Travel Voucher";
		i++;
		c[i] = "5120xxxxxxxxxxxx";
		cd[i] = "MasterCard - Western States Bankard Association";
		i++;
		c[i] = "5130xxxxxxxxxxxx";
		cd[i] = "MasterCard - Eurocard France";
		i++;
		c[i] = "5140xxxxxxxxxxxx";
		cd[i] = "MasterCard - Mountain States Bankard Association";
		i++;
		c[i] = "5150xxxxxxxxxxxx";
		cd[i] = "MasterCard - Credit Systems, Inc.";
		i++;
		c[i] = "5160xxxxxxxxxxxx";
		cd[i] = "MasterCard - Westpac Banking Corporation";
		i++;
		c[i] = "5170xxxxxxxxxxxx";
		cd[i] = "MasterCard - Midamerica Bankard Association";
		i++;
		c[i] = "5172xxxxxxxxxxxx";
		cd[i] = "MasterCard - First Bank Card Center";
		i++;
		c[i] = "518xxxxxxxxxxxxx";
		cd[i] = "MasterCard - Computer Communications of America";
		i++;
		c[i] = "519xxxxxxxxxxxxx";
		cd[i] = "MasterCard - Bank of Montreal";
		i++;
		c[i] = "5201xxxxxxxxxxxx";
		cd[i] = "MasterCard - Mellon Bank, N.A.";
		i++;
		c[i] = "5202xxxxxxxxxxxx";
		cd[i] = "MasterCard - Central Trust Company, N.A.";
		i++;
		c[i] = "5204xxxxxxxxxxxx";
		cd[i] = "MasterCard - Security Pacific National Bank";
		i++;
		c[i] = "5205xxxxxxxxxxxx";
		cd[i] = "MasterCard - Promocion y Operacion, S.A.";
		i++;
		c[i] = "5206xxxxxxxxxxxx";
		cd[i] = "MasterCard - Banco Nacional do Mexico";
		i++;
		c[i] = "5207xxxxxxxxxxxx";
		cd[i] = "MasterCard - New England Bankard Association";
		i++;
		c[i] = "5208xxxxxxxxxxxx";
		cd[i] = "MasterCard - Million Card Service Co., Ltd.";
		i++;
		c[i] = "5209xxxxxxxxxxxx";
		cd[i] = "MasterCard - The Citizens & Southern National Bank";
		i++;
		c[i] = "5210xxxxxxxxxxxx";
		cd[i] = "MasterCard - Kokunai Shinpan Company, Ltd.";
		i++;
		c[i] = "5211xxxxxxxxxxxx";
		cd[i] = "MasterCard - Chemical Bank Delaware";
		i++;
		c[i] = "5212xxxxxxxxxxxx";
		cd[i] = "MasterCard - F.C.C. National Bank";
		i++;
		c[i] = "5213xxxxxxxxxxxx";
		cd[i] = "MasterCard - The Bankcard Association, Inc.";
		i++;
		c[i] = "5215xxxxxxxxxxxx";
		cd[i] = "MasterCard - Marine Midland Bank, N.A.";
		i++;
		c[i] = "5216xxxxxxxxxxxx";
		cd[i] = "MasterCard - Old Kent Bank & Trust Co.";
		i++;
		c[i] = "5217xxxxxxxxxxxx";
		cd[i] = "MasterCard - Union Trust";
		i++;
		c[i] = "5218xxxxxxxxxxxx";
		cd[i] = "MasterCard - Citibank/Citicorp";
		i++;
		c[i] = "5219xxxxxxxxxxxx";
		cd[i] = "MasterCard - Central Finance Co., Ltd.";
		i++;
		c[i] = "5220xxxxxxxxxxxx";
		cd[i] = "MasterCard - Sovran Bank/Central South";
		i++;
		c[i] = "5221xxxxxxxxxxxx";
		cd[i] = "MasterCard - Standard Bank of South Africa, Ltd.";
		i++;
		c[i] = "5222xxxxxxxxxxxx";
		cd[i] = "MasterCard - Security Bank & Trust Company";
		i++;
		c[i] = "5223xxxxxxxxxxxx";
		cd[i] = "MasterCard - Trustmark National Bank";
		i++;
		c[i] = "5224xxxxxxxxxxxx";
		cd[i] = "MasterCard - Midland Bank";
		i++;
		c[i] = "5225xxxxxxxxxxxx";
		cd[i] = "MasterCard - First Pennsylvania Bank, N.A.";
		i++;
		c[i] = "5226xxxxxxxxxxxx";
		cd[i] = "MasterCard - Eurocard Ab";
		i++;
		c[i] = "5227xxxxxxxxxxxx";
		cd[i] = "MasterCard - Rocky Mountain Bankcard System, Inc.";
		i++;
		c[i] = "5228xxxxxxxxxxxx";
		cd[i] = "MasterCard - First Union National Bank of North Carolina";
		i++;
		c[i] = "5229xxxxxxxxxxxx";
		cd[i] = "MasterCard - Sunwest Bank of Albuquerque, N.A.";
		i++;
		c[i] = "5230xxxxxxxxxxxx";
		cd[i] = "MasterCard - Harris Trust & Savings Bank";
		i++;
		c[i] = "5231xxxxxxxxxxxx";
		cd[i] = "MasterCard - Badische Beamtenbank EG";
		i++;
		c[i] = "5232xxxxxxxxxxxx";
		cd[i] = "MasterCard - Eurocard Deutschland";
		i++;
		c[i] = "5233xxxxxxxxxxxx";
		cd[i] = "MasterCard - Computer Systems Association, Inc.";
		i++;
		c[i] = "5234xxxxxxxxxxxx";
		cd[i] = "MasterCard - Citibank Arizona";
		i++;
		c[i] = "5235xxxxxxxxxxxx";
		cd[i] = "MasterCard - Financial Transaction System, Inc.";
		i++;
		c[i] = "5236xxxxxxxxxxxx";
		cd[i] = "MasterCard - First Tennessee Bank, N.A.";
		i++;
		c[i] = "5254xxxxxxxxxxxx";
		cd[i] = "MasterCard - Bank of America";
		i++;
		c[i] = "5273xxxxxxxxxxxx";
		cd[i] = "MasterCard (can be Gold) - Bank of America";
		i++;
		c[i] = "5286xxxxxxxxxxxx";
		cd[i] = "MasterCard - Home Federal";
		i++;
		c[i] = "5291xxxxxxxxxxxx";
		cd[i] = "MasterCard - Signet Bank";
		i++;
		c[i] = "5329xxxxxxxxxxxx";
		cd[i] = "MasterCard - Maryland of North America";
		i++;
		c[i] = "5410xxxxxxxxxxxx";
		cd[i] = "MasterCard - Wells Fargo";
		i++;
		c[i] = "5412xxxxxxxxxxxx";
		cd[i] = "MasterCard - Wells Fargo";
		i++;
		c[i] = "5419xxxxxxxxxxxx";
		cd[i] = "MasterCard - Bank of Hoven";
		i++;
		c[i] = "5424xxxxxxxxxxxx";
		cd[i] = "MasterCard - Citibank/Citicorp";
		i++;
		c[i] = "5434xxxxxxxxxxxx";
		cd[i] = "MasterCard - National Westminster Bank";
		i++;
		c[i] = "5465xxxxxxxxxxxx";
		cd[i] = "MasterCard - Chase Manhattan";
		i++;
		c[i] = "52550114xxxxxxxx";
		cd[i] = "MasterCard - Banco di Sardegna (Italy)";
		i++;
		c[i] = "530693xxxxxxxxxx";
		cd[i] = "MasterCard - Bancolombia Cadenalco (Colombia)";
		i++;
		c[i] = "5406251xxxxxxxxx";
		cd[i] = "MasterCard - Banco de Occidente (Colombia)";
		i++;
		c[i] = "5426xxxxxxxxxxxx";
		cd[i] = "MasterCard - Granahorrar (Colombia)";
		i++;
		c[i] = "5406xxxxxxxxxxxx";
		cd[i] = "MasterCard - Granahorrar (Colombia)";
		i++;
		c[i] = "5xxxxxxxxxxxxxxx";
		cd[i] = "MasterCard/Access/Eurocard";
		i++;
		c[i] = "6013xxxxxxxxxxxx";
		cd[i] = "Discover - MBNA Bank";
		i++;
		c[i] = "60xxxxxxxxxxxxxx";
		cd[i] = "Discover";
		i++;
	}

}
