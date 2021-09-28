package ar.edu.unq.desapp.grupoK.backenddesappapi.h2

class DatabaseConfig {
    // edit security configs to use db as datasource to authenticate users
/*
    @Configuration
    class DatabaseConfig {

        @Value("\${spring.datasource.url}")
        private lateinit var datasourceUrl: String

        @Value("\${spring.datasource.driver-class-name}")
        private lateinit var dbDriverClassName: String

        @Value("\${spring.datasource.username}")
        private lateinit var dbUsername: String

        @Value("\${spring.datasource.password}")
        private lateinit var dbPassword: String

        @Bean
        fun dataSource(): DataSource =
            DriverManagerDataSource().apply {
                setDriverClassName(dbDriverClassName)
                url = datasourceUrl
                username = dbUsername
                password = dbPassword
            }
    }*/
}