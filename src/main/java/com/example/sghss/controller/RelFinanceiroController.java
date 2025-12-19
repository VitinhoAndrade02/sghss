package com.example.sghss.controller;

import com.example.sghss.bo.UnidadeBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Unidade;
import com.example.sghss.model.Profissional;
import com.example.sghss.dto.Relatorio;
import com.example.sghss.dto.DetalheUnidade;
import com.example.sghss.dto.DetalheProfissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/relFinanceiros")
public class RelFinanceiroController {

    @Autowired
    private UnidadeBO unidadeBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public String gerarRelatorio(Model model) {
        List<Unidade> unidades = unidadeBO.lista();
        List<Profissional> profissionais = profissionalBO.lista();

        double salarioTotal = profissionais.stream().mapToDouble(Profissional::getSalario).sum();
        double receitaTotal = unidades.stream().mapToDouble(Unidade::getReceita).sum();
        double lucroLiquidoTotal = receitaTotal - salarioTotal;
        double despesaPorUnidade = !unidades.isEmpty() ? salarioTotal / unidades.size() : 0;

        List<DetalheUnidade> detalhesUnidades = unidades.stream().map(u -> {
            DetalheUnidade du = new DetalheUnidade();
            du.setId(u.getId()); // Garante unicidade pelo ID
            du.setNomeUnidade(u.getNome());
            du.setReceita(u.getReceita());
            du.setDespesaProporcional(despesaPorUnidade);
            du.setLucroLiquido(u.getReceita() - despesaPorUnidade);
            return du;
        }).collect(Collectors.toList());

        List<DetalheProfissional> detalhesProfissionais = profissionais.stream().map(p -> {
            DetalheProfissional dp = new DetalheProfissional();
            dp.setId(p.getId()); // Garante unicidade pelo ID
            dp.setNome(p.getNome());
            dp.setSalario(p.getSalario());
            return dp;
        }).collect(Collectors.toList());

        Relatorio relatorio = new Relatorio();
        relatorio.setTotalUnidades(unidades.size());
        relatorio.setReceitaTotal(receitaTotal);
        relatorio.setTotalProfissionais(profissionais.size());
        relatorio.setSalarioTotal(salarioTotal);
        relatorio.setLucroLiquidoTotal(lucroLiquidoTotal);
        relatorio.setDespesaPorUnidade(despesaPorUnidade);
        relatorio.setDetalhesUnidades(detalhesUnidades);
        relatorio.setDetalhesProfissionais(detalhesProfissionais);

        model.addAttribute("relatorio", relatorio);
        return "relFinanceiros/lista";
    }
}