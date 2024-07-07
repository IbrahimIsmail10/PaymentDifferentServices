package Services;
import java.util.ArrayList;
import java.util.List;

import Providers.*;
import Providers.LandlineProvider;
import ServiceProviderFactorys.LandlineProviderFactory;
import ServiceProviderFactorys.MobileProviderFactory;

public class LandlineRechargeService extends Service{
    public LandlineRechargeService(){
           super();
          this.name="LandlineRecharge";
    }
	public void creatProvider(String name) {
        this.ID++;
		this.servicefactory=new LandlineProviderFactory();
        this.provider=servicefactory.createServiceProvider(name);
		
	}

    public List services(){
        List <IServiceProvider> providers = new ArrayList<IServiceProvider>(); 
        providers.add(new LandlineProvider(new Monthly()));
        providers.add(new LandlineProvider(new Quarter()));
        
        return providers;
    }
}
