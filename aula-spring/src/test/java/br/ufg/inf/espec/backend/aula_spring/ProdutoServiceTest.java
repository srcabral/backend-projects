package br.ufg.inf.espec.backend.aula_spring;

import br.ufg.inf.espec.backend.aula_spring.model.Produto;
import br.ufg.inf.espec.backend.aula_spring.repository.ProdutoRepository;
import br.ufg.inf.espec.backend.aula_spring.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository; // Mock do repositório
    @InjectMocks
    private ProdutoService service; // Injeção do mock no serviço

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa mocks e injectMocks
    }

    @Test
    public void testAddProduto() {
        String nome = "Teste";
        double preco = 100;

        when(produtoRepository.save(any(Produto.class))).thenAnswer(i -> {
            Produto p = i.getArgument(0);
            p.setId(1L); // Simular a geração de ID pelo banco de dados
            return p;
        });
        // Chama salvarProduto e realiza validações ...
        Produto produto = new Produto(null, nome, preco);
        Produto produtoSalvo = service.salvarProduto(produto);
        assertNotNull(produtoSalvo);
        assertNotNull(produtoSalvo.getId());
        assertEquals(nome, produtoSalvo.getNome());
        assertEquals(preco, produtoSalvo.getPreco());
    }
}
