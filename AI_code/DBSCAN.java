
import java.io.*;

public class DBSCAN{

	/**
	 * @param args
	 */
	public static String a[] = new String[21000];
	public static int minpts=4;
	public static int bru=0;
	public static int n;
	public static double eps = 0.4;
	public static int teta = 0;/*soybean = 3; iris =0; art1,2,3,4,5,6 = 1*/
	public static int nhan[]=new int[21000];
	public static  int goc[] = new int[18000]; //array for result
	/*test1,  
	 * test2 = ; test3 = ; 
	 * test4=  ; WINE 
	 * 
	 * test8 = 6.1; art3 = 0.05; art1=0.075; art2=0.05; art4 = 1.12; 
	   art5 = 0.072; Art6 = 0.5; iris = 0.42, soybean = 3.5; Thyroid = 4.5*/
	 
	public static String fi="data/iris.inp"; 
	public static String fi1="data/iris_goc.inp";
	public static String fo="dbscan.out";
	
	
	public static double rand_index_measure() 
	 {    
		Double tong=0.0;
		
			
		for (int i=1;i<=n-1;i++)
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
	
	public static Double similarite(int i, int j)
	{
		Double s=0.0;
		int k1;
		int k2;
		String s1;
		String s2;
		String tg1;
		String tg2;
		tg1=a[i];
		tg2=a[j];
		
		/*System.out.println("i, j = "+i+" "+j);*/
		while (tg1.length()!=0)
		{
			
			tg1=tg1+" ";
			tg2=tg2+" ";
			
			k1=tg1.indexOf(" ");
			k2=tg2.indexOf(" ");
			
			
			s1= tg1.substring(0,k1);
			s2= tg2.substring(0,k2);
			
			s=s+(Double.parseDouble(s1)-Double.parseDouble(s2))*(Double.parseDouble(s1)-Double.parseDouble(s2));
			
			
			tg1=tg1.substring(k1,tg1.length()); tg1=tg1.trim();
			tg2=tg2.substring(k2,tg2.length()); tg2=tg2.trim();
			 
		}
		/*System.out.println(Math.sqrt(s)+"\n");*/
		return Math.sqrt(s);
	}
	
	public static void main(String[] args) throws Exception 
	{
	  FileReader fr = new FileReader(fi);
	  BufferedReader br=new BufferedReader(fr);
	  String s;
	  FileWriter fw;
	
	  
	  
	  
	  try
	  {
		fw=new FileWriter(fo);
	  } catch(IOException exc)
	  
	  { System.out.println("Ne pas ouverir la fichier!");
	    return;
	  }
	  
	  
	  int i=0;
	  
	  while ((s=br.readLine())!=null)
	     { 
		  s=s.substring(0,s.length()-teta); /*Avec soybean*/
		   s=s.replace(',',' ');
		   /*System.out.println(s);*/		  
		   i++; a[i]=s;
		  
		   /* Ghi ra file*/
		   s=s+"\n";
		   
	      }
	  	   n=i;	
	  	   
	  	 
	  	 System.out.println("Nombre des objets dans la donnée = "+n);
	     /*calculer la similarit� entre les objets*/
	  	 System.out.println("Epsilon: "+eps); 
	  	
	  	 FileReader fr1 = new FileReader(fi1);
		  BufferedReader br1=new BufferedReader(fr1);
		  String tg1;
		  int k1;
		  String s1;
		  int j=0; 
	  	 
	  	 
	  	i=0;
		  while ((s=br1.readLine())!=null)
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
		  fr1.close();
	  	 
	     /*L'algorithm DBSCAN*/
	  	 
	  	 bru=0;
	  	  
	  	  for (i=1; i<=n;i++)
	  	    {
	  		  nhan[i]=0;
	  		}
	  	  
	  	  int socluster=0;
	  	  
	  	  for (i=1;i<=n;i++)
	  		 if (nhan[i]==0)
	  		   {
	  			 /*Tim cac phan tu hang xom cua */  
	  			  int dau=1;
	  			  int cuoi=1;
	  			  int mangtg[]=new int[21000];
	  			  mangtg[dau]=i;
	  			 
	  			  while (dau<=cuoi)
	  			  {
	  			  int  soluonghangxom=0;
	  			  int manghangxom[]=new int[21000];
	  			  
	  			  
	  			  for ( j=1; j<=n;j++)
	  				   if ((mangtg[dau]!=j) & (nhan[j]==0)) 
	  				   {
	  					   /*System.out.println("Tinh cho "+mangtg[dau]+" va "+j);*/
	  					   /*System.out.println("La mesure de la similarit� "+similarit�(j,mangtg[dau]));*/
	  					   if (similarite(mangtg[dau],j)<=eps) 
	  				   {
	  					soluonghangxom++;
	  					manghangxom[soluonghangxom]=j;
	  				   }
	  				   }
	  			
	  			   if (soluonghangxom>=minpts)
	  			   {
	  				/* System.out.println("So luong hang xom cua "+mangtg[dau]+"la "+ soluonghangxom);*/	   
	  				   for (int k=1; k<=soluonghangxom;k++)
	  				     {
	  					   int dem=0; for ( k1=1; k1<=cuoi;k1++)
	  						          if (mangtg[k1]==manghangxom[k])
	  						        	 dem++;
	  				    	if (dem==0)
	  					      {cuoi++; mangtg[cuoi]=manghangxom[k];}
   	  				     }
	  			     }
	  			    dau++;
	  			   }/*while*/
	  			  
	  			/*System.out.println("Cuoi= "+cuoi);*/ 
	  			  if (cuoi>5)
	  			  {	  
	  				  socluster++;
	  				  for (int k2=1; k2<=cuoi; k2++)  
	  			      nhan[mangtg[k2]]=socluster;
	  				
	  			  }
	  		   }/*if nhani*/
	  	   
	  	 
	  	for (int k3=1; k3<=n; k3++)
		   {
		 System.out.print(nhan[k3]);
		 fw.write(nhan[k3]+"\n");
		    if (nhan[k3]==0)
		    	{bru++;nhan[k3]=2;}
		   } 
	  	System.out.print("\n");
	  	double u=rand_index_measure() ;
	  	 System.out.println("Nombre des clusters: "+socluster);
	  	   
	  	System.out.print("\n");
	  	  System.out.print("Nombre des bruites " + bru);
	  	   fw.close();
	  	  
	}/* main*/
}



