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

	@GetMapping("/produtos/editar")
	public String mostrarFormularioEditarProduto(@RequestParam("id") Long id, Model model) {
		Produto produto = produtoService.obterProdutos(id);
		model.addAttribute("produto", produto);
		return "editar-produto";
	}

	@PostMapping("/produtos/editar")
	public String editarProduto(@RequestParam("id") Long id, @RequestParam("nome") String nome,
								@RequestParam("preco") double preco, RedirectAttributes redirectAttributes) {
		Produto produto = produtoService.obterProdutos(id);
		produto.setNome(nome);
		produto.setPreco(preco);
		produtoService.salvarProduto(produto);
		redirectAttributes.addAttribute("sucesso", "Produto atualizado com sucesso!");
		return "redirect:/produtos";
	}

	@PostMapping("/produtos/deletar")
	public String deletarProduto(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		produtoService.excluirProduto(id);
		redirectAttributes.addAttribute("sucesso", "Produto deletado com sucesso!");
		return "redirect:/produtos";
	}
}
