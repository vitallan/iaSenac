package example.simple;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class Main {

	public Main(){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource("simple.drl", getClass() ),
		              ResourceType.DRL );
		if ( kbuilder.hasErrors() ) {
		    System.err.println(kbuilder.getErrors().toString() );
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

		Simple s = new Simple(11);
		ksession.execute(s);
		System.out.println(s.isOutput());
		System.out.println("11 - deve retornar true");
		
		Simple s2 = new Simple(5);
		ksession.execute(s2);
		System.out.println(s2.isOutput());
		System.out.println("5 - deve retornar false");
		
		Simple s3 = new Simple(25);
		ksession.execute(s3);
		System.out.println(s3.isOutput());
		System.out.println("25 - deve retornar false");
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
