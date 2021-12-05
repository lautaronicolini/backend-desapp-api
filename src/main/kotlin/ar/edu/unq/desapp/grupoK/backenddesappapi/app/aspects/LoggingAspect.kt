package ar.edu.unq.desapp.grupoK.backenddesappapi.app.aspects

import org.aspectj.lang.ProceedingJoinPoint
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

    @Pointcut(("within(ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers..*)"))
    fun controllersPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    /*@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
        log.error(
            "Exception in {}.{}() with cause = {}", joinPoint.signature.declaringTypeName,
            joinPoint.signature.name, if (e.cause != null) e.cause else "NULL"
        )
    }*/

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("controllersPointcut()")
    @Throws(Throwable::class)
    fun logAround(joinPoint: ProceedingJoinPoint): Any {
        val start = System.currentTimeMillis()

        try {
            val result = joinPoint.proceed()
            val executionTime = System.currentTimeMillis() - start
            log.debug("----------------------------------------------------------------------------------------------------------------------------------------------------------------")
            log.debug("LOG AOP: {}.{}() with arguments = {} and result = {} - Execution duration: {}ms.", joinPoint.signature.declaringTypeName, joinPoint.signature.name, Arrays.toString(joinPoint.args), result, executionTime)
            log.debug("----------------------------------------------------------------------------------------------------------------------------------------------------------------")
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