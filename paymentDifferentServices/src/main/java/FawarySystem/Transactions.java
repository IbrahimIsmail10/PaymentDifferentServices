package FawarySystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.InjectableValues;

import Person.Users;
import Person.Wallet;
import Providers.*;
import Services.*;

public class Transactions {
	public Map<String,Map<Integer,String>> trans = new HashMap <String,Map<Integer,String>>();
	public Map <Integer,Integer> map= new HashMap<Integer,Integer>();
	//every user has its own object of transaction we add to it when the payment of service is done
	public void Add_Transaction(Users u,Service service){
		boolean x = true;
		if(trans.get(u.email) == null){
			x = false;
		}
		if(x == true){
			trans.get(u.email).put(Service.ID,service.provider.getName()+service.getName());
		}
		else{
			trans.put(u.email, new HashMap<Integer,String>());
			trans.get(u.email).put(Service.ID,service.provider.getName()+service.getName());
			
		}
	}
	//this function is resbonible for only wallet transactions
	public void Add_wallet_trans(Users u,Wallet w){
        int amount =(int)w.Balance_amount ;
		boolean x = true;
		if(trans.get(u.email) == null){
			x = false;
		}
		if(x == true){
			trans.get(u.email).put(amount,"Add to Wallet Trans");
		}
		else{
			trans.put(u.email, new HashMap<Integer,String>());
			trans.get(u.email).put(amount,"Add to Wallet Trans");
			
		}
		

	}
	//return to spacifiec user its own transactions
	public Map<String,Map<Integer,String>> retriveTransaction(){
		return trans;
	}

}
