import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class AdapterRulesArchTest {
    @ArchTest
    static final ArchRule classes_name_in_jobs_should_expected_Adapter_suffix =
            classes().that().resideInAPackage("..adapter.jobs..")
                    .should().haveSimpleNameEndingWith("Adapter")
                    .allowEmptyShould(true)
                    .because("Classes from adapter.jobs expected ending with 'Adapter' suffix");
}
