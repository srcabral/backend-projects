package br.ufg.inf.espec.backend.aula_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufg.inf.espec.backend.aula_spring.model.Produto;
import br.ufg.inf.espec.backend.aula_spring.service.ProdutoService;
import org.springframework.ui.Model;

@Controller
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/produtos")
	public String listarProdutos(Model model, @RequestParam(required = false) String sucesso) {
		model.addAttribute("produtos", produtoService.listarProdutos());
		model.addAttribute("sucesso", sucesso);
		return "produtos";
	}

	@GetMapping("/produtos/adicionar")
	public String mostrarFormularioAdicionarProduto() {
		return "adicionar-produto";
	}

	@PostMapping("/produtos")
	public String adicionarProduto(@RequestParam String nome, @RequestParam double preco,
			RedirectAttributes redirectAttributes) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(preco);
		produtoService.salvarProduto(produto);
		redirectAttributes.addAttribute("sucesso", "Produto adicionado com sucesso!");
		return "redirect:/produtos";
	}
}
