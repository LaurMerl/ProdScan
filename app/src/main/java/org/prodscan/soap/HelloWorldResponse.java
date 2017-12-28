package org.prodscan.soap;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;


public class HelloWorldResponse extends BaseObject {

    public String HelloWorldResult;


    public Object getProperty(int index)
    {

    	return HelloWorldResult;

    }

    public int getPropertyCount()
    {
        return 1;
    }

    @SuppressWarnings("unchecked")
	public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info)
    {

    	info.name = "HelloWorldResult";
        info.type = new String().getClass();

    }

    public void setProperty(int index, Object value)
    {

    	HelloWorldResult = (String) value;

    }

    public void register(SoapSerializationEnvelope envelope) {
        envelope.addMapping(NAMESPACE, "HelloWorldResponse", this.getClass());
        
    }

}
