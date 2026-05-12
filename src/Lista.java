import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, primeiro.getProximo());
        if (vazia()) ultimo = nova;
        primeiro.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia()) throw new NoSuchElementException("Lista vazia!");
        Celula<E> atual = primeiro.getProximo();
        primeiro.setProximo(atual.getProximo());
        if (primeiro == ultimo) ultimo = primeiro;
        atual.setProximo(null);
        tamanho--;
        return atual.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = primeiro.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = primeiro.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    // Tarefa 1
    public E buscarPor(Comparator<E> criterioDeBusca, E item) {
        Celula<E> atual = primeiro.getProximo();
        while (atual != null) {
            E e = atual.getItem();
            if (criterioDeBusca.compare(e, item) == 0){
                return e;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Tarefa 2
    public double somarMultiplicacoes(Function<E, Double> extratorValor, Function<E, Integer> extratorFator) {
        if (vazia()) {
            throw new IllegalStateException("Não é possivel somar a lista vazia");
        }
        double total = 0.0;
        Celula<E> atual = primeiro.getProximo();
        while (atual != null) {
            E item = atual.getItem();
            total += extratorValor.apply(item) * extratorFator.apply(item);
            atual = atual.getProximo();
        }
        return total;
        // multiplicá-los e acumular no somatório final.
        // Lançar IllegalStateException se a lista estiver vazia.
    }

    // Tarefa 3
    public Lista<E> filtrar(Predicate<E> condicional) {
        if (vazia()) {
            throw new IllegalStateException("Não é possivel filtrar a lista vazia");
        }
        Lista<E> resultado = new Lista<>();
        Celula<E> atual = primeiro.getProximo();
        while (atual != null) {
            E item = atual.getItem();
            if (condicional.test(item)){
                resultado.inserirInicio(item);
            }
            atual = atual.getProximo();
        }
        return resultado;
        //       para os quais condicional.test() retorna true.
        //       Lançar IllegalStateException se a lista estiver vazia.
    }
}
