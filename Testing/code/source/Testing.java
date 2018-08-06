

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-02-08 19:01:47 IST
// -----( ON-HOST: SudheerPriya

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.util.Stack ;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.ServerAPI;
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.Service;
import com.wm.app.audit.IAuditRuntime;
import com.wm.app.audit.*;
import java.io.*;
import java.util.*;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class Testing

{
	// ---( internal utility methods )---

	final static Testing _instance = new Testing();

	static Testing _newInstance() { return new Testing(); }

	static Testing _cast(Object o) { return (Testing)o; }

	// ---( server methods )---




	public static final void Strlength (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(Strlength)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required inputStr
		// [o] field:0:required Strlength
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	inputStr = IDataUtil.getString( pipelineCursor, "inputStr" );
			String Strlength=String.valueOf(inputStr.length()); 
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "Strlength", Strlength);
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void dateBefore (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dateBefore)>> ---
		// @sigtype java 3.5
		// [i] field:0:required date1
		// [i] field:0:required date2
		// [i] field:0:required datePattern
		// [o] field:0:required result
		// Method: dateBefore
		// Compares two dates. Returns 'true' (in result) if date1 < date2.
		// Rob Brouwers
		
		String date1str = "";
		String date2str = "";
		String datePattern = "";
		
		// pipeline
		IDataHashCursor pipelineCursor = pipeline.getHashCursor();
		if ( pipelineCursor.first( "date1" ) )
		{
			date1str = (String) pipelineCursor.getValue();
		}
		if ( pipelineCursor.first( "date2" ) )
		{
			date2str = (String) pipelineCursor.getValue();
		}
		if ( pipelineCursor.first( "datePattern" ) )
		{
			datePattern = (String) pipelineCursor.getValue();
		}
		pipelineCursor.destroy();
		
		if (datePattern == null) { 
		datePattern = "yyyy/MM/dd"; 
		} 
		try { 
		
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern); 
			Date date1 = sdf.parse(date1str);
			Date date2 = sdf.parse(date2str);
		
			String result = "FALSE";
			if (date1.before(date2)) {
				result = "TRUE";
			}
		
			// pipeline
			IDataHashCursor pipelineCursor_1 = pipeline.getHashCursor();
			pipelineCursor_1.last();
			pipelineCursor_1.insertAfter( "result", result );
			pipelineCursor_1.destroy();
		
		} catch (Exception e) { 
			throw new ServiceException("Error parsing the date: " + e); 
		}
		// --- <<IS-END>> ---

                
	}



	public static final void dateDiff (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dateDiff)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required date1
		// [i] field:0:required date2
		// [i] field:0:required unit
		// [i] field:0:required datePattern
		// [o] field:0:required diff
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	date1 = IDataUtil.getString( pipelineCursor, "date1" );
			String	date2 = IDataUtil.getString( pipelineCursor, "date2" );
			String	datePattern = IDataUtil.getString( pipelineCursor, "datePattern" );
			String	unit = IDataUtil.getString( pipelineCursor, "unit" );
		pipelineCursor.destroy();
		
		SimpleDateFormat sdf;
		GregorianCalendar cal;
		long diffUnits = -1;
		try {
		
			sdf = new SimpleDateFormat(datePattern);
			long d1 = sdf.parse(date1).getTime();
			long d2 = sdf.parse(date2).getTime();
		
			long diff = (d1<d2?d2-d1:d1-d2);
			
			long millisInUnit=0;	
		
			if (unit.compareTo("DAY")==0) millisInUnit = 86400000;
			if (unit.compareTo("HOUR")==0) millisInUnit = 3600000;
			if (unit.compareTo("MINUTE")==0) millisInUnit = 60000;
			if (unit.compareTo("SECOND")==0) millisInUnit = 1000;
			
			if (millisInUnit == 0) {
				throw new ServiceException("Argument 'unit' does not contain valid value");
			}
		
			diffUnits = diff / millisInUnit;
		
		} catch (Exception e) {
		  throw new ServiceException(e.getMessage());
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		pipelineCursor_1.last();
		pipelineCursor_1.insertAfter("diff",String.valueOf(diffUnits));
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void documentListToCSV (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(documentListToCSV)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:required list
		// [i] field:0:required field_delim
		// [i] field:0:required record_delim
		// [o] field:0:required output
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// list
			String result = "";
			IData[]	list = IDataUtil.getIDataArray( pipelineCursor, "list" );
		
			String	field_delim = IDataUtil.getString( pipelineCursor, "field_delim" );
			String	record_delim = IDataUtil.getString( pipelineCursor, "record_delim" );
		
			pipelineCursor.destroy();
		
		
		
			if ( list != null)
			{
				for ( int i = 0; i < list.length; i++ )
				{
					IDataCursor idc = list[i].getCursor();
					boolean b = idc.first();		
					while (b) {
						result = result + IDataUtil.getString(idc);
						b = idc.next();
						if (b) result = result + field_delim; 
					}
		
					if (i<list.length-1) result = result + record_delim;	
				}
			}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "output", result );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getCurrentServiceName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getCurrentServiceName)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required CallingServiceName
		// [o] field:0:required CallingService
		NSService callingService = null;
		String serviceFound = "true";
		String callingServiceName = "";
		Stack callStack = InvokeState.getCurrentState().getCallStack(); 
		int size = callStack.size(); 
		if (size >= 3)
		{ 
		callingService = (NSService) callStack.elementAt (size - 1); 
		callingServiceName = callingService.getNSName().getFullName(); 
		} 
		else
		{
		serviceFound = "false";
		}
		
		
		// pipeline out 
		IDataCursor pipelineCursor = pipeline.getCursor(); 
		IDataUtil.put( pipelineCursor, "ServiceFound", serviceFound);
		IDataUtil.put( pipelineCursor, "CallingServiceName", callingServiceName);
		IDataUtil.put( pipelineCursor, "CallingService", callingService);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getLineFileSeparator (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLineFileSeparator)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required FS
		// [o] field:0:required LS
		String fs = System.getProperty("file.separator");
		String ls = System.getProperty("line.separator");
		
		IDataHashCursor idc = pipeline.getHashCursor();
		idc.insertAfter("FS", fs);
		idc.insertAfter("LS", ls);
		idc.destroy(); 
		// --- <<IS-END>> ---

                
	}


    public static final Values multiConcat (Values in)
    {
        Values out = in;
		// --- <<IS-START(multiConcat)>> ---
		// @sigtype java 3.0
		// [i] field:0:required inStr1
		// [i] field:0:required inStr2
		// [i] field:0:required inStr3
		// [i] field:0:required inStr4
		// [i] field:0:required inStr5
		// [i] field:0:required inStr6
		// [i] field:0:required inStr7
		// [i] field:0:required inStr8
		// [i] field:0:required inStr9
		// [i] field:0:required inStr10
		// [o] field:0:required outStr
		/**
		 * Service takes in up to ten strings, checks them for null (see Shared tab
		 * method checkNull), and concatenates all of them together. checkNull
		 * returns a "" if the String is null, effectively cancelling out its effect
		 * on the concatenation. Returns the concatenated String as "outStr".
		 * 
		 */
		 
		
		String str1 = checkNull(in.getString("inStr1"));
		String str2 = checkNull(in.getString("inStr2"));
		String str3 = checkNull(in.getString("inStr3"));
		String str4 = checkNull(in.getString("inStr4"));
		String str5 = checkNull(in.getString("inStr5"));
		String str6 = checkNull(in.getString("inStr6"));
		String str7 = checkNull(in.getString("inStr7"));
		String str8 = checkNull(in.getString("inStr8"));
		String str9 = checkNull(in.getString("inStr9"));
		String str10 = checkNull(in.getString("inStr10"));
		
		String outStr = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10;
		
		out.put("outStr", outStr);
		
		// --- <<IS-END>> ---
        return out;
                
	}



	public static final void removeLineSeparators (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(removeLineSeparators)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inString
		// [o] field:0:required outString
		
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String inString = null;
		if (idcPipeline.first("inString"))
		{
		  inString = (String) idcPipeline.getValue();
		}
		else
		{
		  // Input string is null. Do nothing
		  return;
		}
		
		StringBuffer tempString = new StringBuffer(inString.length());
		String outString = "";
		
		// Strip string of all "\r\n" occurrences
		for (int i = 0; i < inString.length(); i++)
		{
		  char c;
		  c = inString.charAt(i);
		  if (c != '\r' && c != '\n')
		    tempString.append(c);
		}
		
		idcPipeline.insertAfter("outString", tempString.toString());
		
		// Clean up IData cursors
		idcPipeline.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void splitString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(splitString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inString
		// [i] field:0:required length
		// [o] field:1:required stringList
		// ***************************************************************************
		// splitString
		//
		// This JAVA service splits a string in substrings with a specified max length.
		//
		// Input: 	inString	String you want to split up
		//			length		Maximum length of the substrings
		//
		// Output:	stringList	The split string list
		//
		// ****************************************************************************
		
		
		String inString = null;
		int length = 0;
		
		IDataCursor idc = pipeline.getCursor();
		
		idc.first("inString");
		inString = (String) idc.getValue();
		 
		idc.first("length");
		length = Integer.parseInt( (String) idc.getValue() );
		
		if (length <= 0) 
			throw new ServiceException("Parameter length should contain a positive decimal.");
		
		int arraySize = (inString.length() - 1) / length + 1;
		
		String[] array = new String[arraySize];
		
		
		for (int t = 0; t < arraySize; t++) {
			try
			{
		array[t] = inString.substring(t*length, (t+1)*length);
			}
			catch (StringIndexOutOfBoundsException e)
			{
				array[t] = inString.substring(t*length, inString.length());
			}
		}
		
		idc.insertAfter("stringList", array); 
		idc.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Used by "multiConcat"
	 * 
	 */
	private static String checkNull(String inputString)
	{
	  if (inputString == null)
	    return "";
	  else
	    return inputString;
	}
	// --- <<IS-END-SHARED>> ---
}

