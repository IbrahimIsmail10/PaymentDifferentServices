package Services;
import Providers.IServiceProvider;
import ServiceProviderFactorys.*;

import java.util.ArrayList;
import java.util.List;

import Payment.IPayment;
import Providers.*;
public abstract class Service {
    protected String name;
    public static int ID=0 ;
    public  IServiceProvider provider; 
    public  IServiceProviderFactory servicefactory; 

    public abstract void creatProvider(String name);
             
    public String getName(){
        return this.name;
    }
    public List services(){
        List <IServiceProvider> providers = new ArrayList<IServiceProvider>(); 

        return providers;
    }
}
