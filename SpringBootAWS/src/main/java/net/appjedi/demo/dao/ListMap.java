package net.appjedi.demo.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListMap {
	List<HashMap<String, Object>>data = new ArrayList<HashMap<String, Object>>();
	int currentIndex =0;
	HashMap<String, Object>currentRow=null;
	String[]columns = null;

	public ListMap() {}
	public ListMap(ResultSet rs) {
		add(rs);
	}
	public int add(ResultSet rs)
	{

		try {
			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			columns =new String[colCount];
			for (int i=0;i<colCount;i++)
			{
				String colName =md.getColumnName(i+1).toLowerCase();
				//System.out.printf("%d - %s\n", i,colName);

				columns[i]=colName;
			}

			while (rs.next())
			{
				HashMap<String, Object>row = new HashMap<String, Object>();
				for (int i=0;i<colCount;i++)
				{
					row.put(columns[i], rs.getObject(i+1));
				}
				data.add(row);
			}
			
		}catch (Exception ex)
		{
			ex.printStackTrace();
			return -1;
		}
		
		return data.size();
	}
	public boolean next()
	{
		try {
			if (data.size()<currentIndex)
			{
				return false;
			}
			++currentIndex;
			currentRow=data.get(currentIndex);
			return true;
		}catch (Exception ex)
		{
			return false;
		}
	}
	@Override
	public String toString ()
	{
		StringBuilder sb=new StringBuilder("");
		
		for (HashMap<String, Object>map:data)
		{
			StringBuilder row=new StringBuilder("");
			for (int i=0;i<columns.length;i++)
			{
				String name=columns[i];
				row.append(String.format("%s: %s", name, map.get(name))+(i<columns.length-1?",":""));
			}
			sb.append(row+"\n");
		}
		
		
		return sb.toString();
	}
	public List<HashMap<String, Object>>getList()
	{
		return this.data;
	}
	
	public String[]getColumns(){return this.columns;}
	
	public String getString (String key)
	{
		return (String)currentRow.get(key.toLowerCase());
	}
	public Integer getInt (String key)
	{
		return (Integer)currentRow.get(key.toLowerCase());
	}
	public Long getLong (String key)
	{
		return (Long)currentRow.get(key.toLowerCase());
	}
	public Float getFloat (String key)
	{
		return (Float)currentRow.get(key.toLowerCase());
	}
	public Double getDouble (String key)
	{
		return (Double)currentRow.get(key.toLowerCase());
	}
}
