import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Refinement a set of candidates for constraints Y

public class fsqs {
	
	public static String fi1="loft7.10k.dat"; //cc41, t711, t89, 
	public static String fo1="fsqs.out"; //File da fusion
	
	
	
	public static double x[] = new double[12000];
	public static double y[] = new double[12000];
	
	public static int label1[] = new int[1000];
	public static int label2[] = new int[1000];
	
	
	
	public static int n;
	public static int nq=12; //200 pour art_i
	
public static void main(String[] args)  throws Exception 
	 {
	    
	    FileReader fr1 = new FileReader(fi1);
		BufferedReader br2=new BufferedReader(fr1);
		String s2;
		String tg1;
		
		  
		  int i2=0;n=0;
		  
		  while ((s2=br2.readLine())!=null)
		     { 			     		  
			     i2++;  
			     tg1=s2;
				 
		          tg1=tg1+" ";
		          //tg1= tg1.substring(2,tg1.length());
				 int k1=tg1.indexOf(" ");
				
				 String s1= tg1.substring(0,k1);
			     
				  double x1=  Double.parseDouble(s1);
					
					x[i2]=x1;
					
					tg1=tg1.substring(k1,tg1.length()); 
					tg1=tg1.trim();
					
	                tg1=tg1+" ";
								
					k1=tg1.indexOf(" ");
					
					s1= tg1.substring(0,k1);
				     
					double y1= Double.parseDouble(s1);
					
					y[i2]=y1;
					
					
					//System.out.print(p[i2]+" "+q[i2]+" "+w[i2]+"\n");
								        
		      }
		  n=i2;
		  fr1.close();
		  System.out.print("n= "+n+"\n");
		  int i;
		  
		  int label[] = new int[10000];
		  for (i=1;i<=n;i++)
			  label[i]=0; 
		  label[2]=1;
		  int dem=1;label1[dem]=2; int luu=0;
		  
		  while (dem<nq)
		  { double max=0;
			  for (i=1;i<=n;i++)
				if (label[i]==0)
				 {double min= Math.sqrt(((x[i]-x[label1[1]])*(x[i]-x[label1[1]]))+((y[i]-y[label1[1]])*(y[i]-y[label1[1]])));;
				// System.out.print("Min "+x[i]+" "+y[i]+" "+x[label1[1]]+" "+y[label1[1]]+" " + min+"\n");
				 
				  for (int j=2;j<=dem;j++)
				  {
					double kc = Math.sqrt(((x[i]-x[label1[j]])*(x[i]-x[label1[j]]))+((y[i]-y[label1[j]])*(y[i]-y[label1[j]])));
					if (kc<min)  min=kc;  
				  } 
				 if (min>max) 
					 {max=min;luu=i;}	 
				 } 
			  
			 dem++;  label1[dem]=luu; label[luu]=1;
		    } 
		  
		  //*************************Finish Refinement Process****************************
		  FileWriter fw;
			 try
			  {
				fw=new FileWriter(fo1);
			  } catch(IOException exc)
			   
			  { System.out.println("Ne pas ouverir la fichier!");
			    return;
			  }

			 for (i=1;i<=nq;i++)   
			  //if (kq3[i]<threshold)
			  {  
				  
				 fw.write(label1[i]+"\n");
				 System.out.print(label1[i]+" ");
			      
			  }
			 fw.close();System.out.println("C'est fini!");
		  
		  
	 } 
	
}
