package org.prodscan.soap;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public final class WebServiceSoap
{

    public String Address = "http://192.168.0.103:81/Soap/WebService.asmx";
    public boolean IsDotNet = true;

    public HelloWorldResponse HelloWorld(HelloWorld params) throws Exception
    {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = IsDotNet;
        envelope.setOutputSoapObject(params.GetSoapParams());

        new HelloWorldResponse().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Address);
        androidHttpTransport.call(params.GetSoapAction(), envelope);

        HelloWorldResponse resp = (HelloWorldResponse)envelope.bodyIn;

        return resp;
       
    }

    public IdentityResponse Identity(Identity params) throws Exception
    {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = IsDotNet;
        envelope.setOutputSoapObject(params.GetSoapParams());

        new IdentityResponse().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Address);
        androidHttpTransport.call(params.GetSoapAction(), envelope);

        IdentityResponse resp = (IdentityResponse)envelope.bodyIn;

        return resp;
       
    }



}


