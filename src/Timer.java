import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;



public class Timer extends Thread {
	
	MainFr mf;
	static boolean pl = false;
    static SourceDataLine speak = null;
    static int numb;
    static byte[] data;
    int bufs = 4410;
	
	public Timer(MainFr c) {
		super();
		mf = c;
		AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, true);
		try {
			speak = AudioSystem.getSourceDataLine(format);
			speak.open(format);
			data = new byte[2*bufs];
			speak.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		setDaemon(true);
	}
	
	
	
	public static void setPlay(boolean play) {
		Timer.pl = play;
	}
	
	int tone(double t, int v, int f){
		int x;
		double nt,per;
		x = (int) Math.round((v*Math.sin(2*Math.PI*f*t)));
		if (mf.wave.getSelectedItem()=="Step"){
			if (x>0) x = (int) Math.round(v/Math.sqrt(2.0));
			else x = - (int) Math.round(v/Math.sqrt(2.0));
		}
		else if (mf.wave.getSelectedItem()=="Trig"){
			per = 1.0/f;
			nt = t%per;
			if (nt<per/2) x = 1;
			else x = -1;
			nt = nt%(per/2);
			if (nt<per/4) x = (int) (x*4*v*nt/per);
			else x = (int) (x*((-4*v*nt/per)+(2*v)));
		}
		return x;
	}



	public void run(){
		int x,i,v,freq;
		double t = 0;
		while(true){
			try {
				freq = Integer.parseInt(mf.freq.getText());
				v = mf.vol.getValue()*327;
				sleep(10);
				if ((pl)&&(freq>0)){
					for(i=0;i<2*bufs;i=i+2){
						x = tone(t,v,freq);
						data[i+1] = (byte)( x & 0xFF);
						data[i] = (byte)((x >> 8) & 0xFF);
						t+=(1.0/44100.0);
						mf.plt.newp(x);
					}
					speak.write(data, 0, 2*bufs);
				}
			} catch (InterruptedException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NumberFormatException e) {
			}
		}
	}
	
}
