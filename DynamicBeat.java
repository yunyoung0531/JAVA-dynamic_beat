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

	// ������۸��� ���� ��üȭ�鿡 ���� �̹����� ��� �ν��Ͻ���
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

	
	// �Ʊ� �� �̹����� ���� �� �ִ� �ϳ��� ��ü
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
	private boolean isGameScreen = false; //����ȭ������ �Ѿ�Դ���
	
	ArrayList<Track> trackList  = new ArrayList<Track>();
	
	private Image titleImage;
	//�����̹���
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; // 0�� ù��°���� �ǹ�(index)
	
	public static Game game; //���� 
	
	public DynamicBeat() {
		
		//���ʴ�� 0,1,2��° �ε���!
		trackList.add(new Track("celebrity_title_image.psd", "celebrity_start_image.jfif", "celebrity_game_image.jfif", "������ (IU) - Celebrity.mp3", "������ (IU) - Celebrity.mp3", "������ (IU) - Celebrity.mp3"));
		trackList.add(new Track("���ϴ��Ǻ���_title_image.psd", "���ϴ��Ǻ���_start_image.jfif", "���ϴ��Ǻ���_game_image.jfif", "���ϴ��� ����(2020) .mp3", "���ϴ��� ����(2020) .mp3", "���ϴ��� ����(2020) .mp3"));
		trackList.add(new Track("��������ɸ���_title_image.psd", "��������ɸ���_start_image.jfif", "��������ɸ���_game_image.jpg", "������ - �� ��� ���� ����.mp3", "������ - �� ��� ���� ����.mp3", "������ - �� ��� ���� ����.mp3"));
		
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // �Ҹ��� ������ 1������ �Ŀ� ���α׷� ����->������
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // Ŭ������ �� �̺�Ʈó��
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000); // �Ҹ��� ������ 1������ �Ŀ� ���α׷� ����->������
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // Ŭ������ �� �̺�Ʈó��
			}
		});

		add(quitButton);
		
		
		//----------------------------------------------
		
		leftButton.setVisible(false); //�� ÷���� ������ �ʵ���
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//���� ��ư �̺�Ʈ
				selectLeft();
			}
		});

		add(leftButton);
		
//		//------------------------------------
//		
		rightButton.setVisible(false); //�� ÷���� ������ �ʵ���
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//������ ��ư �̺�Ʈ
				selectRight();
			}
		});

		add(rightButton);
		
		//----------------------------------------
		
		easyButton.setVisible(false); //�� ÷���� ������ �ʵ���
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// ���̵� ���� �̺�Ʈ
				gameStart(nowSelected, "Easy"); //���̵��� ���ٴ� ���� �˷���
			}
		});

		add(easyButton);
		
		//----------------------------------------
		
		hardButton.setVisible(false); //�� ÷���� ������ �ʵ���
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// ���̵� ����� �̺�Ʈ
				gameStart(nowSelected, "Hard"); //���̵��� ��ƴٴ� ���� �˷���
			}
		});

		add(hardButton);
		
		//---------------------------------------------
		
		backButton.setVisible(false); //�� ÷���� ������ �ʵ���
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
			public void mouseExited(MouseEvent e) { // �ش��ư���� ���콺�� ������ ���� �̺�Ʈó��
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				//����ȭ����  �ǵ��ư��� �̺�Ʈ
				backMain();
			}
		});

		add(backButton);
		
		//---------------------------------------------

		menuBar.setBounds(0, 0, 1200, 30); // ��ġ�� ũ�⸦ ������
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) { // �巡���̺�Ʈ
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
				// �巡���Ҷ� ������������ x��ǥ�� y��ǥ�� ���ͼ� ����â�� ��ġ�� �ٲ��� -> �ڵ��̺�Ʈó��
			}
		});
		add(menuBar);


	}

	public void paint(Graphics g) {
		// 1280*720 ��ŭ�� �̹����� ������ش����� �װ��� screenImage�� �־��ְڴ�
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// screenGraphic�� ��ũ���̹����� �̿��ؼ� �׷��Ȱ�ü�� ����
		screenGraphic = screenImage.getGraphics();
		// ��ũ���׷��ȿ� �׸��� �׷��ִ� ��
		screenDraw((Graphics2D) screenGraphic); //�۾������������� 
		// ����â�� ��ũ���̹����� (0,0)�� ��ġ�� �׷���
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// ��ũ���̹����� �׷��� �� �ֵ��� �ϴ� �� (0,0)�� ��ġ��
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) { //����ȭ���� �ƴ� ����ȭ���϶�
			g.drawImage(selectedImage, 400, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen)
		{
			game.screenDraw(g);
		}
		paintComponents(g); // �̹����� �׷���
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ٽ� ����Ʈ�Լ��� �ҷ���->��üȭ���̹����� �ż������� ��� �ݺ��Ǹ鼭 �׷���
		//add->paintComponents�� ���� �׷����� �͵�!!
		this.repaint();
	}
	
	//���õ� � ��ȣ�� �־������ν� �ش���� ���õ����� �˷���
		public void selectTrack(int nowSelected) {
			if(selectedMusic != null) //��Ѱ��� ����ǰ� �ִٸ�
			{
				selectedMusic.close(); //�ٷ� �����Ŵ
			}
			//���缱�õȰ�(Ʈ��)�� �����ִ� Ÿ��Ʋ�̹��� ���� �־���
			titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
			selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
			selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
			selectedMusic.start();
		}
	
	public void selectLeft() {
		if(nowSelected == 0) { //0��° ���϶�(�� ���ʰ�)
			nowSelected = trackList.size() - 1; //���ʹ�ư�� ������ �Ǹ�  ���� �����ʰ��� ���õǰԲ�
		}
		else {
			nowSelected--; //�ƴϸ� �׳� 1�� ����
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1) { //��������° ���϶�(�� �����ʰ�)
			nowSelected = 0; //�����ʹ�ư�� ������ �Ǹ�  ���� ���ʰ��� ���õǰԲ�
		}
		else {
			nowSelected++; //�ƴϸ� �׳� 1�� ������
		}
		selectTrack(nowSelected); //����
	}
	public void gameStart(int nowSelected, String difficulty) //���̵��� ���� ����->difficulty
	{
		if(selectedMusic != null) //������ �������̶��
		{
			selectedMusic.close(); //������� ������ ����
		}
		isMainScreen = false; //����ȭ���� �ƴϴ�->��,��ũ����ο��Լ����� if������x
		leftButton.setVisible(false); //����ȭ���� �ƴϹǷ� �������� �ȵ�
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;

		
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start(); //run�Լ� �ڵ�����
		setFocusable(true); //Ű�����̺�Ʈ�� �������� ��Ȯ��ĳġ�ϵ���
		requestFocus();
	}
	
	public void backMain() { 	//�����Լ��� ���ư��� �Լ�
		isMainScreen = true; 		//�ٽ� �������� ���ƿԱ⶧��
		leftButton.setVisible(true); //�ٽ� ���̵���!
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); //���缱�õ� Ʈ���� �����ְ� �뷡�� ������Ѷ�
		isGameScreen = false;
		game.close(); //���� ����ǰ� �ִ� ���Ӱ� ������ ���������ν� �ٸ�����  �����Ҽ��ֵ��� ��
	}
	
	public void enterMain() { //�����Լ��� ����
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		isMainScreen = true; //���۹�ư�� �������� false => true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0); //��÷�� ù��°�����
	}
}
