import java.util.NoSuchElementException;

public class Fila<E> {

	private Celula<E> frente;
	private Celula<E> tras;
	
	Fila() {
		
		Celula<E> sentinela = new Celula<E>();
		frente = tras = sentinela;
	}
	
	public boolean vazia() {
		
		return (frente == tras);
	}
	
	public void enfileirar(E item) {
		
		Celula<E> novaCelula = new Celula<E>(item);
		
		tras.setProximo(novaCelula);
		tras = tras.getProximo();
	}
	
	public E desenfileirar() {
		
		E item = null;
		Celula<E> primeiro;
		
		item = consultarPrimeiro();
		
		primeiro = frente.getProximo();
		frente.setProximo(primeiro.getProximo());
		
		primeiro.setProximo(null);
			
		// Caso o item desenfileirado seja também o último da fila.
		if (primeiro == tras)
			tras = frente;
		
		return item;
	}
	
	public E consultarPrimeiro() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na fila!");
		}

		return frente.getProximo().getItem();

	}
	
	public void imprimir() {
		
		Celula<E> aux;
		
		if (vazia())
			System.out.println("A fila está vazia!");
		else {
			aux = this.frente.getProximo();
			while (aux != null) {
				System.out.println(aux.getItem());
				aux = aux.getProximo();
			}
		} 	
	}

    public int contaCaracteresRecorrentes(char alvo) {
        Celula<E> atual = frente.getProximo();
        int count = 0;
        while (atual != null) {
            if (atual.getItem().equals(alvo))
                count++;
            atual = atual.getProximo();
        }
        return count;
    }

	public Fila<E> extrairLote(int numItens) {
        Fila<E> lote = new Fila<>();
        int i = 0;
        while(i < numItens && !vazia()) {
            lote.enfileirar(desenfileirar());
            i++;
        }

        return lote;
    }
}