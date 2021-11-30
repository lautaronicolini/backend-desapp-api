package ar.edu.unq.desapp.grupoK.backenddesappapi.app.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class LoggingAspect() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut(
        "within(@org.springframework.stereotype.Repository *)" +
                " || within(@org.springframework.stereotype.Service *)" +
                " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    fun springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut(
        ("within(net.guides.springboot2.springboot2jpacrudexample..*)" +
                " || within(net.guides.springboot2.springboot2jpacrudexample.service..*)" +
                " || within(net.guides.springboot2.springboot2jpacrudexample.controller..*)")
    )
    fun applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
        log.error(
            "Exception in {}.{}() with cause = {}", joinPoint.signature.declaringTypeName,
            joinPoint.signature.name, if (e.cause != null) e.cause else "NULL"
        )
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    @Throws(Throwable::class)
    fun logAround(joinPoint: ProceedingJoinPoint): Any {
        if (log.isDebugEnabled) {
            log.debug(
                "Enter: {}.{}() with argument[s] = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, Arrays.toString(joinPoint.args)
            )
        }
        try {
            val result = joinPoint.proceed()
            if (log.isDebugEnabled) {
                log.debug(
                    "Exit: {}.{}() with result = {}", joinPoint.signature.declaringTypeName,
                    joinPoint.signature.name, result
                )
            }
            return result
        } catch (e: IllegalArgumentException) {
            log.error(
                "Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.args),
                joinPoint.signature.declaringTypeName, joinPoint.signature.name
            )
            throw e
        }
    }
}