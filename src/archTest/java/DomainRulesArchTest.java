import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import org.springframework.stereotype.Service;


@AnalyzeClasses(packages = "com.leaseforlove.tagsmanagementservice")
public class DomainRulesArchTest {

    @ArchTest
    static final ArchRule gateways_should_only_be_accessed_by_infrastructure_and_application =
            classes().that().resideInAnyPackage("..domain.gateways..")
                    .should().onlyBeAccessed().byAnyPackage("..infrastructure..", "..application.services..")
                    .allowEmptyShould(true)
                    .because("Infrastructure and Application packages only be access interfaces from domain.gateways package");

    @ArchTest
    static final ArchRule gataways_should_be_reside_only_gateways_package =
            noClasses().that().resideOutsideOfPackage("..domain.gateways..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "..domain.services..", "..domain.core..", "..application.http..")
                    .allowEmptyShould(true)
                    .because("Gateways interfaces only reside in domain.gateway package");

    @ArchTest
    static final ArchRule domain_not_be_access_infrastructure_or_application_classes =
            noClasses().that().resideInAnyPackage("..application..", "..infrastructure..")
                    .should().accessClassesThat().resideInAnyPackage("..domain..")
                    .allowEmptyShould(true)
                    .because("Domain not be access classes from Infrastructure or Application packages");

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
}
