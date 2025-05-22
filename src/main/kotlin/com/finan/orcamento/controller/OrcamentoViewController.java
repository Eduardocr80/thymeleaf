package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OrcamentoViewController {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/orcamentoPage")
    public String mostrarPaginaOrcamento(Model model) {
        // Adiciona um objeto vazio de OrcamentoModel para o formulário
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        // Adiciona todos os orçamentos cadastrados para exibir na tabela
        model.addAttribute("orcamentos", orcamentoRepository.findAll());
        // Adiciona a lista de usuários para o select de usuário
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "orcamentoPage"; // Renderiza o arquivo Thymeleaf orcamentoPage.html
    }

    @PostMapping("/orcamentosnovo")
    public String salvarOrcamento(@ModelAttribute OrcamentoModel orcamentoModel) {
        orcamentoModel.calcularIcms();

        Long usuarioId = orcamentoModel.getUsuario().getId();
        UsuarioModel usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario != null) {
            orcamentoModel.setUsuario(usuario);
        } else {
            // Aqui você pode lançar uma exceção ou retornar algum erro
            // ou simplesmente tratar o erro (por exemplo, redirecionar com mensagem)
        }

        orcamentoRepository.save(orcamentoModel);
        return "redirect:/orcamentoPage";
    }

}
