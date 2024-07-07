package Providers;

import FawarySystem.*;
import Form.*;
import Payment.IPayment;
import Person.Users;

public abstract class IServiceProvider {
	public String name;
	public IPayment payment;
     public Form form;
    public  abstract boolean handler(Users user);
    public  abstract boolean creatForm(Users user,Form form);
    public String getName(){
    	return this.name;
    }
    
}
