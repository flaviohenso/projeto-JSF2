/**
 * @autor - root
 * Data: 12 de fev de 2017
 */
package com.flavio.debug;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LifeCycleListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		System.out.println("FINALIZANDO FASE: " + event.getPhaseId());
		
	}

	public void beforePhase(PhaseEvent event) {
		System.out.println("INICIANDO FASE: " + event.getPhaseId());
	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
