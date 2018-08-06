package findDependents;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-01-02 22:07:19 IST
// -----( ON-HOST: SudheerPriya

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import java.text.*;
import com.wm.util.*;
import java.util.*;
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class services

{
	// ---( internal utility methods )---

	final static services _instance = new services();

	static services _newInstance() { return new services(); }

	static services _cast(Object o) { return (services)o; }

	// ---( server methods )---




	public static final void writeFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] object:0:required bytes
		// [i] field:0:required overwrite {"true","false"}
		String filename = null;
		Object byteContent = null;
		boolean append = false;
		
		IDataCursor idc = pipeline.getCursor();
		
		idc.first("filename");
		filename = (String) idc.getValue();
		idc.first("bytes");
		byteContent = (Object) idc.getValue();
		if ( idc.first("overwrite") ) {
			append = ((String) idc.getValue()).compareTo("false") == 0 ? true : false;
		} else {
			append = false;
		}
		
		int tries=0; 
		while(true) {
			try {
				FileOutputStream f=new FileOutputStream((String)(filename),append); 
				f.write((byte[])(byteContent));
				f.close();
				break;
			} catch(Exception e) {
				tries++;
		        if (tries==20) {
					throw new ServiceException(e.getMessage());
				}
			}
		}
		idc.destroy();
		// --- <<IS-END>> ---

                
	}
}

