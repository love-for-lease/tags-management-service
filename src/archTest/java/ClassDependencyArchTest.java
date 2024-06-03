import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class ClassDependencyArchTest {

    @ArchTest
    static final ArchRule classes_reside_in_any_handler_package_expected_handler_suffix =
            classes().that().resideInAnyPackage("..handlers..")
                    .should().haveSimpleNameEndingWith("Handler")
                    .allowEmptyShould(true)
                    .because("Classes reside in any handlers package expected ending with 'Handler' suffix");

    @ArchTest
    static final ArchRule classes_reside_in_any_mapper_expected_mapper_suffix =
            classes().that().resideInAnyPackage("..mappers..")
                    .should().haveSimpleNameEndingWith("Mapper")
                    .allowEmptyShould(true)
                    .because("Classes reside in any mappers package expected ending with 'Mapper' suffix");
}
