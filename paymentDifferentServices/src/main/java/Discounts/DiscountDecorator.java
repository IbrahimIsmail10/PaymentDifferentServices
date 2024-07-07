package Discounts;

import Services.*;
public  class  DiscountDecorator extends Service{
    public  Service service;
    public DiscountDecorator(Service service){
        this.service=service;
        
    }
	public  void creatProvider(String name) {
        this.service.creatProvider( name);
        this.servicefactory=service.servicefactory;
        this.provider=service.provider;
    }
		
	
}
