grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

elasticsearch.version = "1.7.1"
lucene.version = "4.10.4"
randomizedtestingrunner.version = "2.1.2"

grails.plugin.location.'elasticsearch' = "/Users/jtoddington/IdeaProjects/elasticsearch-grails-plugin"

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mysql:mysql-connector-java:5.1.27'
        compile 'com.itextpdf:itextpdf:5.5.6'

        def excludes = {
            excludes 'slf4j-simple', 'persistence-api', 'commons-logging', 'jcl-over-slf4j', 'slf4j-api', 'jta'
            excludes 'spring-core', 'spring-beans', 'spring-aop', 'spring-asm', 'spring-webmvc', 'spring-tx', 'spring-context', 'spring-web', 'log4j', 'slf4j-log4j12'
            excludes group: 'org.grails', name: 'grails-core'
            excludes group: 'org.grails', name: 'grails-gorm'
            excludes group: 'org.grails', name: 'grails-test'
            excludes group: 'xml-apis', name: 'xml-apis'
            excludes 'ehcache-core'
            transitive = false
        }

        def datastoreVersion = '3.1.1.RELEASE'

        provided("org.grails:grails-datastore-gorm-plugin-support:$datastoreVersion",
                "org.grails:grails-datastore-gorm:$datastoreVersion",
                "org.grails:grails-datastore-core:$datastoreVersion",
                "org.grails:grails-datastore-web:$datastoreVersion", excludes)

        runtime 'org.elasticsearch:elasticsearch-groovy:1.4.4'
        runtime "org.elasticsearch:elasticsearch-mapper-attachments:2.5.0"
        runtime 'com.spatial4j:spatial4j:0.4.1'

        compile 'com.vividsolutions:jts:1.13'

        test 'com.googlecode.json-simple:json-simple:1.1.1'

        /*
        compile "org.elasticsearch:elasticsearch:${elasticsearch.version}"

        test "com.carrotsearch.randomizedtesting:randomizedtesting-runner:${randomizedtestingrunner.version}"
        test ("org.apache.lucene:lucene-test-framework:${lucene.version}") {
            exclude group: 'com.carrotsearch.randomizedtesting', module: 'randomizedtesting-runner'
        }
        //test "org.elasticsearch:elasticsearch:${elasticsearch.version}"
        test (group: 'org.elasticsearch', name: 'elasticsearch', version: "${elasticsearch.version}", classifier: 'tests')
        */
    }

    plugins {
        build(":release:3.0.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }

        test(':hibernate:3.6.10.16', ':tomcat:7.0.54') {
            export = false
        }
        //runtime ':elasticsearch:0.0.4.6'
    }
}
