package example.contactLenses;

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
			kbuilder.add( ResourceFactory.newClassPathResource("ContactLenses.drl", getClass() ),
			              ResourceType.DRL );
			if ( kbuilder.hasErrors() ) {
			    System.err.println(kbuilder.getErrors().toString() );
			}
			KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
			kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
			
			StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

			//attribute age 			{young, pre-presbyopic, presbyopic}
			//attribute spectacle-prescrip	{myope, hypermetrope}
			//attribute astigmatism		{no, yes}
			//attribute tear-prod-rate	{reduced, normal}
			//attribute contact-lenses	{soft, hard, none}
			//age, spectaclePrescrip, astigmatism, tearProdRate
			ContactLenses c = new ContactLenses("young","myope","no","reduced");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("young","myope","no","normal");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("young","hypermetrope","yes","normal");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("young","hypermetrope","yes","reduced");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("young","myope","yes","normal");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("presbyopic","hypermetrope","yes","normal");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
			c = new ContactLenses("pre-presbyopic","hypermetrope","yes","normal");
			ksession.execute(c);
			System.out.println("O paciente deve usar lente de contato? "+c.getContactLenses());
			
		}

		public static void main(String[] args) {
			new Main();
		}
}
