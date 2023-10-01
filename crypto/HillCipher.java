package crypto;

public class HillCipher 
{
	private static char alpha[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int keyMatrix[][],plainText[][];

	private int size,i,j,k;
	
	void generateSquareMatrix(String key) throws Exception
	{
		size = (int) Math.sqrt(key.length());
		
		if((size*size) != key.length())
		{
			throw new Exception();
		}
		keyMatrix = new int[size][size];
		
		int k=0;
		for(i=0;i<size;i++)
		{
			for(j=0;j<size;j++)
			{
				keyMatrix[i][j] = indexOf(alpha,key.charAt(k++));
				
			}
		}
	}
	
	void generateBlock(String msg)
	{
		plainText = new int[1][size];
		for(i=0,k=0;i<size;i++)
		{
			plainText[0][i] = indexOf(alpha,msg.charAt(k++));
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
	
	private int[][] keyInverse(String key) throws Exception
	{
		generateSquareMatrix(key);
		int det = determinant(keyMatrix);
		int adj[][] = adjoint(keyMatrix);
		det = (det + 26) % 26;
		int mi = multiplicativeInverse(det,26) ;
		
		for(i=0;i<size;i++)
		{
			for(j=0;j<size;j++)
			{
				adj[i][j] *= mi;
				adj[i][j] = adj[i][j] % 26;
				adj[i][j] = (adj[i][j]+26)%26;
			}
		}
		
		return adj;
	}
	
	public int multiplicativeInverse(int b,int m) throws Exception
	{
		int q,a1,a2,a3,b1,b2,b3,t1,t2,t3;
		q  = 1;
		a1 = 1;
		a2 = 0;
		a3 = m;
		b1 = 0;
		b2 = 1;
		b3 = b;
				
		while(b3!=1 && b3!=0)
		{
			
			q=a3/b3;
			t1 = b1;
			t2 = b2;
			t3 = b3;
			b1 = a1 - q * b1;
			b2 = a2 - q * b2;
			b3 = a3 - q * b3;
			
			a1 = t1;
			a2 = t2;
			a3 = t3;
			
			
		}
		
		if(b3 == 0)
		{
			throw new Exception();
		}
		return (b2+26)%26;
	}
	
	private static int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] cofactorMatrix = new int[n][n];
        
        // Calculate the cofactor matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int minorDeterminant = calculateMinorDeterminant(matrix, i, j);
                cofactorMatrix[i][j] = minorDeterminant * ((i + j) % 2 == 0 ? 1 : -1);
            }
        }
        
        // Transpose the cofactor matrix to obtain the adjoint matrix
        int[][] adjointMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjointMatrix[j][i] = cofactorMatrix[i][j];
            }
        }
        
        return adjointMatrix;
    }
    
    private static int calculateMinorDeterminant(int[][] matrix, int rowToRemove, int colToRemove) 
    {
        int n = matrix.length;
        int[][] minorMatrix = new int[n - 1][n - 1];
        int minorRow = 0;
        
        for (int i = 0; i < n; i++) {
            if (i == rowToRemove) {
                continue;
            }
            int minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == colToRemove) {
                    continue;
                }
                minorMatrix[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        
        int minorDeterminant = determinant(minorMatrix);
        return minorDeterminant;
    }
    
    private String makeValid(String msg)
    {
    	for(int i=1;i<=msg.length()%size;i++)
    	{
    		msg += 'X';
    	}
    	return msg;
    }
	
    private static int determinant(int[][] matrix) 
    {
        int n = matrix.length;
        
        // Base case: If the matrix is 1x1, return the single element as the determinant.
        if (n == 1) {
            return matrix[0][0];
        }
        
        int det = 0;
        int sign = 1;
        
        // Iterate through the first row to expand along.
        for (int j = 0; j < n; j++) {
            int[][] submatrix = new int[n - 1][n - 1];
            
            // Create the (n-1)x(n-1) submatrix by excluding the first row and the j-th column.
            for (int i = 1; i < n; i++) {
                for (int k = 0, l = 0; k < n; k++) {
                    if (k == j) {
                        continue;
                    }
                    submatrix[i - 1][l++] = matrix[i][k];
                }
            }
            
            // Recursively calculate the determinant of the submatrix and sum it with the sign.
            det += sign * matrix[0][j] * determinant(submatrix);
            sign = -sign; // Alternate the sign for each element in the first row.
        }
        
        return det%26;
    }
 
	public String encrypt(String msg,String key) throws Exception
	{
		int temp;
		String eMsg = "";
		
		key = key.toUpperCase();
		
		generateSquareMatrix(key);
		msg = msg.toUpperCase();
		msg = makeValid(msg);
		
		for(int a=0;a<msg.length()-1;a+=size)
		{
			generateBlock(msg.substring(a));
			
			for(i=0;i<1;i++)
			{
				for(j=0;j<size;j++)
				{
					temp = 0;
					for(k=0;k<size;k++)
					{
						temp += plainText[i][k] * keyMatrix[k][j];
					}
					temp = temp % 26;
					eMsg += alpha[temp];
				}
			}
			
		}
		return eMsg;
	}
	public String decrypt(String msg,String key) throws Exception
	{
		int temp;
		String dMsg = "";
		msg = msg.toUpperCase();
		key = key.toUpperCase();
		
		int keyInv[][]=keyInverse(key);
	
		for(int a=0;a<msg.length();a+=size)
		{
			generateBlock(msg.substring(a));
			
			for(i=0;i<1;i++)
			{
				for(j=0;j<size;j++)
				{
					temp = 0;
					for(k=0;k<size;k++)
					{
						temp += plainText[i][k] * keyInv[k][j];
					}
					temp = temp % 26;
					dMsg += alpha[temp];
				}
			}
			
		}
		return dMsg;
	}
}
