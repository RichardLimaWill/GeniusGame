import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;

public class Fila{

	int inicio = -1;
    int fim = inicio;
    // Tamanho é a quantidade de rounds que o jogo terá
    int tamanho = 7;
    // Variável que armazena quantos elementos a fila possui
    int qtdeElementos = 0;
    // Criação da Fila
    int f[] = new int[tamanho];
    
    //Verifica se a fila está vazia
    public boolean estaVazia(){
        if (qtdeElementos == 0){
            return true;
        }
        return false;
    }
    
    //Verifica se a fila está cheia, caso esteja, o usuário venceu
    public boolean estaCheia(){
        if (qtdeElementos == tamanho){
        	Genius.tocarSom("ganhar");
        	JOptionPane.showMessageDialog(null, "Parabéns! Você terminou o jogo!");
        	System.exit(0);
            return true;
        }
        return false;
    }
    
    //Adiciona um novo elemento na fila
    public void adicionar(int e){
    	if (! estaCheia()){
            if (inicio == -1){
                inicio = 0;
            }
            fim++;
            f[fim] = e;
            qtdeElementos++;
        	}
    }
    
    //Obtém o numero da fila que está na posição informada (i)
	public int getF(int i) {
		
		return f[i];
	}
	
	// Obtém a quantidade de elementos contidos na fila
	public int getQtdeElementos() {
		 
		return qtdeElementos;
	}

}
