package nyan;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Neta extends JFrame{
	NetaPanel panel = new NetaPanel();
	public static void main(String[] args) throws IOException{
		BufferedReader br =
			new BufferedReader(new InputStreamReader(System.in));
		System.out.print("PLEASE INPUT nyan> ");
		String str = br.readLine();

		Neta frame = new Neta(str);
		frame.disp();
	}

	public Neta(String str){
		setSize(500, 500);
		panel.setTxt(str);
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}

	public void disp(){
		byte[][] bin = panel.getBin();
		for(int y = 0; y < bin.length; y++){
			for(int x = 0; x < bin[0].length; x++){
				if(bin[y][x] == 1){
					System.out.print("@");
				}else{
					System.out.print("O");
				}
			}
			System.out.println();
		}
	}
}

class NetaPanel extends JPanel{
	final Font font = new Font(Font.DIALOG, Font.BOLD, 40);
	String text;
	int w = 300;
	int h = 300;
	byte[][] chImg = new byte[h][w];
	BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);;
	Graphics2D off;
	public NetaPanel(){
		off = img.createGraphics();
		off.setFont(font);
		off.drawString("にゃんにゃん", 10, 100);
		chImg = makeArray(img);
	}

	public void setTxt(String str){
		text = str;
	}

	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
	}

	public BufferedImage getImg(){
		return img;
	}

	public byte[][] getBin(){
		/*
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
					System.out.print(chImg[y][x]);
			}
			System.out.println();
		}
		*/
		return chImg;
	}

	public byte[][] makeArray(BufferedImage img){
		byte[][] bin = new byte[img.getHeight()][img.getWidth()];
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				if(img.getRGB(x ,y) == -1){
					bin[y][x] = 1;
				}
			}
		}
		return bin;
	}
}
