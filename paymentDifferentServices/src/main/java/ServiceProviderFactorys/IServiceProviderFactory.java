package ServiceProviderFactorys;
import Providers.*;
public interface IServiceProviderFactory {
    public IServiceProvider createServiceProvider(String choice);
}
