package com.servico.backservico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public List<Servico> buscarTodos() {
		return servicoRepository.findAll();
	}
	
	public List<Servico> buscarServicosPagamanetosPendente() {
		return servicoRepository.buscarServicosPagamentoPendente();
	}
	
	public List<Servico> buscarServicosCancelados() {
		return servicoRepository.buscarServicosCancelados();
	}
	
	public Servico inserir(Servico servico) {
		if(servico.getValorPago()== null || servico.getValorPago()==0 || servico.getDataPagamento()==null ) {
			servico.setStatus("pendente");
		} else {
			servico.setStatus("realizado");
		}
		return servicoRepository.save(servico);
	}
	
	public Servico alterar(Servico servico) {
		if(servico.getValorPago()!=null && servico.getValorPago()>0 && servico.getDataPagamento()!=null) {
			servico.setStatus("realizado");
		}
		return servicoRepository.saveAndFlush(servico);
	}
	
	public void excluir(Long id) {
		//.get pega o objeto ao inves do optional que o findById retorna.
		Servico servico = servicoRepository.findById(id).get();
		servicoRepository.delete(servico);
	}
}
