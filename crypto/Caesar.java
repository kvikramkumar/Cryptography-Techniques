package crypto;
public class Caesar 
{
	private static char alpha[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	private static int index,t;
	
	public String encrypt(String plainText,int key)
	{
		String eMsg="";
		char ch;
		for(int i=0;i<plainText.length();i++)
		{
			ch = plainText.charAt(i);
			if(Character.isAlphabetic(ch))
			{
				index = indexOf(alpha,ch);
				t = (index + key) % 52;
				eMsg += alpha[t];
			}
			else
				eMsg += ch;
		}
		return eMsg;
	}
	
	public String decrypt(String cipherText,int key)
	{
		String dMsg="";
		char ch;
		for(int i=0;i<cipherText.length();i++)
		{
			ch = cipherText.charAt(i);
			if(Character.isAlphabetic(ch))
			{
				index = indexOf(alpha,ch);
				t = (index - key + 52) % 52;
				dMsg += alpha[t];
			}
			else
				dMsg += ch;
		}
		return dMsg;
	}
	
	int indexOf(char alpha[],char seek)
	{
		for(int i=0;i<alpha.length;i++)
		{
			if(alpha[i]==seek)
				return i;
		}
		return -1;
	}
	
}
