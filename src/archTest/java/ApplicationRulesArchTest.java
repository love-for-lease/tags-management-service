import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class ApplicationRulesArchTest {

    @ArchTest
    static final ArchRule configurations_classes_expected_annotated_with_configuration =
            classes().that().resideInAPackage("..application.config..")
                    .should().beAnnotatedWith(Configuration.class)
                    .allowEmptyShould(true)
                    .because("Configuration classes expected annotated with '@Configuration'");

    @ArchTest
    static final ArchRule classes_name_in_config_should_expected_Config_suffix =
            classes().that().resideInAPackage("..application.config..")
                    .should().haveSimpleNameEndingWith("Config")
                    .allowEmptyShould(true)
                    .because("Classes from application.config expected ending with 'Config' suffix");

    @ArchTest
    static final ArchRule application_services_should_be_annotated_with_component =
            classes().that().resideInAPackage("..application.services..")
                    .should().beAnnotatedWith(Component.class)
                    .andShould().notBeAnnotatedWith(Service.class)
                    .allowEmptyShould(true)
                    .because("Classes in application.services layer expected annotated with '@Component'. don't use '@Service'");


}
