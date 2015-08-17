// configuration for plugin testing - will not be included in the plugin zip

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

//elasticSearch.client.mode = 'local'

elasticSearch.client.hosts = [
        [host:'127.0.0.1', port:9301]
]

elasticsearch {
    disableAutoIndex = true
    bulkIndexOnStartup = false
    datastoreImpl = 'hibernateDatastore'
}

environments {
    development {
        /**
         * Possible values : "local", "node", "dataNode", "transport"
         * If set to null, "node" mode is used by default.
         */
        elasticSearch.client.mode = 'transport'

    }
    test {
        elasticSearch {
            client.mode = 'transport'
            disableAutoIndex = true
            bulkIndexOnStartup = false
            datastoreImpl = 'hibernateDatastore'
            cluster.name = 'elasticsearch_jtoddington'
            //index.store.type = 'memory' // store local node in memory and not on disk
            client.hosts = [
                    [host:'localhost', port:9301]
            ]
        }
    }
}

