package example.license;

import java.util.Date;

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
		kbuilder.add( ResourceFactory.newClassPathResource("licenseApplication.drl", getClass() ),
		              ResourceType.DRL );
		if ( kbuilder.hasErrors() ) {
		    System.err.println(kbuilder.getErrors().toString() );
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		Application application = new Application( "Joao da Silva", 16 , new Date("2009/09/21"));
		Application application2 = new Application( "Antonio Eduardo", 20 , new Date("2008/09/21"));
		Application application3 = new Application( "Carlos da Silva", 20 , new Date("2009/09/21"));
		ksession.execute(application);
		System.out.println(application.isValid());
		ksession.execute(application2);
		System.out.println(application2.isValid());
		ksession.execute(application3);
		System.out.println(application3.isValid());

	}

	public static void main(String[] args) {
		new Main();
	}

}
