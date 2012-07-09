package org.jamie.test.rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>{

	static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   /*  
	    * 每条rss消息都至少包含tile,link,description,pubdate字段  
	    */  
	   private String title=null;  
	   private URL link=null;  
	   private String description=null;  
	   private String pubDate=null;  
	     
	   public String getTitle() {  
	       return title;  
	   }  

	   public void setTitle(String title) { 
	       this.title = title; 
	   } 

	   // getters and setters omitted for brevity 
	   public URL getLink() { 
	       return link; 
	   } 
	 
	   public void setLink(String link) { 
	       try { 
	           this.link = new URL(link); 
	       } catch (MalformedURLException e) {  
	             throw new RuntimeException(e); 
	        } 
	    }  
	  
	    public String getDescription() { 
	         return description; 
	     } 
	 /* 
	  * 里面加append部分是为了解决类似网易新闻中description tag解析两次的问题 
	  */ 
	     public void setDescription(String description) { 
	      if( this.description==null){ 
	          this.description=description; 
	          }else{ 
	              this.description=this.description+description; 
	          } 
	  } 

	  public String getDate() { 
	      return this.pubDate;  
	  }  
	 
	  public void setDate(String date) {  
	      try {  
	          if (date == null || date.equals("")) { 
	              this.pubDate = null; 
	          } else {  
	              this.pubDate = date;  
	          } 
	      } catch (Exception e) {  
	          e.printStackTrace(); 
	      }  
	      System.out.println("date string is:\t" + date);  
	  }  
	 
	  @Override  
	  public String toString() { 
	      StringBuilder sb = new StringBuilder();  
	      sb.append("Title: ");  
	      sb.append(title);  
	      sb.append('\n');  
	      sb.append("Date: ");  
	      sb.append(this.getDate());  
	      sb.append('\n'); 
	      sb.append("Link: ");  
	      sb.append(link);  
	      sb.append('\n');  
	      sb.append("Description: "); 
	      sb.append(description);  
	      return sb.toString();  
	  } 
	 
	  @Override 
	  public int hashCode() {  
	      final int prime = 31;  
	      int result = 1; 
	      result = prime * result + ((this.pubDate == null) ? 0 : this.pubDate.hashCode()); 
	      result = prime * result  
	              + ((description == null) ? 0 : description.hashCode());  
	      result = prime * result + ((link == null) ? 0 : link.hashCode());  
	      result = prime * result + ((title == null) ? 0 : title.hashCode()); 
	       return result; 
	   } 

	   @Override 
	   public boolean equals(Object obj) { 
	       if (this == obj) 
	           return true;
	       if (obj == null) 
	           return false; 
	       if (getClass() != obj.getClass())
	           return false;
	       Message other = (Message) obj; 
	       if (this.pubDate == null) {
	           if (other.pubDate != null)
	               return false;
	       } else if (!this.pubDate.equals(other.pubDate))
	           return false; 
	       if (description == null) {
	           if (other.description != null) 
	               return false;
	       } else if (!description.equals(other.description))
	           return false; 
	       if (link == null) { 
	           if (other.link != null)
	               return false;
	       } else if (!link.equals(other.link)) 
	           return false; 
	       if (title == null) { 
	           if (other.title != null) 
	               return false; 
	       } else if (!title.equals(other.title))
	           return false;
         return true; 
	     } 
	  
	     public int compareTo(Message another) { 
	         if (another == null) 
	             return 1; 
	         return another.pubDate.compareTo(this.pubDate);
	     }




}
