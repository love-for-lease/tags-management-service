import com.matchmate.tagsmanagementservice.common.event.DomainEventHandler;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import org.springframework.stereotype.Service;


@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class DomainRulesArchTest {

    @ArchTest
    static final ArchRule gateways_should_only_be_accessed_by_infrastructure_and_application =
            classes().that().resideInAnyPackage("..domain.gateways..")
                    .should().onlyBeAccessed().byAnyPackage("..infrastructure..", "..application.services..")
                    .allowEmptyShould(true)
                    .because("Infrastructure and Application packages only be access interfaces from domain.gateways package");

    @ArchTest
    static final ArchRule gateways_should_be_reside_only_gateways_package =
            noClasses().that().resideInAPackage("..domain.gateways..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "..domain.services..", "..domain.core..", "..application.http..")
                    .allowEmptyShould(true)
                    .because("Gateways interfaces only reside in domain.gateway package");

    @ArchTest
    static final ArchRule domain_not_be_access_infrastructure_or_application_classes =
            noClasses().that().resideInAnyPackage("..domain..")
                    .should().accessClassesThat().resideInAnyPackage("..application..", "..infrastructure..")
                    .allowEmptyShould(true)
                    .because("Classes in the domain package should not access classes from the infrastructure or application packages");

    @ArchTest
    static final ArchRule classes_name_in_gateways_should_have_gateway_suffix =
            classes().that().resideInAPackage("..domain.gateways..")
                    .should().haveSimpleNameEndingWith("Gateway")
                    .allowEmptyShould(true)
                    .because("Classes from domain.gateways expected ending with 'Gateway' suffix");

    @ArchTest
    static final ArchRule classes_name_in_domain_usecases_expected_usecase_suffix =
            classes().that().resideInAnyPackage("..domain.usecases..")
                    .should().haveSimpleNameEndingWith("UseCase")
                    .allowEmptyShould(true)
                    .because("Classes from domain.usecases expected ending with 'UseCase' suffix");

    @ArchTest
    static final ArchRule only_interfaces_in_package_domain_gateways =
            classes().that().resideInAPackage("..domain.gateways..")
                    .should().beInterfaces()
                    .allowEmptyShould(true)
                    .because("This package should contain only interfaces");

    @ArchTest
    static final ArchRule classes_reside_in_usecases_should_be_containing_annotation_Service =
            classes().that().resideInAPackage("..domain.usecases..")
                    .should().beAnnotatedWith(Service.class)
                    .allowEmptyShould(true)
                    .because("Service classes should reside within the use case package or be annotated with @Service");

    @ArchTest
    static final ArchRule domain_not_be_access_mappers =
            noClasses().that().resideInAPackage("..domain..")
                    .should().accessClassesThat().resideInAPackage("..mappers..")
                    .andShould().accessClassesThat().haveSimpleNameEndingWith("Mapper")
                    .allowEmptyShould(true)
                    .because("Domain classes should not access mappers");

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

    @ArchTest
    static final ArchRule classes_name_in_properties_should_expected_Properties_suffix =
            classes().that().resideInAPackage("..application.properties..")
                    .should().haveSimpleNameEndingWith("Properties")
                    .allowEmptyShould(true)
                    .because("Classes from application.properties expected ending with 'Properties' suffix");

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
