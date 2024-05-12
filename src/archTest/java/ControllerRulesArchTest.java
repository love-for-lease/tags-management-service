import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import static com.tngtech.archunit.core.domain.JavaClass.Functions.GET_PACKAGE_NAME;
import com.tngtech.archunit.core.domain.JavaMember;
import static com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn;
import com.tngtech.archunit.core.domain.PackageMatchers;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.leaseforlove.tagsmanagementservice")
public class ControllerRulesArchTest {

    @ArchTest
    static final ArchRule controllers_should_only_call_service_methods =
            classes().that().resideInAPackage("..controllers..")
                    .should().onlyCallMethodsThat(areDeclaredInController()
                            .or(are(annotatedWith(Service.class)))
                            .or(are(annotatedWith(Component.class))))
                    .allowEmptyShould(true)
                    .because("Controllers only call methods annotated with '@Service' or '@Component'");

    @ArchTest
    static final ArchRule others_classes__should_not_depend_on_controllers =
            noClasses().that().resideInAnyPackage(
                            "..application.config..", "..application.services..", "..domain..",
                            "..infraestructure..", "..http.config..")
                    .should().dependOnClassesThat().resideInAPackage("..controllers..")
                    .allowEmptyShould(true)
                    .because("Controllers should not be a dependency of other packages");

    @ArchTest
    static final ArchRule classes_name_in_controllers_requests_expected_request_suffix =
            classes().that().resideInAnyPackage("..http.requests..")
                    .should().haveSimpleNameEndingWith("Request")
                    .allowEmptyShould(true)
                    .because("Classes from http.controllers.requests expected ending with 'Request' suffix");

    @ArchTest
    static final ArchRule classes_name_in_controllers_responses_expected_request_suffix =
            classes().that().resideInAnyPackage("..http.responses..")
                    .should().haveSimpleNameEndingWith("Response")
                    .allowEmptyShould(true)
                    .because("Classes from http.controllers.response expected ending with 'Response' suffix");

    @ArchTest
    static final ArchRule all_controllers_classes_should_be_controller_suffix =
            classes().that().resideInAnyPackage("..http.controllers..")
                    .should().haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true)
                    .because("Classes from http.controllers expected ending with 'Controller' suffix");

    @ArchTest
    static final ArchRule all_public_methods_in_the_controller_layer_should_return_response_entity =
            methods()
                    .that().areDeclaredInClassesThat().resideInAPackage("..controllers..")
                    .and().arePublic()
                    .should().haveRawReturnType(ResponseEntity.class)
                    .allowEmptyShould(true)
                    .because("All public methods in the controller layer should return ResponseEntity");

    @ArchTest
    static final ArchRule classes_in_controllers_layer_should_be_annotated_with_rest_controller_and_request_mapping =
            classes().that().resideInAnyPackage("..controllers..")
                    .should().beAnnotatedWith(RestController.class)
                    .andShould().beAnnotatedWith(RequestMapping.class)
                    .allowEmptyShould(true)
                    .because("Classes within the controllers layer must be annotated with '@RestController' and '@RequestMapping'");

    private static DescribedPredicate<JavaMember> areDeclaredInController() {
        DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME.is(PackageMatchers.of("..controllers..", "java.."))
                .as("a package '..controller..'");
        return are(declaredIn(aPackageController));
    }
}
