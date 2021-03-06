package org.jugvale.transfgov.service.impl;

import java.util.List;
import java.util.function.Supplier;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.jugvale.transfgov.model.indicador.Indicador;
import org.jugvale.transfgov.service.Service;

@Stateless
public class IndicadorService extends Service<Indicador> {

	public Indicador buscaPorNomeOuCria(String nomeindicador,
				Supplier<Indicador> criaIndicador) {
			Indicador indicador;
			try {
				indicador = buscaPorNome(nomeindicador);
			} catch (NoResultException e) {
				indicador = criaIndicador.get();
				salvar(indicador);
			}
			return indicador;
		}

		public Indicador buscaPorNome(String indicador) {
			TypedQuery<Indicador> buscaIndicadorPorNome = em.createNamedQuery(
					"Indicador.porNome", Indicador.class);
			buscaIndicadorPorNome.setParameter("nome", indicador);
			return buscaIndicadorPorNome.getSingleResult();
			
		}
		
		public List<Indicador> buscaPorGrupo(String grupo) {
			TypedQuery<Indicador> buscaIndicadorPorNome = em.createNamedQuery(
					"Indicador.porGrupo", Indicador.class);
			buscaIndicadorPorNome.setParameter("nomeGrupo", grupo);
			return buscaIndicadorPorNome.getResultList();
		}

}
