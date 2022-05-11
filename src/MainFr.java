import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;



public class MainFr extends JFrame implements MouseListener {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JButton stbut;
		boolean play=false;
		static Timer tim;
		JTextField freq;
		JSlider vol;
		JPanel knobs,panfrq,panvol;
		JLabel tmpl;
		JComboBox<String> wave;
		Plot plt;
		
		public MainFr() throws HeadlessException {
			super("FreqGen");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setBackground(new Color(4,32,50));
			setResizable(false);
			
			this.setSize(300, 290);
			knobs = new JPanel();
			knobs.setLayout(new BoxLayout(knobs,BoxLayout.Y_AXIS));
			panfrq = new JPanel();
			panfrq.setLayout(new BoxLayout(panfrq,BoxLayout.PAGE_AXIS));
			panvol = new JPanel();
			panvol.setLayout(new BoxLayout(panvol,BoxLayout.PAGE_AXIS));
			

			stbut = new JButton("Start");
			freq = new JTextField("500");
			vol = new JSlider();
			stbut.addMouseListener(this);
			wave = new JComboBox<String>(new String[] {"Sine","Step","Trig"});
			plt = new Plot();
			
			
			tmpl = new JLabel("Frequency (Hz)");
			panfrq.add(tmpl);
			tmpl.setAlignmentX(Component.CENTER_ALIGNMENT);
			panfrq.add(freq);
			
			tmpl = new JLabel("Wave function");
			panfrq.add(tmpl);
			tmpl.setAlignmentX(Component.CENTER_ALIGNMENT);
			panfrq.add(wave);
			
			
			tmpl = new JLabel("Volume");
			panvol.add(tmpl);
			tmpl.setAlignmentX(Component.CENTER_ALIGNMENT);
			panvol.add(vol);
			panvol.add(stbut);
			stbut.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			
			
			knobs.add(panfrq);
			knobs.add(panvol);

			
			
			getContentPane().add(plt,BorderLayout.SOUTH);
			getContentPane().add(knobs,BorderLayout.NORTH);
			tmpl = new JLabel("Created by Anastasios Pikridas");
			getContentPane().add(tmpl,BorderLayout.LINE_END);
			tmpl.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setVisible(true);
			tim = new Timer(this);
		}
		

		public static void main(String[] args) throws InterruptedException {
			new MainFr();
			tim.start();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (play){
				stbut.setText("Start");
				play = false;
			}
			else{
				stbut.setText("Stop");
				play = true;
			}
			Timer.setPlay(play);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}

