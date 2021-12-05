package ar.edu.unq.desapp.grupoK.backenddesappapi

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController


internal class ArchunitApplicationTests {
    private var importedClasses: JavaClasses? = null

    @BeforeEach
    fun setup() {
        importedClasses = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ar.edu.unq.desapp.grupoK.backenddesappapi.app")
    }

    @Test
    fun serviceClassesShouldBeNamedXServiceOrXComponentOrXServiceImpl() {
        classes()
            .that().resideInAPackage("..services..")
            .should().haveSimpleNameEndingWith("Service")
            .orShould().haveSimpleNameEndingWith("Service")
            .check(importedClasses)
    }

    @Test
    fun repositoryClassesShouldBeNamedXRepository() {
        classes()
            .that().resideInAPackage("..repositories..")
            .should().haveSimpleNameEndingWith("Repository")
            .check(importedClasses)
    }

    @Test
    fun controllerClassesShouldBeNamedXController() {
        classes()
            .that().resideInAPackage("..controllers..")
            .should().haveSimpleNameEndingWith("Controller")
            .check(importedClasses)
    }
    @Test
    fun aspectClassesShouldBeNamedXAspect() {
        classes()
            .that().resideInAPackage("..aspects..")
            .should().haveSimpleNameEndingWith("Aspect")
            .check(importedClasses)
    }
    @Test
    fun dtoClassesShouldBeNamedXDTO() {
        classes()
            .that().resideInAPackage("..dto..")
            .should().haveSimpleNameEndingWith("DTO")
            .check(importedClasses)
    }

    @Test
    fun layeredArchitectureShouldBeRespected() {
        layeredArchitecture()

            .layer("Controller").definedBy("..controllers..")
            .layer("Service").definedBy("..services..")
            .layer("Config").definedBy("..config..")
            .layer("Repository").definedBy("..repositories..")


            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service", "Config")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
            .check(importedClasses)
    }


    @Test
    fun repositoryClassesShouldHaveSpringRepositoryAnnotation() {
        classes()
            .that().resideInAPackage("..repository..")
            .should().beAnnotatedWith(Repository::class.java)
            .check(importedClasses)
    }

    @Test
    fun serviceClassesShouldHaveSpringServiceAnnotation() {
        classes()
            .that().resideInAPackage("..services..")
            .should().beAnnotatedWith(Service::class.java)
            .check(importedClasses)
    }

    @Test
    fun controllerClassesShouldHaveSpringControllerAnnotation() {
        classes()
            .that().resideInAPackage("..controllers..")
            .should().beAnnotatedWith(RestController::class.java)
            .check(importedClasses)
    }


}