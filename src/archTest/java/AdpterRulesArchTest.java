import com.matchmate.tagsmanagementservice.common.event.DomainEventHandler;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class AdpterRulesArchTest {

    @ArchTest
    static final ArchRule event_handlers_should_only_be_accessed_by_adapter_package =
            classes().that().haveSimpleNameEndingWith("EventHandler")
                    .should().resideInAnyPackage("..adapter..", "..common.event..");

    @ArchTest
    static final ArchRule event_handlers_should_implement_domain_event_handler =
            classes().that().resideInAPackage("..adapter.handlers..")
                    .and()
                    .haveSimpleNameEndingWith("EventHandler")
                    .should().implement(DomainEventHandler.class);

}
