package GUI;

import java.awt.event.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import javax.swing.*;
import sun.audio.*;
import java.io.*;
import java.io.IOException;

public class Sound {
	
	public static void main(String[]args){
		JFrame frame=new JFrame();
		frame.setSize(200,200);
		JButton button=new JButton("click me");
		frame.add(button);
		button.addActionListener(new AL());
		frame.show(true);
		
	}
	
	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e){
			music();
		}
	
		
	}
	public static void music(){
		AudioPlayer MGP=AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		sun.audio.ContinuousAudioDataStream loop=null;
		
		
		try{
		BGM = new AudioStream(new FileInputStream("gm.wav"));
		MD=BGM.getData();
		loop= new sun.audio.ContinuousAudioDataStream(MD);
		}catch(IOException error){}
		MGP.start(loop);
	}
	

}

