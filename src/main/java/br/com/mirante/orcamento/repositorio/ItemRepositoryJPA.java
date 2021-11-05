package br.com.mirante.orcamento.repositorio;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.mirante.orcamento.domain.ItemOrcamento;

@Repository
public class ItemRepositoryJPA implements ItemRepository {
	
	public ItemRepositoryJPA(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	private EntityManager entityManager;
	
	@Override
	public void excluir(Integer codigoItem) {
		var itemRecuperado = entityManager.find(ItemOrcamento.class, codigoItem);
		if(itemRecuperado != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(itemRecuperado);
			entityManager.getTransaction().commit();	
		}
		
	}

	@Override
	public void salvar(List<ItemOrcamento> itens) {
		itens.forEach(entityManager::persist);
	}
	
	@Override
	public void excluirItens(Integer idOrcamento) {
		entityManager
			.createQuery("delete from ItemOrcamento i where i.orcamento.id = :idOrcamento")
			.setParameter("idOrcamento", idOrcamento)
			.executeUpdate();
	}
}