package com.bb.TreePruning;

public class NavigationData {
	protected String id ;
	protected String name ;
	protected String url ;
	
	public NavigationData ()
	{
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 public NavigationData (String m_id, String m_name, String m_url )
	 {
		 id= m_id;
		 name = m_name ;
		 url = m_url ;
	 }
		public String toString()
		{
			return id.toString() + " " + name + " " + url ;
		}
}
