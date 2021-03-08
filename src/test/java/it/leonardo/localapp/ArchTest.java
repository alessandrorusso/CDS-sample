package it.leonardo.localapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.leonardo.localapp");

        noClasses()
            .that()
            .resideInAnyPackage("it.leonardo.localapp.service..")
            .or()
            .resideInAnyPackage("it.leonardo.localapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..it.leonardo.localapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
