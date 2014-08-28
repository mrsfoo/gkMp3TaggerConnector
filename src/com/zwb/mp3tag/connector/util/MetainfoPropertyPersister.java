package com.zwb.mp3tag.connector.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import com.zwb.mp3tag.connector.api.IMetadataPersister;
import com.zwb.mp3tag.connector.exception.gkMp3TaggerConnectorRuntimeExceptionFileIO;
import com.zwb.tab.Tab;

public class MetainfoPropertyPersister implements IMetadataPersister
{
	Properties props;
	File file;
	
	public MetainfoPropertyPersister(String folder, String filename)
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
	
	public MetainfoPropertyPersister(String path)
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
	
	public String getValue(String key)
	{
		return this.props.getProperty(key);
	}
	
	public void setValue(String key, String value)
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
	
	public void setValues(Map<String,String> properties)
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
	
	public void sort()
	{
		Map<String,String> map = new TreeMap<>();
		Tab tab = new Tab("","","","");
		if(this.props.entrySet()!=null)
		{
			for(Entry<Object, Object> e: this.props.entrySet())
			{
				map.put(e.getKey().toString(), e.getValue().toString());
			}
		}
		if(this.props.entrySet()!=null)
		{
			for(Entry<String, String> e: map.entrySet())
			{
				tab.addRow(e.getKey(), "=", e.getValue());
			}
		}
		try 
		{
			FileWriter fw = new FileWriter(this.file);
			fw.write(tab.printHeadless());
			fw.close();
		}
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	
	
}
