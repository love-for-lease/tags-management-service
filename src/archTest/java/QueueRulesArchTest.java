import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class QueueRulesArchTest {
    @ArchTest
    static final ArchRule classes_name_in_consumers_should_expected_Consumer_suffix =
            classes().that().resideInAPackage("..adapter.queues.consumers..")
                    .should().haveSimpleNameEndingWith("Consumer")
                    .allowEmptyShould(true)
                    .because("Classes from adapter.jobs.consumers expected ending with 'Consumer' suffix");

    @ArchTest
    static final ArchRule classes_name_in_messages_should_expected_Message_suffix =
            classes().that().resideInAPackage("..adapter.jobs.messages..")
                    .should().haveSimpleNameEndingWith("Message")
                    .allowEmptyShould(true)
                    .because("Classes from adapter.queues.messages expected ending with 'Message' suffix");
}
