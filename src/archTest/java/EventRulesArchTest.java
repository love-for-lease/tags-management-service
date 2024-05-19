import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class EventRulesArchTest {
    @ArchTest
    static final ArchRule classes_name_in_event_should_expected_Event_suffix =
            classes().that().resideInAPackage("..domain.events..")
                    .should().haveSimpleNameEndingWith("Event")
                    .allowEmptyShould(true)
                    .because("Classes from application.config expected ending with 'Event' suffix");

    @ArchTest
    static final ArchRule event_should_be_reside_only_events_package =
            noClasses().that().resideInAPackage("..domain.event..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "..domain.core..", "..application.http..", "..application.config..")
                    .allowEmptyShould(true)
                    .because("Event classes only reside in domain.events package");
}
