package Services;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy.Provider;

import Providers.IServiceProvider;
import Providers.*;
import ServiceProviderFactorys.DonationProviderFactory;
import ServiceProviderFactorys.MobileProviderFactory;

public class MobileRechargeService extends Service{
    public MobileRechargeService(){
    	super();
    	this.name="MobileRecharge";
    }

	@Override
	public void creatProvider(String name) {
		this.ID++;
		this.servicefactory=new MobileProviderFactory();
        this.provider=servicefactory.createServiceProvider(name);
		
	}

	@Override
	public List services(){
        List <IServiceProvider> providers = new ArrayList<IServiceProvider>(); 
		providers.add(new WEProvider());
		providers.add(new VodafoneProvider());
		providers.add(new OrangeProvider());
		providers.add(new EtisalatProvider());
        return providers;
    }
    
}
