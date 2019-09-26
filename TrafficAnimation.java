import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a [put your description here]
 *
 * @author BSU CS 121 Instructors
 * @author Everett Hansen
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = new Color(0, 125, 0);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
		
		// Draws the Road
		g.setColor(Color.gray);
		g.fillRect(0, (height - (height/2)) / 2 , width, height - (height / 2));

		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % (width * 2);

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		// This draws the bottom section of the vehicle.
		int squareSide = height / 4;
		int squareY = height / 2;
		
		g.setColor(Color.black);
		g.fillRect(xOffset, squareY, squareSide * 2, squareSide/4);
		
		//This draws the top section of the vehicle.
		int roofSide = height / 3;
		int roofY = height / 3;
		int start = 0;
		int arc = 180;
		
		g.setColor(Color.black);
		g.fillArc(xOffset + squareSide /3 , roofY + (roofSide / 7), roofSide, roofSide, start, arc);
		
		// This draws the wheels.
		int wheelY = height / 2;
		int wheelSide = height / 2;
		
		g.setColor(Color.white);
		g.fillOval(xOffset + wheelSide / 90, wheelY + wheelSide / 10, wheelSide / 10, wheelSide/ 10);
		
		g.setColor(Color.white);
		g.fillOval(xOffset + (wheelSide - wheelSide / 8), wheelY + wheelSide / 10, wheelSide / 10, wheelSide / 10);
		
		//This will be the code for a Second Car
		int squareSide2 = height / 4;
		int squareY2 = height / 4;
		
		g.setColor(Color.red);
		g.fillRect(width  - (xOffset*2) - (squareSide2 * 2) , squareY2, squareSide2 * 2, squareSide2 / 4);
		
		int roofSide2 = height /3;
		int roofY2 = height / 2;
		
		g.setColor(Color.red);
		g.fillArc(width - (xOffset*2) -(squareSide2 * 2) + (roofSide2 / 3), roofY2 - (3 * wheelSide / 4 ), roofSide2, roofSide2, start, arc);
		
		int wheelY2 = height / 2;
		int wheelSide2 = height /2;
		
		g.setColor(Color.cyan);
		g.fillOval(width - xOffset, wheelY2 + wheelSide2, wheelSide2 / 10, wheelSide2 / 10 );
		
		
		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
