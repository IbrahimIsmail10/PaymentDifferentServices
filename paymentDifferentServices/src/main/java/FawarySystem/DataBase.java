package FawarySystem;

import java.util.*;

import org.springframework.context.annotation.Scope;
import Person.*;
import Services.*;
@org.springframework.stereotype.Service
@Scope("singleton")
public class DataBase{
	private static DataBase instance = null;
	public Map<String,Map<Integer,String>> transactionsHistory = new HashMap<String,Map<Integer,String>>();
	public Map<String,Map<Integer,String>> refundsHistory = new HashMap<String,Map<Integer,String>>();

	public List <Users> users=new ArrayList<Users>(); 
	public Admins [] admins ={
		new Admins("ibrahim","123456","ibrahim10@gmail.com"),
		new Admins("ballo","123456","ballo@gmail.com")
	};
	private DataBase(){}
	//add all users transactions in history 
	public void addTransaction(Users u,Service ser){
		if(transactionsHistory.get(u.email) == null){
			
			transactionsHistory.put(u.email, new HashMap<Integer,String>());
			transactionsHistory.get(u.email).put(ser.ID,ser.provider.getName()+ser.getName());
		}
		else{
			transactionsHistory.get(u.email).put(ser.ID,ser.provider.getName()+ser.getName());
		}
}
	//add all Refunds in history 
    public void addRefunds(Users u,int id, String name ){
	if(refundsHistory.get(u.email) == null){
		
		refundsHistory.put(u.email, new HashMap<Integer,String>());
		refundsHistory.get(u.email).put(id,name);
	}
	else{
		refundsHistory.get(u.email).put(id,name);
	}
}
    public Map<String,Map<Integer,String>> getALLTransactions(){
	return transactionsHistory;
}
    public Map<String,Map<Integer,String>> getALLRefunds(){
	return refundsHistory;
}
public void addWalletTrans(Users u,double amount){
	if(transactionsHistory.get(u.email) == null){
			
		transactionsHistory.put(u.email, new HashMap<Integer,String>());
		transactionsHistory.get(u.email).put((int)amount,"Add TO Wallet Transaction");
	}
	else{
		transactionsHistory.get(u.email).put((int)amount,"Add TO Wallet Transaction");
	}
}
public static DataBase Get_Instance(){
		synchronized(DataBase.class){
			if(instance == null){
				instance = new DataBase();
			}
		}
		return instance;
	}
	public void addUser(Users obj){
		users.add(obj);
	}

	public Admins loginAdmin(Person admin) {
		
		for(int i = 0;i<admins.length;i++){
			if(admins[i].email.equals(admin.email)){
				if(admins[i].password.equals(admin.password)){
					return admins[i];
				}
			}
		}
		
		return null;
	}

	public Users loginUser(Person user) {
		for(int i = 0;i<users.size();i++){
			if(users.get(i).email.equals(user.email)){
				if(users.get(i).password.equals(user.password)){
					return users.get(i);
				}
			}
		}
		return null;
	}

	public Users signUP(Person p) {
		
		if(authentication(p.email)){
			return null;
		}
		Users newUser = new Users(p.username,p.password,p.email);
		addUser(newUser);
		
		return newUser;
	}
	
	public boolean authentication(String email) {
		for(int i = 0;i<users.size();i++){
			if(users.get(i).email.equals(email)){
				return true;
			}
		}
		return false;
	}
	public Users getUser (String mail){
		for(int i = 0;i<users.size();i++){
			if(users.get(i).email.equals(mail)){
				return users.get(i);

			}
		}
		return null;
	}

}

