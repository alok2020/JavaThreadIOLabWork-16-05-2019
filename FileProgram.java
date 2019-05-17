import java.io.*;


class CopyDataThread extends Thread{
	
	BufferedReader br;
	BufferedWriter bw;

	CopyDataThread(BufferedReader br, BufferedWriter bw){

		this.br = br;
		this.bw = bw;


	}

	@Override
	public void run(){

		try{


			int i = 1;
			String str ="";
			int j = 0;
			while((i = br.read()) != -1){

				if(j != 10){

					str = str + ((Character) ((char)i)).toString();
					j++;

				}else{
					j = 1;

					System.out.println("10 characters copied! {" + str + "}");
					Thread.sleep(5000);

					bw.write(str);
					str = ((Character)((char)i)).toString();


				}
				
			}
			//Writing remaining characters
			bw.write(str);

			br.close();
			bw.flush();
			bw.close();

		}
		catch(IOException  ioe){

			ioe.printStackTrace();
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}


	}

}




class  FileProgram{

	

	public static void main(String[] args) {

		try{

			FileReader fr = new FileReader("source.txt");		
			BufferedReader br = new BufferedReader(fr);

			FileWriter fw = new FileWriter("target.txt");
			BufferedWriter bw = new BufferedWriter(fw);



			CopyDataThread cdt = new CopyDataThread(br,bw);
			cdt.start();

		}catch(IOException ioe){

			ioe.printStackTrace();
		}	

		}
}