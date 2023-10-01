package crypto;
import java.util.LinkedHashSet;
public class Playfair 
{
	private char keyMatrix[][] = new char[5][5];
	LinkedHashSet<Character> k = new LinkedHashSet<>();
	
	private void generateKeyMatrix(String key)
	{
		int i,j;
		
		key = key.toUpperCase();
		for(i=0;i<key.length();i++)
		{
			k.add(key.charAt(i));
		}
		for(char ch='A';ch<='Z';ch++)
		{
			k.add(ch);
		}
		k.remove('J');
		
		i=0; j=0;
		for (Character ch : k)
		{
			keyMatrix[i][j] = ch;
			j++;
			if(j==5)
			{
				i++;
				j=0;
			}
		}
	}
	
	private String addBogusCharacter(String plainText)
	{
		for(int i=0;i<plainText.length()-1;i+=2)
		{
			if(plainText.charAt(i) == plainText.charAt(i+1))
			{
				plainText = plainText.substring(0,i+1) + 'X' + plainText.substring(i+1);
			}
		}
		return plainText;
	}
	
	String makeValid(String plainText)
	{
		plainText = plainText.toUpperCase();
		
		plainText.replace('J','I');
		
		plainText = addBogusCharacter(plainText);
		
		if(plainText.length() % 2 == 1)
			plainText += 'Z';
		
		return plainText;
	}
	
	private int[] findIndex(char ch)
	{
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(keyMatrix[i][j] == ch)
					return new int[] {i,j};
			}
		}
		return null;
	}
	
	public String encrypt(String plainText,String key)
	{
		generateKeyMatrix(key);
		
		plainText = makeValid(plainText);
		
		int r1=0,r2=0,c1=0,c2=0;
		
		String eMsg = "";
		
		for(int i=0;i<plainText.length()-1;i+=2)
		{
			 int arr[] = findIndex(plainText.charAt(i));
			 if(arr != null)
			 {
				 r1 = arr[0];
				 c1 = arr[1];
			 }
			 
			 arr = findIndex(plainText.charAt(i+1));
			 if(arr != null)
			 {
				 r2 = arr[0];
				 c2 = arr[1];
			 }
			 
			 if(r1 == r2)
			 {
				 eMsg += keyMatrix[r1][(c1+1)%5];
				 eMsg += keyMatrix[r1][(c2+1)%5];
			 }
			 else if(c1 == c2)
			 {
				 eMsg += keyMatrix[(r1+1)%5][c1];
				 eMsg += keyMatrix[(r2+1)%5][c1];
			 }
			 else
			 {
				 eMsg += keyMatrix[r1][c2];
				 eMsg += keyMatrix[r2][c1];
			 }
		}
		
		return eMsg;
	}
	
	public String decrypt(String cipherText,String key)
	{
		generateKeyMatrix(key);
		
		int r1=0,r2=0,c1=0,c2=0;
		
		String dMsg = "";
		
		for(int i=0;i<cipherText.length()-1;i+=2)
		{
			 int arr[] = findIndex(cipherText.charAt(i));
			 if(arr != null)
			 {
				 r1 = arr[0];
				 c1 = arr[1];
			 }
			 
			 arr = findIndex(cipherText.charAt(i+1));
			 if(arr != null)
			 {
				 r2 = arr[0];
				 c2 = arr[1];
			 }
			 
			 if(r1 == r2)
			 {
				 dMsg += keyMatrix[r1][(c1-1+5)%5];
				 dMsg += keyMatrix[r1][(c2-1+5)%5];
			 }
			 else if(c1 == c2)
			 {
				 dMsg += keyMatrix[(r1-1+5)%5][c1];
				 dMsg += keyMatrix[(r2-1+5)%5][c1];
			 }
			 else
			 {
				 dMsg += keyMatrix[r1][c2];
				 dMsg += keyMatrix[r2][c1];
			 }
		}
		
		return dMsg;
		
	}
}
