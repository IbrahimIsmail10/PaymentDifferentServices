package Services;

import java.util.ArrayList;
import java.util.List;

import Providers.*;
import ServiceProviderFactorys.*;

public class InternetRechargeService extends Service {
    public InternetRechargeService(){
    	super();
    	this.name="InternetRecharge";
    }
	public void creatProvider(String name) {
		this.ID++;
		this.servicefactory=new InternetProviderFactory();
        this.provider=servicefactory.createServiceProvider(name);
		
		
	}
	public List services(){
        List <IServiceProvider> providers = new ArrayList<IServiceProvider>(); 
		providers.add(new WEProvider());
		providers.add(new VodafoneProvider());
		providers.add(new OrangeProvider());
		providers.add(new EtisalatProvider());
        return providers;
    }
}
