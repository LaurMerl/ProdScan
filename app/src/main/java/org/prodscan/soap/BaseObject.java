package org.prodscan.soap;

import org.ksoap2.serialization.*;

public abstract class BaseObject implements KvmSerializable {

    protected static final String NAMESPACE = "http://tempuri.org/";

    public BaseObject()
    {
        super();
    }

}
