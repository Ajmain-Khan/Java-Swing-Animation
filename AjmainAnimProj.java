/*********************************************************************************************************************************************
 * Title: Deadmau5 Ghosts 'n Stuff Animation
 * Date: 2018-04-27
 * By: Ajmain K.
 * Description: An animation of the famous DJ Deadmau5 with several elements such as music (Ghosts 'n Stuff), audio visualizer 
 *		with randomly generated colors, beating speakers, etc.
 *********************************************************************************************************************************************/
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;
import java.awt.*;

public class AjmainAnimProj
{ 
	public static void main(String args[]) throws Exception 
	{ 
		FunGUI frame = new FunGUI();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.loop();
	}
}


@SuppressWarnings("serial")   
class FunGUI extends JFrame
{
	private int frame = 1;
	private int counter = 1;
	final int WIDTH = 1250, HEIGHT = 980;

	BufferedImage buffer;
	Image crowd = Toolkit.getDefaultToolkit().getImage("Crowd.PNG");
	Image crowd2 = Toolkit.getDefaultToolkit().getImage("LilCrowd.PNG");
	public static final Color DEEP_PURP = new Color(0x43, 0x1, 0x58);
	public static final Color BG = new Color(0x15, 0x0, 0x22);
	//Sets of variables to generate random values for color
	Random rand = new Random();
	float a = rand.nextFloat();
	float e = rand.nextFloat();
	float x = rand.nextFloat();

	float c = rand.nextFloat();
	float y = rand.nextFloat();
	float m = rand.nextFloat();

	float q = rand.nextFloat();
	float s = rand.nextFloat();
	float t = rand.nextFloat();

	float f = rand.nextFloat();
	float o = rand.nextFloat();
	float p = rand.nextFloat();

	float j = rand.nextFloat();
	float l = rand.nextFloat() /2f;
	float k = rand.nextFloat() /2f;

	float r = rand.nextFloat();
	float g = rand.nextFloat() / 2f;
	float b = rand.nextFloat() / 2f;

	float z = rand.nextFloat();
	float i = rand.nextFloat();
	float u = rand.nextFloat();

	float ac = rand.nextFloat();
	float ab = rand.nextFloat();
	float ad = rand.nextFloat();

	float bc = rand.nextFloat();
	float bb = rand.nextFloat();
	float bd = rand.nextFloat();

	float cc = rand.nextFloat();
	float cb = rand.nextFloat();
	float cd = rand.nextFloat();

	float w = rand.nextFloat()/4f;
	float wa = rand.nextFloat()/4f;
	float wb = rand.nextFloat()/4f;

	// Sets of random Color method calls //
	Color randomColor = new Color(r, g, b);
	Color randomColor2 = new Color(c, y, m);
	Color randomColor3 = new Color(a, e, x);
	Color randomColor4 = new Color(q, s, t);
	Color randomColor5 = new Color(f, o, p);
	Color randomColor6 = new Color(j, l, k);
	Color randomColor7 = new Color(z, i, u);
	Color randomColor8 = new Color(ac, ab, ad);
	Color randomColor9 = new Color(bc, bb, bd);
	Color randomColor10 = new Color(cc, cb, cd);
	Color randomColorX = new Color(w, wa, wb);


	public void play(String song)   
	{
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(song));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception ex) {
		}
	}


	public FunGUI()
	{
		super ("Ghosts 'n Stuff - Ajmain");
		buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		JPanel pane = (JPanel) getContentPane(); 
		pane.setDoubleBuffered(true);
		pane.setLayout(new BorderLayout(10,10));
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setIgnoreRepaint(true);
	}

	// Buffers drawings to memory //
	public void drawBuffer()
	{
		Graphics2D b = buffer.createGraphics();
		BGLayer(b);
		animLayer(b);
		b.dispose();
	}

	// Draws to screen, from memory //
	public void drawScreen()
	{
		Graphics2D g = (Graphics2D)this.getGraphics();

		g.drawImage(buffer,0,0,this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}


	/*******************************
	 ******** Animation LOOP *******
	 *******************************/
	public void loop()
	{
		play("Ghosts.wav");

		while(true)
		{
			try
			{
				drawBuffer();
				drawScreen();
				Thread.sleep(120);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}    
		}
	}


	/**********************************************
	 *************** Layer Methods ****************
	 **********************************************/
	void BGLayer(Graphics2D b) {
		rectangle(b, true, BG, 0, 0, WIDTH, HEIGHT);
	}

	void animLayer(Graphics2D b) {
		if (frame == 1 && counter < 50000) {
			// Animate Ears to wiggle //
			headFrame(b, 0, 10);
			// Randomly colors eyes & mouth //
			facialDetail(b, randomColor);

			// Randomly colors each rectangle from visualizer & switches places with other columns to imitate sound variance //
			aV1(b, randomColor,0,0);
			aV2(b, randomColor2,0,0);
			aV3(b, randomColor3,0,0);
			aV4(b, randomColor4,0,0);
			aV5(b, randomColor5,0,0);
			aV6(b, randomColor6,0,0);
			aV7(b, randomColor7,0,0);
			aV8(b, randomColor8,0,0);
			aV9(b, randomColor9,0,0);
			aV10(b, randomColor10,0,0);
			aV11(b, randomColor,0,0);
			aV12(b, randomColor2,0,0);
			aV13(b, randomColor3,0,0);
			aV14(b, randomColor4,0,0);
			aV15(b, randomColor5,0,0);
			aV16(b, randomColor6,0,0);

			// Bouncing crowd //
			b.drawImage(crowd2, 170, 480, null);
			b.drawImage(crowd, 50, 495, null);

			// Bobbing speakers //
			speakers(b,0,0);
			// Vibrating drivers //
			leftTeeter(b, 2, 2);
			leftMid(b, 0, 0);
			leftLow(b, 2, 2);
			rightTeeter(b, 2, -2);
			rightMid(b, 0, 0);
			rightLow(b, -2, 2);

			// each frame changes colors or positions to produce smooth animations //
		} else if (frame == 2 && counter < 50000) {
			headFrame(b,0,0);
			facialDetail(b, randomColor2);

			aV1(b, randomColor3,0,0);
			aV2(b, randomColor,40,0); // Column 2 switches places with column 4 for one frame
			aV3(b, randomColor2,0,0);
			aV4(b, randomColor10,-40,0); // Column 4 switches places with column 2 for one frame
			aV5(b, randomColor5,40,0);
			aV6(b, randomColor8,0,0);
			aV7(b, randomColor,-40,0);
			aV8(b, randomColor9,0,0);
			aV9(b, randomColor6,40,0);
			aV10(b, randomColor10,-40,0);
			aV11(b, randomColor4,-40,0);
			aV12(b, randomColor2,40,0);
			aV13(b, randomColor8,40,0);
			aV14(b, randomColor6,-40,0);
			aV15(b, randomColor5,-40,0);
			aV16(b, randomColor3,0,0);

			b.drawImage(crowd2, 170, 460, null);
			b.drawImage(crowd, 50, 515, null);

			speakers(b,8,8);

			leftTeeter(b, 2, 2);
			leftMid(b, 0, 0);
			leftLow(b, 2, 2);
			rightTeeter(b, 2, -2);
			rightMid(b, 0, 0);
			rightLow(b, -2, 2);

		} else if (frame == 3 && counter < 50000) {
			headFrame(b,0,20);
			facialDetail(b, randomColor3);

			aV1(b, randomColor10,0,0);
			aV2(b, randomColor2,40,0);
			aV3(b, randomColor7,0,0);
			aV4(b, randomColor5,-40,0);
			aV5(b, randomColor8,40,0);
			aV6(b, randomColor10,0,0);
			aV7(b, randomColor,-40,0);
			aV8(b, randomColor3,0,0);
			aV9(b, randomColor6,40,0);
			aV10(b, randomColor4,-40,0);
			aV11(b, randomColor10,-40,0);
			aV12(b, randomColor,40,0);
			aV13(b, randomColor8,40,0);
			aV14(b, randomColor5,-40,0);
			aV15(b, randomColor6,-40,0);
			aV16(b, randomColor2,0,0);

			b.drawImage(crowd2, 170, 460, null);
			b.drawImage(crowd, 50, 515, null);

			speakers(b,0,0);

			leftTeeter(b, -2, -2);
			leftMid(b, 1, 1);
			leftLow(b, -2, -2);
			rightTeeter(b, -2, 2);
			rightMid(b, -1, -1);
			rightLow(b, 2, -2);

		} else if (frame == 4 && counter < 50000) {
			headFrame(b,0,0);
			facialDetail(b, randomColor4);

			aV1(b, randomColor7,0,0);
			aV2(b, randomColor5,40,0);
			aV3(b, randomColor2,0,0);
			aV4(b, randomColor10,-40,0);
			aV5(b, randomColor3,40,0);
			aV6(b, randomColor8,40,0);
			aV7(b, randomColor,-40,0);
			aV8(b, randomColor10,40,0);
			aV9(b, randomColor6,40,0);
			aV10(b, randomColor,-40,0);
			aV11(b, randomColor2,-40,0);
			aV12(b, randomColor9,40,0);
			aV13(b, randomColor3,40,0);
			aV14(b, randomColor6,-40,0);
			aV15(b, randomColor,-40,0);
			aV16(b, randomColor5,0,0);

			b.drawImage(crowd2, 160, 460, null);
			b.drawImage(crowd, 60, 515, null);

			speakers(b,0,0);

			leftTeeter(b, 0, 0);
			leftMid(b, 0, 0);
			leftLow(b, 0, 0);
			rightTeeter(b, 0, 0);
			rightMid(b, 0,0);
			rightLow(b, 0,0);

		} else if (frame == 5 && counter < 50000) {
			headFrame(b,0,0);
			facialDetail(b, randomColor5);

			aV1(b, randomColor2,0,0);
			aV2(b, randomColor,40,0);
			aV3(b, randomColor3,0,0);
			aV4(b, randomColor3,-40,0);
			aV5(b, randomColor5,40,0);
			aV6(b, randomColor8,0,0);
			aV7(b, randomColor10,-40,0);
			aV8(b, randomColor9,40,0);
			aV9(b, randomColor6,40,0);
			aV10(b, randomColor10,-40,0);
			aV11(b, randomColor5,-40,0);
			aV12(b, randomColor6,40,0);
			aV13(b, randomColor,40,0);
			aV14(b, randomColor6,-40,0);
			aV15(b, randomColor7,-40,0);
			aV16(b, randomColor4,0,0);

			b.drawImage(crowd2, 160, 460, null);
			b.drawImage(crowd, 60, 515, null);

			speakers(b,15,15);

			leftTeeter(b, -3, -3);
			leftMid(b, -3, -3);
			leftLow(b, -6, -6);
			rightTeeter(b, -3, 3);
			rightMid(b, -3, -3);
			rightLow(b, 6, -6);

		} else if (frame == 6 && counter < 50000) {
			headFrame(b,0,0);
			facialDetail(b, randomColor6);

			aV1(b, randomColor3,0,0);
			aV2(b, randomColor,0,0);
			aV3(b, randomColor2,60,0);
			aV4(b, randomColor10,0,0);
			aV5(b, randomColor5,0,0);
			aV6(b, randomColor8,40,0);
			aV7(b, randomColor,0,0);
			aV8(b, randomColor9,40,0);
			aV9(b, randomColor6,0,0);
			aV10(b, randomColor10,-40,0);
			aV11(b, randomColor4,0,0);
			aV12(b, randomColor2,0,0);
			aV13(b, randomColor8,0,0);
			aV14(b, randomColor6,0,0);
			aV15(b, randomColor5,0,0);
			aV16(b, randomColor3,0,0);

			b.drawImage(crowd2, 170, 480, null);
			b.drawImage(crowd, 50, 495, null);

			speakers(b,0,0);

			leftTeeter(b, -3, -3);
			leftMid(b, -3, -3);
			leftLow(b, -6, -6);
			rightTeeter(b, -3, 3);
			rightMid(b, -3, -3);
			rightLow(b, 6, -6);

			counter++;
		}
		frame++;
		if (frame > 6)
			frame = 1;
	}


	/****************************************************************
	 ********************* Screen Item Methods **********************
	 ****************************************************************/
	// Mouse head
	public void headFrame(Graphics b, int w, int h) {
		circle(b, true, DEEP_PURP, WIDTH/2-200, HEIGHT/2-250, 400, 450); //Head
		circle(b, true, DEEP_PURP, w+150, h+115, 400, 360); //Left Ear
		circle(b, true, DEEP_PURP, w+700, h+115, 400, 360); //Right Ear
	}
	// Facial details
	public void facialDetail(Graphics b, Color c) {
		circle(b, true, c, WIDTH/2+45, HEIGHT/2-155, 150, 160); //Left Eye
		circle(b, true, c, WIDTH/2-195, HEIGHT/2-155, 150, 160); //Right Eye
		//Left Eye Patches
		quadrilateral(b, true, Color.BLACK,WIDTH/2-60,HEIGHT/2-125, WIDTH/2-75,HEIGHT/2-140,WIDTH/2-180,HEIGHT/2-23,WIDTH/2-163,HEIGHT/2-7);//1
		quadrilateral(b, true, Color.BLACK,WIDTH/2-165,HEIGHT/2-140, WIDTH/2-180,HEIGHT/2-124,WIDTH/2-80,HEIGHT/2-6,WIDTH/2-63,HEIGHT/2-20);//2
		//Right Eye Patches
		quadrilateral(b, true, Color.BLACK,WIDTH/2+60,HEIGHT/2-125, WIDTH/2+75,HEIGHT/2-140,WIDTH/2+180,HEIGHT/2-23,WIDTH/2+163,HEIGHT/2-7);//1
		quadrilateral(b, true, Color.BLACK,WIDTH/2+165,HEIGHT/2-140, WIDTH/2+180,HEIGHT/2-124,WIDTH/2+80,HEIGHT/2-6,WIDTH/2+63,HEIGHT/2-20);//2
		//Edge Refining	(for sharper smile)	*Changes color randomly
		triangle(b, true, randomColorX, 452,HEIGHT/2+40,550,HEIGHT/2+40,500,HEIGHT/2+129); //left
		triangle(b, true, randomColorX, 455,HEIGHT/2+40,550,HEIGHT/2+40,486,HEIGHT/2+110); //leftedge
		triangle(b, true, randomColorX, 798,HEIGHT/2+40,700,HEIGHT/2+40,750,HEIGHT/2+129); //right
		triangle(b, true, randomColorX, 796,HEIGHT/2+40,700,HEIGHT/2+40,764,HEIGHT/2+110); //rightedge
		semiCircle(b, true, randomColorX, WIDTH/2-160, HEIGHT/2-100, 320, 280, 0, -180); //Mouth (smile)
	}
	// 3 Dimensional Speakers
	public void speakers(Graphics b, int x, int y) {
		quadrilateral(b, true, Color.BLACK,x+25,y+600,15,950,190,990,x+210,y+540);//Left Speaker
		quadrilateral(b, true, Color.DARK_GRAY,x+220,y+540,200,1000,270,950,x+290,y+630);//LShade
		quadrilateral(b, true, Color.BLACK,1040-x,540-y,1050,990,1235,950,1225-x,600-y);//Right Speaker
		quadrilateral(b, true, Color.DARK_GRAY,965-x,630-y,980,950,1040,1000,1030-x,540-y);//RShade
	}
	//// Left Speaker Drivers ////
	public void leftTeeter(Graphics b, int x, int y) {	// Top Driver
		circle(b, true, Color.GRAY, x+85, y+620, 40, 55); // Cover
		circle(b, true, Color.BLACK, 93, 631, x+25, y+35); // Drum
		circle(b, false, Color.CYAN, 75, 610, 60, 75); // Ring
	}
	public void leftMid(Graphics b, int x, int y) {	// Middle Driver
		circle(b, true, Color.GRAY, x+75, y+710, 55, 70);
		circle(b, true, Color.BLACK, 82, 723, x+40, y+45);
		circle(b, false, Color.RED, 75, 710, x+55, y+70);
	}
	public void leftLow(Graphics b, int x, int y) { // Bottom Driver
		circle(b, true, Color.GRAY, x+50, y+810, 100, 125);
		circle(b, true, Color.BLACK, 60, 823, x+80, y+96);
		circle(b, false, Color.CYAN, 37, 795, 125, 155);
	}
	//// Right Speaker Drivers ////
	public void rightTeeter(Graphics b, int x, int y) {
		circle(b, true, Color.GRAY, x+1120, y+620, 40, 55);
		circle(b, true, Color.BLACK, 1128, 631, x+25, y+35);
		circle(b, false, Color.CYAN, 1109, 610, 60, 75);
	}
	public void rightMid(Graphics b, int x, int y) {
		circle(b, true, Color.GRAY, x+1115, y+710, 55, 70);
		circle(b, true, Color.BLACK, 1123, 723, x+40, y+45);
		circle(b, false, Color.RED, 1115, 710, x+55, y+70);
	}
	public void rightLow(Graphics b, int x, int y) {
		circle(b, true, Color.GRAY, x+1098, y+810, 100, 125);
		circle(b, true, Color.BLACK, 1109, 823, x+80, y+96);
		circle(b, false, Color.CYAN, 1085, 795, 125, 155);
	}

	///////////////// || Audio Visualization || \\\\\\\\\\\\\\\\\
	// Varying lengths of columns of rectangles to represent different sounds //
	public void aV1(Graphics b, Color c, int x, int y) { //Column 1
		rectangle(b, true, c, WIDTH/2-160+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-160+x, HEIGHT/2+50+y, 15, 8);
	}
	public void aV2(Graphics b, Color c, int x, int y) { //Column 2
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-140+x, HEIGHT/2+100+y, 15, 8);
	}
	public void aV3(Graphics b, Color c, int x, int y) { //Column 3
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-120+x, HEIGHT/2+120+y, 15, 8);
	}
	public void aV4(Graphics b, Color c, int x, int y) { //Column 4
		rectangle(b, true, c, WIDTH/2-100+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-100+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-100+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-100+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-100+x, HEIGHT/2+80+y, 15, 8);
	}
	public void aV5(Graphics b, Color c, int x, int y) { //Column 5
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-80+x, HEIGHT/2+100+y, 15, 8);
	}
	public void aV6(Graphics b, Color c, int x, int y) { //Column 6
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+120+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+130+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+140+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+150+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-60+x, HEIGHT/2+160+y, 15, 8);
	}
	public void aV7(Graphics b, Color c, int x, int y) { //Column 7
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+120+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-40+x, HEIGHT/2+130+y, 15, 8);
	}
	public void aV8(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+120+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+130+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+140+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+150+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+160+y, 15, 8);
		rectangle(b, true, c, WIDTH/2-20+x, HEIGHT/2+170+y, 15, 8);
	}
	public void aV9(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+x, HEIGHT/2+90+y, 15, 8);
	}
	public void aV10(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+120+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+130+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+140+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+20+x, HEIGHT/2+150+y, 15, 8);
	}
	public void aV11(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+40+x, HEIGHT/2+120+y, 15, 8);
	}
	public void aV12(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+60+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+60+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+60+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+60+x, HEIGHT/2+70+y, 15, 8);
	}
	public void aV13(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+80+x, HEIGHT/2+90+y, 15, 8);
	}
	public void aV14(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+80+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+90+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+100+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+110+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+100+x, HEIGHT/2+120+y, 15, 8);
	}
	public void aV15(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+120+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+120+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+120+x, HEIGHT/2+60+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+120+x, HEIGHT/2+70+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+120+x, HEIGHT/2+80+y, 15, 8);
	}
	public void aV16(Graphics b, Color c, int x, int y) { //Column
		rectangle(b, true, c, WIDTH/2+140+x, HEIGHT/2+40+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+140+x, HEIGHT/2+50+y, 15, 8);
		rectangle(b, true, c, WIDTH/2+140+x, HEIGHT/2+60+y, 15, 8);
	}


	/****************************************************************
	 *********************** Wrapper Methods ************************
	 ****************************************************************/
	void circle(Graphics b, boolean f, Color c, int x, int y, int h, int w) {
		b.setColor(c);
		if (f)
			b.fillOval(x,y,h,w);
		else
			b.drawOval(x,y,h,w);
	}

	void semiCircle(Graphics b, boolean f, Color c, int x, int y, int w, int h, int u, int v) {
		b.setColor(c);
		if (f)
			b.fillArc(x,y,w,h,u,v);
		else
			b.drawArc(x,y,w,h,u,v);
	}

	void triangle(Graphics b, boolean f, Color c, int x1, int y1, int x2, int y2, int x3, int y3) {
		int[] xP = {x1,x2,x3};
		int[] yP = {y1,y2,y3};
		b.setColor(c);
		if (f)
			b.fillPolygon(xP, yP, 3);
		else
			b.drawPolygon(xP, yP, 3);
	}

	void quadrilateral(Graphics b, boolean f, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		int[] xP = {x1,x2,x3,x4};
		int[] yP = {y1,y2,y3,y4};
		b.setColor(c);
		if (f)
			b.fillPolygon(xP, yP, 4);
		else
			b.drawPolygon(xP, yP, 4);
	}

	void rectangle(Graphics b, boolean f, Color c, int x, int y, int w, int h) {
		b.setColor(c);
		if (f)
			b.fillRect(x,y,w,h);
		else
			b.drawRect(x,y,w,h);
	}
}
