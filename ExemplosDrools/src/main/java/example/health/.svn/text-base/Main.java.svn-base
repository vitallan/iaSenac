package example.health;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class Main{
	
	public Main(){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("healthInsurance.drl",getClass()),ResourceType.DRL);
		if ( kbuilder.hasErrors() ) {
		    System.err.println(kbuilder.getErrors().toString() );
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		Pessoa p = new Pessoa(
				"Maria",
				30,
				Tipos.CADASTRO.OK,
				Tipos.CARENCIA.DENTRO_LIMITE,
				Tipos.SINISTRO.ACIDENTE);
		ksession.execute(p);
		System.out.println(p.getNome()+" "+p.isLiberado());
		
		Pessoa p2 = new Pessoa(
				"Joana",
				20,
				Tipos.CADASTRO.OK,
				Tipos.CARENCIA.FORA_LIMITE,
				Tipos.SINISTRO.PARTO);
		ksession.execute(p2);
		System.out.println(p2.getNome()+" "+p2.isLiberado());
		
		Pessoa p3 = new Pessoa(
				"Karina",
				35,
				Tipos.CADASTRO.NAO_OK,
				Tipos.CARENCIA.DENTRO_LIMITE,
				Tipos.SINISTRO.ACIDENTE);
		ksession.execute(p3);
		System.out.println(p3.getNome()+" "+p3.isLiberado());
	}

	public static void main(String[] args) {
		new Main();
	}

}
