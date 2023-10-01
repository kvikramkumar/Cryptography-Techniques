package crypto;

public class RailFence 
{
	public String encrypt(String msg,int depth)
	{
		String keyMatrix[][] = new String[depth][msg.length()];
		
		int row = 0;
		int col = 0;
		int cur = 0;
		int i,j;
		while(cur < msg.length())
		{
			while(row < depth && cur < msg.length())
			{
				keyMatrix[row++][col] = "" + msg.charAt(cur++);
			}
			row = Math.max(0,row - 2);
			
			while(row > 0 && cur < msg.length())
			{
				keyMatrix[row--][++col] = ""+ msg.charAt(cur++);
			}
			col++;
		}
		String eMsg = "";
		for(i=0;i<depth;i++)
		{
			for(j=0;j<msg.length();j++)
			{
				if(keyMatrix[i][j] != null)
					eMsg += keyMatrix[i][j];
			}
		}
		return eMsg;
	}
	public String decrypt(String msg,int depth)
	{
		String keyMatrix[][] = new String[depth][msg.length()];
		String dMsg = "";

		int row = 0;
		int col = 0;
		int cur = 0;
		int i,j;
	
		while(cur < msg.length())
		{
			while(row < depth && cur < msg.length())
			{
				keyMatrix[row++][col] = "*";
				cur++;
			}
			row = Math.max(0,row - 2);
			
			while(row > 0 && cur < msg.length())
			{
				keyMatrix[row--][++col] = "*";
				cur++;
			}
			col++;
		}
		

		cur=0;
		for(i=0;i<depth;i++)
		{
			for(j=0;j<msg.length() && cur<msg.length();j++)
			{
				if(keyMatrix[i][j] != null)
					keyMatrix[i][j] = ""+msg.charAt(cur++);
			}
		}

		for(i=0;i<msg.length();i++)
		{
			for(j=0;j<depth;j++)
			{
				if(keyMatrix[j][i]!=null)
					dMsg += keyMatrix[j][i];
			}
		}
		return dMsg;
	}

}
