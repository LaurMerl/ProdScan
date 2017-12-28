package org.prodscan.soap;

import org.ksoap2.serialization.SoapObject;

public class HelloWorld
{
    private static final String METHOD_NAME = "HelloWorld";
    private static final String NAMESPACE = "http://tempuri.org/";



	public SoapObject GetSoapParams()
	{
         SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


         return request;
	}

	public String GetSoapAction()
	{
		return NAMESPACE + METHOD_NAME;
	}

}
