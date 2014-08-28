package com.zwb.mp3tag.connector.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zwb.mp3tag.connector.api.IMetadataPersister;
import com.zwb.mp3tag.connector.exception.gkMp3TaggerConnectorRuntimeExceptionFileIO;

public class MetainfoSimplePersister implements IMetadataPersister
{
	private static final int POS_ARTIST_NAME = 0;
	private static final int POS_RELEASE_NAME = 1;
	private static final int POS_TRACKS_NAMES = 2;
	
	File file;
	
	public MetainfoSimplePersister(String folder, String filename)
	{
		this.file = new File(folder, filename);
	}
	
	public MetainfoSimplePersister(String path)
	{
		this.file = new File(path);
	}

	@Override
	public String getValue(String key) 
	{
		return null;
	}

	@Override
	public void setValue(String key, String value) 
	{
	}

	@Override
	public void setValues(Map<String, String> values) 
	{
	}

	@Override
	public String printFormatted() 
	{
		return null;
	}

	@Override
	public void sort() 
	{		
	}
	
	private BufferedReader brFromLine(int line)
	{
		try 
		{
			FileReader a = new FileReader(this.file);
	        BufferedReader br = new BufferedReader(a);
	        for(int i=0; i<line; i++)
	        {
	        	br.readLine();
	        }
	        return br;
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	private String getArtistName()
	{
		try 
		{
			BufferedReader br = brFromLine(POS_ARTIST_NAME);
			return br.readLine();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	private String getReleaseName()
	{
		try 
		{
			BufferedReader br = brFromLine(POS_RELEASE_NAME);
			return br.readLine();
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
	
	private List<String> getTrackNames()
	{
		try 
		{
			List<String> l = new ArrayList<>();
			BufferedReader br = brFromLine(POS_ARTIST_NAME);
			String line;
			while((line = br.readLine()) != null)
			{
				l.add(line);
			}
			return l;
		} 
		catch (IOException e) 
		{
			throw new gkMp3TaggerConnectorRuntimeExceptionFileIO("error writing/reading file <"+this.file.getAbsolutePath()+">", e);
		}
	}
}
