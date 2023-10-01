package crypto;

public class Vernam 
{
	private static char alpha[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int indexOf(char alpha[],char seek)
	{
		for(int i=0;i<alpha.length;i++)
		{
			if(alpha[i]==seek)
				return i;
		}
		return -1;
	}
	private String repeatKey(int len,String key)
	{
		while(len > key.length())
		{
			key += key;
		}
		return key.substring(0, len);
	}
	public String encrypt(String msg,String key)
	{
		int msg_Index,key_Index,temp;
		String eMsg = "";
		
		if(msg.length() > key.length())
			key = repeatKey(msg.length(),key);
		
		for(int i=0;i<msg.length();i++)
		{
			msg_Index = indexOf(alpha,msg.charAt(i));
			key_Index = indexOf(alpha,key.charAt(i));
			
			temp = (msg_Index ^ key_Index);
			
			eMsg += String.format("%02X", temp);
			
		}
		return eMsg;
	}
	public String decrypt(String msg,String key)
	{
		int msg_Index,key_Index,temp;
		String hexaByte;
		String dMsg = "";
		
		if(msg.length()/2 > key.length())
			key = repeatKey(msg.length()/2,key);
		
		for(int i=0,j=0;i<key.length();i++,j+=2)
		{
			hexaByte = msg.substring(j,j+2);
			
			msg_Index = Integer.parseInt(hexaByte, 16); 
			
			key_Index = indexOf(alpha,key.charAt(i));
			
			temp = (msg_Index ^ key_Index) % 52;
			
			dMsg += alpha[temp];
		}
		return dMsg;
	}
}
