package com.saulo.webstore.services;

import com.saulo.webstore.models.*;
import com.saulo.webstore.models.enums.EstadoPagamento;
import com.saulo.webstore.repositories.ItemPedidoRepository;
import com.saulo.webstore.repositories.PagamentoRepository;
import com.saulo.webstore.repositories.PedidoRepository;
import com.saulo.webstore.services.exceptions.DataIntegrityException;
import com.saulo.webstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private CupomService cupomService;

    public Pedido findById(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido insert(Pedido pedido){
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
        }
        Double valorTotal = 0.0;
        for (ItemPedido ip : pedido.getItens()){
            Produto produto = produtoService.findById(ip.getProduto().getId());
            Integer quantidade = ip.getQuantidade();
            Double preco = produto.getPreco();
            Double desconto = produto.getDescontoPromocao();
            valorTotal = valorTotal + ((preco - desconto) * quantidade);
        }
        pedido.setValorTotal(valorTotal);
        if (pedido.getCupom() != null){
            Cupom cupom = cupomService.findByCodigo(pedido.getCupom());
            if (cupom.getTipo() == 1){
                Double valorComCupom = valorTotal * (1-(cupom.getDesconto()/100));
                pedido.setValorComCupom(valorComCupom);
            }else {
                Double valorComCupom = valorTotal - cupom.getDesconto();
                pedido.setValorComCupom(valorComCupom);
            }
        }else {
            pedido.setValorComCupom(valorTotal);
            pedido.setCupom("Cupom não inserido.");
        }
        pedido.setValorTotalAVista10PorcDesc(pedido.getValorComCupom() * 0.9);

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()){
            ip.setDesconto(produtoService.findById(ip.getProduto().getId()).getDescontoPromocao() * ip.getQuantidade());
            ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }

//    public Pedido insert(Pedido pedido){
//        pedido.setId(null);
//        return pedidoRepository.save(pedido);
//    }

    public Pedido update(Pedido pedido){
        Pedido newObj = findById(pedido.getId());
        updateData(newObj, pedido);
        return pedidoRepository.save(newObj);
    }

    public void deleteById(Integer id){
        findById(id);
        try {
            pedidoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um pedido que possui pagamento e itens !");
        }
    }

    public void updateData(Pedido newObj, Pedido pedido){
        newObj.setEnderecoEntrega(pedido.getEnderecoEntrega());
    }
}
