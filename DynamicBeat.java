package dynamic_beat_16;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import dynamic_beat_1.Main;

public class DynamicBeat extends JFrame {

	// 더블버퍼링을 위해 전체화면에 대한 이미지를 담는 인스턴스들
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));

	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));

	
	// 아까 그 이미지를 담을 수 있는 하나의 객체
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false; //게임화면으로 넘어왔는지
	
	ArrayList<Track> trackList  = new ArrayList<Track>();
	
	private Image titleImage;
	//시작이미지
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; // 0은 첫번째곡을 의미(index)
	
	public static Game game; //선언만 
	
	public DynamicBeat() {
		
		//차례대로 0,1,2번째 인덱스!
		trackList.add(new Track("celebrity_title_image.psd", "celebrity_start_image.jfif", "celebrity_game_image.jfif", "아이유 (IU) - Celebrity.mp3", "아이유 (IU) - Celebrity.mp3", "아이유 (IU) - Celebrity.mp3"));
		trackList.add(new Track("밤하늘의별을_title_image.psd", "밤하늘의별을_start_image.jfif", "밤하늘의별을_game_image.jfif", "밤하늘의 별을(2020) .mp3", "밤하늘의 별을(2020) .mp3", "밤하늘의 별을(2020) .mp3"));
		trackList.add(new Track("봄사랑벚꽃말고_title_image.psd", "봄사랑벚꽃말고_start_image.jfif", "봄사랑벚꽃말고_game_image.jpg", "아이유 - 봄 사랑 벚꽃 말고.mp3", "아이유 - 봄 사랑 벚꽃 말고.mp3", "아이유 - 봄 사랑 벚꽃 말고.mp3"));
		
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new KeyListener());
		
		introMusic.start();
	
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // 소리가 나온후 1초정도 후에 프로그램 종료->정상적
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 클릭했을 때 이벤트처리
			}
		});

		add(exitButton);

		// -----------------------------------

		startButton.setBounds(350, 450, 300, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				introMusic.close();
				enterMain();
			}
		});

		add(startButton);

		// --------------------------------

		quitButton.setBounds(600, 450, 380, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // 소리가 나온후 1초정도 후에 프로그램 종료->정상적
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 클릭했을 때 이벤트처리
			}
		});

		add(quitButton);
		
		
		//----------------------------------------------
		
		leftButton.setVisible(false); //맨 첨에는 보이지 않도록
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//왼쪽 버튼 이벤트
				selectLeft();
			}
		});

		add(leftButton);
		
//		//------------------------------------
//		
		rightButton.setVisible(false); //맨 첨에는 보이지 않도록
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//오른쪽 버튼 이벤트
				selectRight();
			}
		});

		add(rightButton);
		
		//----------------------------------------
		
		easyButton.setVisible(false); //맨 첨에는 보이지 않도록
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 난이도 쉬움 이벤트
				gameStart(nowSelected, "Easy"); //난이도가 쉽다는 것을 알려줌
			}
		});

		add(easyButton);
		
		//----------------------------------------
		
		hardButton.setVisible(false); //맨 첨에는 보이지 않도록
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 난이도 어려움 이벤트
				gameStart(nowSelected, "Hard"); //난이도가 어렵다는 것을 알려줌
			}
		});

		add(hardButton);
		
		//---------------------------------------------
		
		backButton.setVisible(false); //맨 첨에는 보이지 않도록
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) { // 해당버튼에서 마우스가 나왔을 때의 이벤트처리
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//메인화면을  되돌아가는 이벤트
				backMain();
			}
		});

		add(backButton);
		
		//---------------------------------------------

		menuBar.setBounds(0, 0, 1200, 30); // 위치와 크기를 정해줌
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) { // 드래그이벤트
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				// 드래그할때 순간순간마다 x좌표와 y좌표를 얻어와서 게임창의 위치를 바꿔줌 -> 자동이벤트처리
			}
		});
		add(menuBar);


	}

	public void paint(Graphics g) {
		// 1280*720 만큼의 이미지를 만들어준다음에 그것을 screenImage에 넣어주겠다
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// screenGraphic은 스크린이미지를 이용해서 그래픽객체를 얻어옴
		screenGraphic = screenImage.getGraphics();
		// 스크린그래픽에 그림을 그려주는 것
		screenDraw((Graphics2D) screenGraphic); //글씨깨짐막기위해 
		// 게임창에 스크린이미지를 (0,0)의 위치에 그려줌
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// 스크린이미지에 그려줄 수 있도록 하는 것 (0,0)의 위치에
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) { //시작화면이 아닌 메인화면일때
			g.drawImage(selectedImage, 400, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen)
		{
			game.screenDraw(g);
		}
		paintComponents(g); // 이미지를 그려줌
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 다시 페인트함수를 불러옴->전체화면이미지를 매순간마다 계속 반복되면서 그려줌
		//add->paintComponents에 의해 그려지는 것들!!
		this.repaint();
	}
	
	//선택된 곡에 번호를 넣어줌으로써 해당곡이 선택됐음을 알려줌
		public void selectTrack(int nowSelected) {
			if(selectedMusic != null) //어떠한곡이 실행되고 있다면
			{
				selectedMusic.close(); //바로 종료시킴
			}
			//현재선택된곡(트랙)이 갖고있는 타이틀이미지 값을 넣어줌
			titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
			selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
			selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
			selectedMusic.start();
		}
	
	public void selectLeft() {
		if(nowSelected == 0) { //0번째 곡일때(젤 왼쪽곡)
			nowSelected = trackList.size() - 1; //왼쪽버튼을 누르게 되면  가장 오른쪽곡이 선택되게끔
		}
		else {
			nowSelected--; //아니면 그냥 1씩 빼줘
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1) { //마지막번째 곡일때(젤 오른쪽곡)
			nowSelected = 0; //오른쪽버튼을 누르게 되면  가장 왼쪽곡이 선택되게끔
		}
		else {
			nowSelected++; //아니면 그냥 1씩 더해줘
		}
		selectTrack(nowSelected); //세팅
	}
	public void gameStart(int nowSelected, String difficulty) //난이도에 대한 정보->difficulty
	{
		if(selectedMusic != null) //음악이 실행중이라면
		{
			selectedMusic.close(); //재생중인 음악을 닫음
		}
		isMainScreen = false; //메인화면이 아니다->즉,스크린드로우함수에서 if문실행x
		leftButton.setVisible(false); //메인화면이 아니므로 보여지면 안됨
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;

		
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start(); //run함수 자동실행
		setFocusable(true); //키보드이벤트가 오류없이 정확히캐치하도록
		requestFocus();
	}
	
	public void backMain() { 	//메인함수로 돌아가는 함수
		isMainScreen = true; 		//다시 메인으로 돌아왔기때문
		leftButton.setVisible(true); //다시 보이도록!
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); //현재선택된 트랙을 보여주고 노래를 재생시켜라
		isGameScreen = false;
		game.close(); //현재 실행되고 있는 게임과 음악을 종료함으로써 다른곡을  선택할수있도록 함
	}
	
	public void enterMain() { //메인함수로 들어간다
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		isMainScreen = true; //시작버튼을 눌렀을때 false => true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0); //맨첨에 첫번째곡실행
	}
}
