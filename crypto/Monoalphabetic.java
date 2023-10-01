package crypto;

import java.util.LinkedHashSet;
import java.util.Set;

public class Monoalphabetic 
{
	private char alpha[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private char map[]=new char[26];
	private int index;
	
	private void generateKey(String key)
	{
		int i;
		Set<Character> charSet = new LinkedHashSet<>();
		for(i=0;i<key.length();i++)
			charSet.add(key.charAt(i));
		for(char ch='A';ch<='Z';ch++)
			charSet.add(ch);
		i=0;
		for(char ch : charSet)
		{
			map[i]=ch;
			i++;
		}
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
		key = key.toUpperCase();
		generateKey(key);
		
		
		String eMsg="";
		char ch;
		for(int i=0;i<plainText.length();i++)
		{
			ch = plainText.charAt(i);
			if(Character.isLowerCase(ch))
			{
				index = ch - 97;
				eMsg += Character.toLowerCase(map[index]);
			}
			else if(Character.isUpperCase(ch))
			{
				index = ch - 65;
				eMsg += map[index];
			}
			else
				eMsg += ch;
		}
		return eMsg;
	}
	public String decrypt(String cipherText,String key)
	{
		key = key.toUpperCase();
		generateKey(key);
		
		
		String dMsg="";
		char ch;
		for(int i=0;i<cipherText.length();i++)
		{
			ch = cipherText.charAt(i);
			if(Character.isLowerCase(ch))
			{
				index = indexOf(map,Character.toUpperCase(ch));
				dMsg += Character.toLowerCase(alpha[index]);
			}
			else if(Character.isUpperCase(ch))
			{
				index = indexOf(map,ch);
				dMsg += alpha[index];
			}
			else
				dMsg += ch;
		}
		return dMsg;
	}
}