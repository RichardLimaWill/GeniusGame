import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.management.timer.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class Genius {
	
	// Criação das variaveis
	
	JButton btnVermelho;
	JButton btnAmarelo;
	JButton btnVerde;
	JButton btnAzul;	
	
	private JFrame frmGame;
	JLabel lblScore = new JLabel();
	int score = 0;
	String recorde = "0";
	
	Fila fila = new Fila();
	private JTextField txtScore;
	
	// Variável que vai obter qual a posição que está a cor que o usuário clicou
	int posicaoAtualFila = 0;
	
	// Vetor que vai armazenar cada valor que o usuário clicar
	ArrayList<Integer> numerosClicados = new ArrayList<Integer>();	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Genius window = new Genius();
					window.frmGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Genius() {
		initialize();
	}
	
	private void initialize() {
		
		frmGame = new JFrame();
		frmGame.setFont(new Font("Dialog", Font.PLAIN, 20));
		frmGame.setTitle("GAME");
		frmGame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 24));
		frmGame.setBounds(100, 100, 400, 447);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGame.getContentPane().setLayout(null);
		
		// Criação dos botões
		btnVerde = new JButton("");
		btnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Quando o usuário clicar em uma cor, chama o método comparar
				 * Passa para o método o valor correspondente ao botão que está sendo clicado
				 * Passa para o método também o número presente na fila, na posição atual 
				 * (a posição atual começa em 0 e a cada botão que o usuário clica "anda" uma casa) */
				comparar(2,fila.getF(posicaoAtualFila));
				// Adiciona no vetor o número que acabou de ser clicado
				numerosClicados.add(2);
				// A posição atual agora muda para a próxima ("anda" uma posição)
				posicaoAtualFila ++;
				/* Somente vai piscar os botões quando o tamanho do vetor de números que o usuário clicou 
				 * for igual ao tamanho da fila que contém os números gerados */
				if (numerosClicados.size() == fila.getQtdeElementos()) {
					botaoAleatorio();
				}
			}
		});
		btnVerde.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnVerde.setBackground(Color.cyan);				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnVerde.setBackground(Color.green);
			}
		});
		
		btnVerde.setForeground(Color.WHITE);
		btnVerde.setBackground(new Color(0, 255, 0));
		btnVerde.setBounds(34, 91, 154, 114);
		frmGame.getContentPane().add(btnVerde);
		btnVerde.setContentAreaFilled(false);
		btnVerde.setOpaque(true);
		btnVerde.setBackground(Color.GREEN);

		btnAzul = new JButton("");
		btnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ver comentário do botão verde
				comparar(4,fila.getF(posicaoAtualFila));
				numerosClicados.add(4);
				posicaoAtualFila ++;
				if (numerosClicados.size() == fila.getQtdeElementos()) {
					botaoAleatorio();
				}
			}
		});
		btnAzul.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnAzul.setBackground(Color.cyan);				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnAzul.setBackground(Color.blue);
			}
		});
		btnAzul.setForeground(Color.BLACK);
		btnAzul.setOpaque(true);
		btnAzul.setContentAreaFilled(false);
		btnAzul.setBackground(new Color(0, 0, 255));
		btnAzul.setBounds(198, 216, 154, 114);
		frmGame.getContentPane().add(btnAzul);
		btnAzul.setContentAreaFilled(false);
		btnAzul.setOpaque(true);
		btnAzul.setBackground(Color.BLUE);

		btnVermelho = new JButton("");
		btnVermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ver comentário do botão verde
				comparar(3,fila.getF(posicaoAtualFila));
				numerosClicados.add(3);
				posicaoAtualFila ++;
				if (numerosClicados.size() == fila.getQtdeElementos()) {
					botaoAleatorio();
				}
			}
		});
		btnVermelho.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnVermelho.setBackground(Color.cyan);				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnVermelho.setBackground(Color.red);
			}
		});
		btnVermelho.setOpaque(true);
		btnVermelho.setContentAreaFilled(false);
		btnVermelho.setBackground(new Color(255, 0, 0));
		btnVermelho.setBounds(198, 91, 154, 114);
		frmGame.getContentPane().add(btnVermelho);
		btnVermelho.setContentAreaFilled(false);
		btnVermelho.setOpaque(true);
		btnVermelho.setBackground(Color.RED);

		btnAmarelo = new JButton("");
		btnAmarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ver comentário do botão verde
				comparar(1,fila.getF(posicaoAtualFila));
				numerosClicados.add(1);
				posicaoAtualFila ++;
				if (numerosClicados.size() == fila.getQtdeElementos()) {
					botaoAleatorio();
				}
			}
		});
		btnAmarelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnAmarelo.setBackground(Color.cyan);				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnAmarelo.setBackground(Color.yellow);
			}
		});
		btnAmarelo.setOpaque(true);
		btnAmarelo.setContentAreaFilled(false);
		btnAmarelo.setBackground(new Color(255, 255, 0));
		btnAmarelo.setBounds(34, 216, 154, 114);
		frmGame.getContentPane().add(btnAmarelo);
		btnAmarelo.setContentAreaFilled(false);
		btnAmarelo.setOpaque(true);
		btnAmarelo.setBackground(Color.YELLOW);

		JLabel lblLogo = new JLabel("GENIUS");
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogo.setBounds(138, 41, 133, 32);
		frmGame.getContentPane().add(lblLogo);

		JLabel lblNewLabel = new JLabel("SCORE:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 86, 32);
		frmGame.getContentPane().add(lblNewLabel);

		JButton btnStart = new JButton("START");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//Toca o som inicial do botão start
				tocarSom("play");
				botaoAleatorio();
			}
		});
		btnStart.setBounds(115, 352, 148, 45);
		frmGame.getContentPane().add(btnStart);
		
		txtScore = new JTextField();
		txtScore.setEditable(false);
		txtScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtScore.setBackground(SystemColor.control);
		txtScore.setBounds(82, 11, 51, 30);
		frmGame.getContentPane().add(txtScore);
		txtScore.setColumns(10);
	}
	
	public void comparar(int numeroClicado, int numeroCerto) {
		tocarSom("proximo");
		int qtd = fila.getQtdeElementos();	
		
		if(qtd-1 < 0)
		{
			JOptionPane.showMessageDialog(frmGame, "Inicie o programa primeiro!!!");
			System.exit(0);
		}
		if(numeroClicado == numeroCerto)
		{
			score += 10;
			recorde = String.valueOf(score);
			txtScore.setText(recorde);
		}
		else {
			tocarSom("perder");
			recorde = String.valueOf(score);
			txtScore.setText(recorde);
			JOptionPane.showMessageDialog(frmGame, "Infelizmente você perdeu! Seu Score foi de: " + recorde);
			System.exit(0);
		}
	}
	public static void tocarSom(String nome) {
        try {
            
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sons/"+nome+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
	
	public void botaoAleatorio() {
		Random rand = new Random();
		int num = rand.nextInt(4) + 1;
		if(fila.getQtdeElementos()>0) {
			while(num == fila.getF((fila.getQtdeElementos()-1))) 
			{
				num = rand.nextInt(4) + 1;
			}
		}
		fila.adicionar(num);
		posicaoAtualFila = 0;
		numerosClicados.clear();
		
		pisca(num);
	}

	public void pisca(int num) {
		mostraOrdem();
		switch (num) {
		case 1:
			acendeBotao(btnAmarelo);
			break;
		case 2:
			acendeBotao(btnVerde);
			break;
		case 3:
			acendeBotao(btnVermelho);
			break;
		case 4:
			acendeBotao(btnAzul);
			break;
		}
			javax.swing.Timer timer = new javax.swing.Timer(1250, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					apagaBotaoAceso();
				}
			});
			timer.setRepeats(false);
			timer.start();
	}
	
	public void mostraOrdem() {
		System.out.println("-");
		int tamanhoFila = fila.getQtdeElementos();
		for (int i = 0; i < tamanhoFila; i++) {
			int numfila = fila.getF(i);
			apagaBotaoAceso();
			switch (numfila) {
			case 1:
				System.out.println("YELLOW");
				break;
			case 2:
				System.out.println("GREEN");
				break;
			case 3:
				System.out.println("RED");
				break;
			case 4:
				System.out.println("BLUE");
				break;
			}
		}
	}
	
	public void acendeBotao(JButton botao) {
		botao.setBackground(Color.cyan);
	}
	
	public void apagaBotaoAceso() {
		btnAzul.setBackground(Color.blue);
		btnAmarelo.setBackground(Color.yellow);
		btnVerde.setBackground(Color.green);
		btnVermelho.setBackground(Color.red);
	}
}
