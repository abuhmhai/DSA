import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

 public class lof {
	
	public static Double x[][] = new Double[21000][100];
	public static Double u[][] = new Double[21000][100];
	public static  int kq[] = new int[21000];
	public static int n;
	public static int m;
	public static int Minpts= 55;
	public static int teta=1; // soybean=3; iris =0;art1-6 =1
	public static double epsilon=0.1;
	public static Double c[][] = new Double[100][100];
	public static int voisine[] = new int[500];
	public static int voisin=0;
	public static Double LOF[] = new Double[21000];
	/*test1, eps = 43.3; 
	 * test2 = ; test3, test thu nghiem = ; 
	 * test4=  ; WINE 
	 * test5: IRIS eps = 0.42, 
	 * test6, eps = 1, statlog-heart
	 * test8: Du lieu phuc tap*/
	public static String fi="art1.inp";//file du lieu 
	public static String fo="art1.out"; //ghi cac outlieur chi dung cho LOF
	
	//luu giu du lieu cho lof
	//test81 Minpts = 30; test82 minpts =40; test 83 min pts =45; LOF>1.15, outlier = 1953;
											// test 84
	
	
	
	
	public static double distance(int i, int j)
	 {   /* Tinh khoang cach giua objet i et cluster c[j]*/
	  
		int j1; double s=0;
		for (j1=1;j1<=m;j1++)
			s=s+(x[i][j1]-c[j][j1])*(x[i][j1]-c[j][j1]);
		
		return Math.sqrt(s);
		
	 }
	
	public static double distance1(int i, int j)
	 {   /* Tinh khoang cach giua objet i et object j*/
	  
		int j1; double s=0;
		for (j1=1;j1<=m;j1++)
			s=s+(x[i][j1]-x[j][j1])*(x[i][j1]-x[j][j1]);
		return Math.sqrt(s);
		
	 }
	
	public static double k_distance(int i)
	 {   /* */
		Double tam[] = new Double[21000];
		for (int j=1; j<=n; j++)
			if (i!=j)
				tam[j]=distance1(i,j);
		
		double min=10000000;
		for (int v=1; v<=Minpts; v++) 
		{  int vitri=0;
		    min=10000000;
		    
		    for (int jj=1; jj<=n; jj++)
			if (jj!=i) 
			if (tam[jj]<min)		
			{
				
				min=tam[jj]; vitri=jj;
			}
		    	    
	 	 	tam[vitri]=10000000.0;
	  	}
		
		return min;
		
	 }
	
	public static int k_distancevoisine(int i)
	 {   /* Tra ra cac gia tri voisine cua i*/
	  
		double khoangcach=k_distance(i);
		voisin=0;
		for (int j=1; j<=n; j++)
		 if ((j!=i) & (distance1(i,j)<=khoangcach))
		  {
			 voisin++; voisine[voisin]=j;		 
		  }			
		return voisin;
	 }
	
	public static double reach_distance(int i, int j)
	 {   /* */
	  	
		return Math.max(k_distance(i), distance1(i,j));
		
	 }
	
	public static double lrd(int p)
	 {   /* */
	  
		int soluong=k_distancevoisine(p);
		double tg=0.0;
		 for (int i=1; i<=soluong;i++)
			 tg=tg+reach_distance(p,voisine[i]);	
		return soluong/tg;
		
	 }
	
	public static double lof(int p)
	 {   /* */
		int soluong=k_distancevoisine(p);
		int voisine1[] = new int[500];
		for (int o=1; o<=soluong;o++)
			voisine1[o]=voisine[o];
		double lrdp=lrd(p);
		double s=0.0;
		for (int o=1; o<=soluong;o++)
			s=s+lrd(voisine1[o]);		
		return s/(soluong*lrdp);
		
	 }
	
	public static void LOF()
	{
		int i;
		/*############## Commencer de SUPPRIMER LES BRUITES #####################*/
		  
		  
		  int dem=0;
		  
		  System.out.print("Bat dau LOF "+"\n");
		  for (i=1; i<=n;i++)
		   {
			  LOF[i]=lof(i); 
			  System.out.print(LOF[i]+"\n");
			  if (LOF[i]>1.15)
			  { dem++; kq[i]=0;}
			  else 
			  
				  kq[i]=1;
				  
		   }
		  
		  System.out.print("Tinh xong LOF!!!          Dem = "+dem+"\n");
		  
		  /*############### Finir de SUPPRIMER LES BRUITES ####################*/
		  
		  /*Inittialization le matrix U*/
		  int n1=0;
		  Double xx[][] = new Double[21000][100];
		  int j;
		  for (i=1; i<=n;i++)
			 			  
		   if (LOF[i]<=1.2)
		   {n1++; 
		    for (j=1; j<=m;j++)
			   xx[n1][j]=x[i][j];
		   } 
		  	
		
		
	}
	
	public static void main(String[] args)  throws Exception 
	 {
		
		System.out.print("LOF bienvenue:");
		 
		  FileReader fr = new FileReader(fi);
		  BufferedReader br=new BufferedReader(fr);
		  String s;
		  
		  
		  int i=0;
		  String tg1;
		  int k1;
		  String s1;
		  int j=0;
		  
		  while ((s=br.readLine())!=null)
		     { 
			  s=s.substring(0,s.length()-teta); /*avec pima*/  
			  s=s.replace(',',' ');
			   i++; tg1=s;j=0;
			   while (tg1.length()!=0)
				{
					
					tg1=tg1+" ";										
					k1=tg1.indexOf(" ");
					s1= tg1.substring(0,k1);
					
					j++;
					x[i][j]=Double.parseDouble(s1);					
					tg1=tg1.substring(k1,tg1.length()); tg1=tg1.trim();
					
				}		   			   
		       }
		  	   
		   n=i;	m=j;  /*Kich thuoc du lieu, n hang m la so chieu cua du lieu, tinh tu (1, 1)*/
		  
		   System.out.print("Kich thuoc du lieu "+n+"  "+m+"\n");
		    
		 //*******************************************Chi de ghi ket qua cho LOF 0/1
            LOF();
            FileWriter fw;
			 try
			  {
				fw=new FileWriter(fo);
			  } catch(IOException exc)
			   
			  { System.out.println("Ne pas ouverir la fichier!");
			    return;
			  }

			 for (i=1;i<=n;i++)   //Ghi cac outlieur cho LOF
			  {  
				  fw.write(kq[i]);
			      fw.write("\n");
			  }
			 fw.close();
			 //***********************************************
		  	  
	 } /*main*/

}/*classe*/
