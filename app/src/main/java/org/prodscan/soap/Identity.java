package org.prodscan.soap;

import org.ksoap2.serialization.SoapObject;

public class Identity
{
    private static final String METHOD_NAME = "Identity";
    private static final String NAMESPACE = "http://tempuri.org/";

     public String s1;


	public SoapObject GetSoapParams()
	{
         SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
     request.addProperty("s1", s1);


         return request;
	}

	public String GetSoapAction()
	{
		return NAMESPACE + METHOD_NAME;
	}

}
