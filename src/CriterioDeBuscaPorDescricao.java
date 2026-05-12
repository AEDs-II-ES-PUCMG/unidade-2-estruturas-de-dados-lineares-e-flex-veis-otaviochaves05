import java.util.Comparator;

public class CriterioDeBuscaPorDescricao implements Comparator<ItemDePedido> {

    @Override
    public int compare(ItemDePedido item1, ItemDePedido item2) {
        if (item1.getProduto().descricao.compareTo(item2.getProduto().descricao) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
