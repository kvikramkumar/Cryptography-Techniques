package crypto;
import java.util.Arrays;

public class SingleColumnTransposition 
{
	private char text[][];
	private int row,col;
	
	private void generateEncryptMatrix(String msg,String key)
	{
		double t = (double)msg.length() / key.length();
		
		row = (int) Math.ceil(t);
		col = key.length();
		
		text = new char[row][col];
		
		int i,j,k=0;
		int temp=msg.length();
		for(i=temp;i<row*col;i++)
		{
			msg += '#';
		}
		for(i=0;i<row;i++)
		{
			for(j=0;j<col;j++)
			{
				text[i][j] = msg.charAt(k++);
				
			}
			
		}
		
	}
	
	private void generateDecryptMatrix(String msg,String key)
	{
		double t = (double)msg.length() / key.length();
		
		row = (int) Math.ceil(t);
		col = key.length();
		
		text = new char[row][col];
		
		int i,j,k=0;
		int temp=msg.length();
		for(i=temp;i<row*col;i++)
		{
			msg += '#';
		}
		for(i=0;i<col;i++)
		{
			for(j=0;j<row;j++)
			{
				text[j][i] = msg.charAt(k++);
			}
		}
		
	}
	
	public String encrypt(String msg,String key)
	{
		generateEncryptMatrix(msg,key);
		
		String eMsg = "";
		char k[] = key.toCharArray();
		Arrays.sort(k);
		int i,j,col_Index;
		for(i=0;i<k.length;i++)
		{
			col_Index = key.indexOf(k[i]);
			for(j=0;j<row;j++)
			{
				eMsg += text[j][col_Index];
			}
		}
		return eMsg;
	}
	public String decrypt(String msg,String key)
	{
		String dMsg = "",keyInverse="";
		generateDecryptMatrix(msg,key);
		int i,j,temp;
		
		char k[] = key.toCharArray();
		Arrays.sort(k);
		
		for(i=0;i<key.length();i++)
		{
			keyInverse += key.indexOf(k[i]) + 1;
		}
		
		k = keyInverse.toCharArray();
		Arrays.sort(k);
		
	
		for(i=0;i<row;i++)
		{
			for(j=0;j<col;j++)
			{
				temp = keyInverse.indexOf(k[j]);
				dMsg += text[i][temp];
			}
		}
		dMsg = dMsg.replace("#","");
		return dMsg;
	}

}
