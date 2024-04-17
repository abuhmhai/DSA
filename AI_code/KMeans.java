import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// K-Means normal, 
// 

public class KMeans {
	public static Double a[][] = new Double[2200][500];//array for data set
	public static  int nhan[] = new int[10000]; //array for result
	public static  int goc[] = new int[10000]; //array for result
	public static  int nhancluster[] = new int[200];
	public static  double xx[] = new double[18000]; //array for result
	public static  int seedcluster[][] = new int[200][3]; 
	public static int n;
	public static int m;
	public static double total=0.0;
	public static int numberofcluster=7;
	public static int numberoftry=50;
	public static Double c[][] = new Double[2000][500]; //array for the centers
	
	
	public static String fi1="data/image.inp"; 
	public static String fi2="data/image_goc.inp";
	public static String fo1="data/kq.out";
	
	public static double distance11(int i, int j)
	 {   /* Tinh khoang cach giua objet i et cluster c[j]*/
	  
		int j1 ; double s=0;
		for (j1=1;j1<=m;j1++)
			s=s+(a[i][j1]-c[j][j1])*(a[i][j1]-c[j][j1]);
		
		return Math.sqrt(s);
		
	 }
	
	
	
	
	public static double distance(int i, int j)
	 {   /* Tinh khoang cach giua objet i et cluster c[j]*/
	  
		
		
		return distance11(i, j);
		
	 }
	
	
	public static int min(int i)
	 {   /*Finding the cluster for i*/	  
		int luu=1; double min1=distance(i, 1);
		
		for (int k=2; k<=numberofcluster; k++)
		  {double tg=distance(i, k);
		   
		    if (min1>tg)
		      {luu=k; min1=tg;}	
		  }	
		//System.out.print("i= "+i+" "+luu+"\n");
		return luu;
		
	 }
	
	public static double rand_index_measure() 
	 {    
		Double tong=0.0;
		
			
		for (int i=1;i<n;i++)
			for (int j=i+1;j<=n;j++)
			{
				if ((goc[i]==goc[j]) & (nhan[i]==nhan[j]))
					tong++;
				
				if ((goc[i]!=goc[j]) & (nhan[i]!=nhan[j]))
					tong++;
				
			}	
		   
		 Double reussite=0.00;
		 reussite= (tong * 2)/(n*(n-1));
		 reussite = reussite * 100;			
		
		 System.out.print("% Accuracy of Rand Index = " + reussite);
	  	System.out.print("\n");
		return reussite;
		
	 }
	
	
	public static double distance1(int i, int j)
	 {   
		int j1; double s=0;
		for (j1=1;j1<=m;j1++)
			s=s+(a[i][j1]-a[j][j1])*(a[i][j1]-a[j][j1]);
		
		return Math.sqrt(s);
		
	 }
	
	public static double intra_measure() 
	 {    
		Double im=0.0; int dem=0;
			//int k=79;		
		for (int t=1; t<=numberofcluster;t++)
		   {
			   dem=0;Double s1=0.0;
			   for (int i=1;i<=n;i++)
			   {
				if (nhan[i]==t)
					dem++;
				for (int j=i+1;j<=n;j++)
		         if ((nhan[i]==t) & (nhan[j]==t))
		         {
		        	 s1=s1+distance1(i,j);
		        	// System.out.print(s1+" ");
		         }
			   }	
			   im=im+s1/(dem*dem);
		   }
		  
		System.out.print("dem = " + dem);
		// System.out.print("\n");
		return im/numberofcluster;
		
	 }
	
	public static double CiCj(int ci, int cj) 
	 {    
		Double im=0.0; Double s1=0.0;
					
		
		int dem1=0;int dem2=0;
			   for (int i=1;i<=n;i++)
			   {
				   if (nhan[i]==ci)
						dem1++;
				   if (nhan[i]==cj)
						dem2++;
				if (nhan[i]==ci)
				for (int j=1;j<=n;j++)
					
				{ 	
		         if (nhan[j]==cj)
		         {
		        	 s1=s1+distance1(i,j);
		        	 
		         }
				}
			   
		       }
			   im=im+s1/(dem1*dem2);
			   //System.out.println(ci+ " "+cj+" " + im);
		return im;
		
	 }
	
	
	
	public static double inter_measure() 	
	 {    
		Double im=0.0; 
					
		for (int i=1; i<numberofcluster;i++)
			for (int j=i+1; j<=numberofcluster;j++)
		      im=im+CiCj(i,j);
		
		// System.out.print("% Accuracy of Intra_measure = " + im);
		// System.out.print("\n");
		return (2*im)/(numberofcluster*numberofcluster);
		
	 }
	
	
	
	
	public static void main(String[] args) throws Exception
	{
	
	 System.out.print("K_means algorithme bienvenue:");
	 
	  FileReader fr = new FileReader(fi1);
	  BufferedReader br=new BufferedReader(fr);
	  
	  FileReader fr2 = new FileReader(fi2);
	  BufferedReader br2=new BufferedReader(fr2);
	  
	  String s;
	  FileWriter fw1;
	  
	
	  
	 
	  
	  
	  
	  int i=0;
	  String tg1;
	  int k1;
	  String s1;
	  int j=0;
	  
	  while ((s=br.readLine())!=null)
	     { 
		 
		  s=s.replace(',',' ');
		  tg1=s;
		   i++; j=0;
		   while (tg1.length()!=0)
			{
				
				tg1=tg1+" ";
				
				k1=tg1.indexOf(" ");
				
				s1= tg1.substring(0,k1);
				
				j++;
				
				a[i][j]=Double.parseDouble(s1);
								
				tg1=tg1.substring(k1,tg1.length()); tg1=tg1.trim();
				
			}		   
		   
	       }
	  	   
	  n=i;	m=j;  
	  
	  System.out.print("Size of data set (number of objects n and number of attributes m): "+n+"  "+m+"\n");
	    
	  i=0;
	  while ((s=br2.readLine())!=null)
	     { 
		 
		  s=s.replace(',',' ');
		   i++; tg1=s;j=0;
		   while (tg1.length()!=0)
			{
				
				tg1=tg1+" ";
				
				k1=tg1.indexOf(" ");
				
				s1= tg1.substring(0,k1);
				int y2= (int) Double.parseDouble(s1);
				
				goc[i]=y2;						
				tg1=tg1.substring(k1,tg1.length()); tg1=tg1.trim();
				
			}		   
		   
	       }
	  fr2.close();
	  
	  double total1=0.0;
	  double total2=0.0;
	  
for (int it=1; it<=numberoftry; it++)
 {	
	  /*Randomly initialize k centers  */
	  
	  int nhan1[] = new int[21000];  
	  for (i=1;i<=n;i++)
		  nhan1[i]=0;
	  
	  int i1; 
	  for (j=1;j<=numberofcluster;j++)
	   {do
		{
		 double snn=Math.random();
	     i1=(int)(snn*n);
	    }
	    while ((i1==0) || (nhan1[i1]==1));
	  
	   //System.out.print(i1+"\n");	   
	   for (int t=1;t<=m;t++)
		   c[j][t]=a[i1][t];	   
	   nhan1[i1]=1;
	   }
	  
	  
	 
	  for (i=1;i<=n;i++)
		  nhan[i]=0;
	  int flag=1;
	  double of=0.0;double cu=0.0;
	  
	  //for (int vv=1;vv<=10;vv++)
	  while (flag==1)
	  {
		for (i=1;i<=n;i++)
		 {nhan[i]=min(i);  
		 //System.out.print(nhan[i]+" ");
		 }
		 of=0.0;
		
		for (j=1;j<=numberofcluster;j++)
	    {
	       for (i=1;i<=n;i++)
	         {  if (nhan[i]==j)
	           of=of+distance(i,j)*distance(i,j);} //distance between object i and center j
	    }
		
		if (cu!=of)
			cu=of;
		else
			flag=0;
		//System.out.print("of = "+of+"\n");	
		//estimate the centers
		
		 for (i=1; i<=numberofcluster; i++)
			  for (int t=1;t<=m;t++)
				   c[i][t]=0.0; 
		
		for (i=1; i<=numberofcluster; i++)
		{   int dem=0;
			for (j=1; j<=n; j++)
			 if (nhan[j]==i)
			 {   dem++;
			     for (int t=1;t<=m;t++)
				   c[i][t]=c[i][t]+a[j][t];
			 }		 
			for (int t=1;t<=m;t++)
				  c[i][t]=c[i][t]/dem;
		 }		
	  } //while 
  
	  //double v= intra_measure();
	  //double w= inter_measure();
	  double z= rand_index_measure();
	  
	 // total=total+v;
	  //total1=total1+w;
	  total2=total2+z;
	  
	  xx[it]=z;
 } //for it	  
	  
	  
	  double tong=0;
	   for (int u=1; u<=numberoftry;u++)
	    	tong=tong+xx[u];
	    tong=tong/numberoftry;
	   
	    double et=0;
	      for (int u=1; u<=numberoftry;u++)
		   et=et+(xx[u]-tong)*(xx[u]-tong);
	     et=et/numberoftry;
	     et=Math.sqrt(et);
	     
	     System.out.print("\n"+"% Accuracy= "+total2/numberoftry+"\n");
	     System.out.println("L'ecart-type = " +et+"\n" );
	     FileWriter fw;
		 try
		  {
			fw1=new FileWriter(fo1);
		  } catch(IOException exc)
		   
		  { System.out.println("Ne pas ouverir la fichier!");
		    return;
		  }
	     
		  //fw1.write("\n"+"% Accuracy (intra cluster)= "+total/numberoftry+"\n");
		   //  fw1.write("L'ecart-type = " +et+"\n" );
		    // fw1.write("\n"+"% Accuracy (inter cluster)= "+total1/numberoftry+"\n");
		     fw1.write("\n"+"% Accuracy (Rand Index)= "+total2/numberoftry+"\n");
	     fw1.close();
	}/*main*/

}/*class*/

