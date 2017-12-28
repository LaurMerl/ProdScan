package org.prodscan.soap;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;


public class IdentityResponse extends BaseObject {

    public String IdentityResult;


    public Object getProperty(int index)
    {

    	return IdentityResult;

    }

    public int getPropertyCount()
    {
        return 1;
    }

    @SuppressWarnings("unchecked")
	public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info)
    {

    	info.name = "IdentityResult";
        info.type = new String().getClass();

    }

    public void setProperty(int index, Object value)
    {

    	IdentityResult = (String) value;

    }

    public void register(SoapSerializationEnvelope envelope) {
        envelope.addMapping(NAMESPACE, "IdentityResponse", this.getClass());
        
    }

}
