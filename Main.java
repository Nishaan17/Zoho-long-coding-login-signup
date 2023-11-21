package Zcoin_Exchange;
import java.util.*;
class storeValue{
	static String name;
	static String mail;
	static String mobileNumber;
	static int id;
	static String password;
	static String currency;
	void addloginDetails(String userName,String mail,String mobileNumber,int id,String password,String currency){
		this.name=userName;
		this.mail=mail;
		this.mobileNumber=mobileNumber;
		this.id=id;
		this.password=password;
		this.currency=currency;
	}
	static HashMap<Integer,String> userNamehm = new HashMap<>();
	static HashMap<Integer,String> mailhm = new HashMap<>();
	static HashMap<Integer,String> mobileNumberhm = new HashMap<>();
	static HashMap<Integer,String> passwordhm = new HashMap<>();
	static HashMap<Integer,String> cuurencyhm = new HashMap<>();
	void insert(Integer id) {
		userNamehm.put(id,storeValue.name);
		mailhm.put(id,storeValue.mail);
		mobileNumberhm.put(id,storeValue.mobileNumber);
		passwordhm.put(id,storeValue.password);
		cuurencyhm.put(id,storeValue.currency);
	}
}
class createAccount{
	static boolean checkPassword(String password, String userName, String mail, String mobileNumber){ 
		boolean flag=false;
		if(password.length()<8) {
			return false;
		}
		int numcheck=0;
		int lowcasecheck=0;
		int uppcasecheck =0;
		int splcharcheck=0;
		int userNamecheck=0;
		int mailCheck=0;
		if(password.contains("!")||password.contains("#") || password.contains("%") ||password.contains("?") ||password.contains(">") ||password.contains("<") ||password.contains("&") ||password.contains("*") ) {  //SPECIAL CHAR CHECK
			splcharcheck++;
		}
		for(int i=0; i<password.length(); i++) {
			if((int)password.charAt(i) >= 47 && (int)password.charAt(i)<=56){
				numcheck++;	
			}
			else if((int)password.charAt(i) >= 97 && (int)password.charAt(i)<=121){
				lowcasecheck++;
			}
			else if((int)password.charAt(i) >= 67 && (int)password.charAt(i)<=92){
				uppcasecheck++;
			}
		}
		if(!password.contains(userName)) {
			userNamecheck++;
		}
		if(!password.contains(mail)) {
		     mailCheck++;	
		}
		if(splcharcheck> 0 && numcheck>0  && lowcasecheck>0 && uppcasecheck>0 && userNamecheck>0 && mailCheck>0) {
			return true;
		}
		else {
			return false;
		}
	}
	static int storeuserInfo(int a){
		return 1;
	}
	void addDetails(Scanner s) {
		System.out.print("Enter your full name: ");
		String userName=s.next();         //USER NAME
		System.out.print("Enter your Email_id: ");
		String mail=s.next();   // MAIL
		System.out.print("Enter your Mobile_Number: ");
		String mobileNumber=s.next();   // Mobile  number
		double id_temp=Math.random()+1;
		double ii=id_temp*1000;
		int id=(int)ii;    // H_ID
		System.out.print("Enter Your Password: ");
		String password= s.next(); // PASSWORD
		boolean Password=checkPassword(password,userName,mail,mobileNumber);
     	if(Password) {
     		System.out.println("Account created succesfully");
     	}
     	else {
     		System.out.println("Not created");
     		addDetails(s);
     	}
     	System.out.println("Enter your Initial Real Currency");
     	String currency=s.next();
     	storeValue sv=new storeValue();
     	sv.addloginDetails(userName,mail,mobileNumber,id,password,currency);
     	sv.insert(id);
     	System.out.print("Create another Account?");
     	String yesno=s.next();
     	if(yesno.equals("Yes")) {
     		addDetails(s);
     	}
     	else {
     		Login.agentCheck(s);
     	}
	}
}
class Login{
	
	String agentName="Mani";
	String Password="Mani!12345";
	static void agentCheck(Scanner s) {
		System.out.print("ZE or NU");
		String input=s.next();
		if(input.equals("ZE")) {
		System.out.println("Agent UserName:");
		String agentUsername=s.next();
		System.out.println("Agent Password:");
		String agentPassword=s.next();
		int count1=0;
		int count2=0;
		if(agentUsername.equals("Mani")) {
			count1++;
		}
		if(agentPassword.equals("Mani!12345")) {
			count2++;
		}
		if(count1>0 && count2>0) {
			System.out.println("Login Successfull");
		}
		else {
			System.out.println("Invalid Agent");
			agentCheck(s);
		}
		}
		else if(input.equals("NU")) {
			System.out.print("Enter Your Email :");
			String mail=s.next();
			System.out.print("Enter your password :");
			String password=s.next();
			NormalUser nmUser= new NormalUser();
			nmUser.nmUserCheck(mail,password,s);
		}
		else {
			System.out.print("Enter Valid NU or ZE");
			agentCheck(s);
		}
	}
}
class NormalUser{
	void nmUserCheck(String mail, String password, Scanner s) {
		boolean usernamee=storeValue.mailhm.containsValue(mail);
		boolean passwordd=storeValue.passwordhm.containsValue(password);
		if(usernamee && passwordd) {
			System.out.print("Login Succesfull");
		}
		else {
			System.out.print("Invalid Login");
			Login.agentCheck(s);
		}
	}
}
public class Main {
	public static void main(String[] args) {
		createAccount CA= new createAccount();
		Login log= new Login();
		Scanner s= new Scanner(System.in);
		System.out.print("1) Create Account\n2)Login\n3)User Managment Panel\n4)User Acc Managment\n5)Special Category \n 6)another Login");
        int input=s.nextInt();
        switch(input) {
        case 1:
        	CA.addDetails(s);
        	log.agentCheck(s);
        	break;
        case 2:
        	log.agentCheck(s);
        	break;
        case 3:
        	
        }
	}
}