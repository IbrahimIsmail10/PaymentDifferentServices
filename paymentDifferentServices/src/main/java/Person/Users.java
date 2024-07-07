package Person;

import java.security.Provider.Service;
import java.util.HashMap;
import java.util.Map;

import FawarySystem.Refund;
import FawarySystem.Transactions;

public class Users extends Person {
	public Wallet wallet;
	public Transactions ts;
	public Map <Integer,Double> service_money = new HashMap<Integer,Double>();
	public Users(){
		super();
	}
	public Users(String name,String pass,String email){
		super(name,pass,email);
		wallet = new Wallet();
		ts = new Transactions();
	}
	//this function return to user money in his wallet when its refund accepted
	public void returnmoney(int id){
		for (Map.Entry<Integer,Double> entry : service_money.entrySet()){
			if (entry.getKey().equals(id)) {
				double x = entry.getValue();
				System.out.println(id +"     "+ x);
				wallet.add_amount(x);
				break;
			}
		}
		service_money.remove(id);
	}
}
