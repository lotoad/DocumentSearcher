package com.pod.documentsearcher

import com.pod.documentsearcher.IndexService
import grails.test.mixin.TestFor
import grails.test.spock.Integration
import grails.test.spock.IntegrationSpec
//import org.elasticsearch.test.ElasticsearchIntegrationTest
import spock.lang.Specification


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
//@TestFor(IndexService)
class IndexServiceSpec extends IntegrationSpec {


    def indexService
    static def TEST_PDFS_BASE_PATH = "test/resources"

    def setup() {

    }

    def cleanup() {

    }

    void "test passing a pdf to the index service and making sure it gets split into pages and they are in the search index"() {
        when:
        def f = new File("${TEST_PDFS_BASE_PATH}/SampleScannedDoc.pdf")
        def r = indexService.indexPdf(f,"SAMPLE_DOC_OBJ_ID")
        then:
        //Get the document and check that it has pages?
        def docs = indexService.findDocs("radiobutton")
        docs.size() == 1
    }

    void "test passing a non pdf doc to the index service and making sure it throws an exception"() {

    }
}
