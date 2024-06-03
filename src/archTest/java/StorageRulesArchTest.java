import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@AnalyzeClasses(packages = "com.matchmate.tagsmanagementservice")
public class StorageRulesArchTest {

    @ArchTest
    static final ArchRule classes_name_in_mongo_documents_expected_document_suffix =
            classes().that().resideInAnyPackage("..mongo.documents..")
                    .should().haveSimpleNameEndingWith("Document")
                    .allowEmptyShould(true)
                    .because("Classes from infraestructure.storage.mongo.documents expected ending with 'Document' suffix");

    @ArchTest
    static final ArchRule classes_name_in_mongo_repositories_expected_mongo_repository_suffix =
            classes().that().resideInAnyPackage("..mongo.repositories")
                    .should().haveSimpleNameEndingWith("MongoRepository")
                    .allowEmptyShould(true)
                    .because("Classes from infraestructure.storage.mongo.repositories expected ending with 'MongoRepository' suffix");

    @ArchTest
    static final ArchRule should_be_repository_annotation_in_classes_reside_in_repositories_package =
            classes().that().resideInAPackage("..repositories..")
                    .should().beAnnotatedWith(Repository.class)
                    .allowEmptyShould(true)
                    .because("Expected annotation '@Repository' in repositories package");

    @ArchTest
    static final ArchRule should_be_document_annotation_in_classes_reside_in_documents_package =
            classes().that().resideInAPackage("..documents..")
                    .should().beAnnotatedWith(Document.class)
                    .allowEmptyShould(true)
                    .because("Expected annotation '@Document' in documents package");
}
