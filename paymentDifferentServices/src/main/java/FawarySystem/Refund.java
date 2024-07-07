package FawarySystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import Person.Users;
import Services.Service;
@org.springframework.stereotype.Service
public class Refund {
	private static Refund instance = null;
	public DataBase db= DataBase.Get_Instance();
	public Map<Integer,String> refunds = new HashMap<Integer,String>();
	public Map<Integer,String> connection = new HashMap<Integer,String>();	
	public Map<String,Map<String,String>> reuser = new HashMap<String,Map<String,String>>();
	
	public Map<Integer,String>getReefunds(){

		return refunds;
	}
	private Refund(){}
	public static Refund Get_Instance(){
		synchronized(Refund.class){
			if(instance == null){
				instance = new Refund();
			}
		}
		return instance;
	}
	//user use it to ask refund of it's services
	public void ask_refund(Users user,int choice){
		
        refunds.put(choice,user.ts.trans.get(user.email).get(choice) );
		if(reuser.get(user.email) == null){
			reuser.put(user.email, new HashMap<String,String>());
			reuser.get(user.email).put(user.ts.trans.get(user.email).get(choice),"Waiting");
		}
		else{
			reuser.get(user.email).put(user.ts.trans.get(user.email).get(choice),"Waiting");
		}
		db.addRefunds(user, choice ,user.ts.trans.get(user.email).get(choice));
		connection.put(choice, user.email);
	}
//this function for admin which can accept or reject users refunds 
	public Map<Integer,String> setStatusOfSpecificRefunds(int choice,String status){
        if(status.contains("Accept")){
			String mail = connection.get(choice);
			int found;
        	reuser.get(mail).replace(refunds.get(choice),"Accepted");
			//if admin accept we remove this refund from refunds
        	refunds.remove(choice);
				Users u = db.getUser(mail);
				if (u != null) {
				//if admin accept return money of this transaction to user wallet	
				u.returnmoney(choice);
			}

    	}
        else{
			//if user reject the refund we also remove it from refunds
        	reuser.get(connection.get(choice)).replace(refunds.get(choice),"Rejected");
        	refunds.remove(choice);
        }
		return refunds;
		
        
	}
	//return to user all of his refunds
	public Map<String,Map<String,String>> printuser(Users u){
		return reuser;
	}
}
	
