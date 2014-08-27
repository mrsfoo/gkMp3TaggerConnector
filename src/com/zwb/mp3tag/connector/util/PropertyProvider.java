package com.zwb.mp3tag.connector.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import com.zwb.mp3tag.connector.exception.gkMp3TaggerConnectorRuntimeExceptionFileIO;
import com.zwb.tab.Tab;

public class PropertyProvider
{
	Properties props;
	File file;
	
	public PropertyProvider(String folder, String filename)
	{
		try 
		{
			this.file = new File(folder, filename);
			init();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	public PropertyProvider(String path)
	{
		try 
		{
			this.file = new File(path);
			init();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	private void init() throws IOException
	{
		if(file.exists())
		{
			FileReader reader = new FileReader(this.file);
			this.props = new Properties();
			this.props.load(reader);
		}
		else
		{
			file.createNewFile();
		}
	}
	
	public String getProperty(String key)
	{
		return this.props.getProperty(key);
	}
	
	public void setProperty(String key, String value)
	{
		try 
		{
			FileOutputStream out = new FileOutputStream(this.file);
			this.props.setProperty(key, value);
			this.props.store(out, new Timestamp(System.currentTimeMillis()).toString());
			out.close();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	public void setProperties(Map<String,String> properties)
	{
		try 
		{
			FileOutputStream out = new FileOutputStream(this.file);
			for(Entry<String, String> e: properties.entrySet())
			{
				this.props.setProperty(e.getKey(), e.getValue());				
			}
			this.props.store(out, new Timestamp(System.currentTimeMillis()).toString());
			out.close();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	public String toString()
	{
		String s = "{";
		String comma = "";
		for(Entry<Object, Object> e: this.props.entrySet())
		{
			s += comma+e.getKey()+"="+e.getValue();
			comma = ",";
		}
		s += "}";
		return s;
	}
	
	public String printFormatted()
	{
		Tab tab = new Tab("","key","value");
		if(this.props.entrySet()!=null)
		{
			for(Entry<Object, Object> e: this.props.entrySet())
			{
				tab.addRow(e.getKey().toString(), e.getValue().toString());
			}
		}
		return tab.printFormatted();
	}
	
}
