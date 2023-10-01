package crypto;

public class Vignere 
{
	private static char lower[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static char upper[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	private String repeatKey(int len,String key)
	{
		while(len > key.length())
		{
			key += key;
		}
		return key.substring(0, len);
	}
	
	private int indexOf(char alpha[],char seek)
	{
		for(int i=0;i<alpha.length;i++)
		{
			if(alpha[i]==seek)
				return i;
		}
		return -1;
	}
	
	public String encrypt(String plainText,String key)
	{
		String eMsg = "";
		int msg_Index,key_Index,temp;
		char ch,k;
		
		if(plainText.length() > key.length())
			key = repeatKey(plainText.length(),key);
		
		for(int i=0;i<plainText.length();i++)
		{
		
			ch = plainText.charAt(i);
			k = key.charAt(i);
			
			if(Character.isLowerCase(ch))
			{
				msg_Index = indexOf(lower,ch);
				k = Character.toLowerCase(k);
				key_Index = indexOf(lower,k);
				temp = (msg_Index + key_Index) % 26;
				eMsg += lower[temp];
			}
			else if(Character.isUpperCase(ch))
			{
				msg_Index = indexOf(upper,ch);
				k = Character.toUpperCase(k);
				key_Index = indexOf(upper,k);
				temp = (msg_Index + key_Index) % 26;
				eMsg += upper[temp];
			}
			else
				eMsg += ch;
			
		}
		
		return eMsg;
	}
	public String decrypt(String cipherText,String key)
	{
		String dMsg = "";
		int msg_Index,key_Index,temp;
		char ch,k;
		
		if(cipherText.length() > key.length())
			key = repeatKey(cipherText.length(),key);
		
		for(int i=0;i<cipherText.length();i++)
		{
			ch = cipherText.charAt(i);
			k = key.charAt(i);
			if(Character.isLowerCase(ch))
			{
				msg_Index = indexOf(lower,ch);
				k = Character.toLowerCase(k);
				key_Index = indexOf(lower,k);
				temp = (msg_Index -  key_Index + 26) % 26;
				
				dMsg += lower[temp];
			}
			else if(Character.isUpperCase(ch))
			{
				msg_Index = indexOf(upper,ch);
				k = Character.toUpperCase(k);
				key_Index = indexOf(upper,k);
				temp = (msg_Index -  key_Index + 26) % 26;
				
				dMsg += upper[temp];
			}
			else
				dMsg += ch;
		}
		return dMsg;
	}
}
