package com.pod.documentsearcher

import grails.transaction.Transactional
import org.apache.commons.lang.NotImplementedException
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfCopy

@Transactional
class IndexService {

    def TEMP_WORKING_FS = "test/resources/workspace" //TODO Look up from config
    def elasticSearchService

    def indexPdf(File f, String documentId) {

        PdfReader reader = new PdfReader(f.path);
        println "Pages: ${reader.getNumberOfPages()}"

        def workingFolderName = UUID.randomUUID().toString()

        //create the working folder.
        new File("${TEMP_WORKING_FS}/${workingFolderName}/").mkdirs()

        com.itextpdf.text.Document pdfDocument;
        com.pod.documentsearcher.Document document = new com.pod.documentsearcher.Document()
        document.id = documentId

        PdfCopy copy;
        int n = reader.getNumberOfPages();
        for (int i = 0; i < n; ) {
            pdfDocument = new com.itextpdf.text.Document();
            def pdfFile = new File("${TEMP_WORKING_FS}/${workingFolderName}/file_${++i}.pdf")
            copy = new PdfCopy(pdfDocument, pdfFile.newOutputStream())
            pdfDocument.open();
            copy.addPage(copy.getImportedPage(reader, i));
            pdfDocument.close();

            //Get the base64 encoded contents of file.
            Page page = new Page()
            page.filename = f.name
            page.contentType = "application/pdf"
            page.number = i
            page.id = "${workingFolderName}_${i}"
            page.parentDocumentId = documentId
            page.attachment = pdfFile.bytes.encodeBase64().toString()


            elasticSearchService.index(page)
            //document.addToPages(page)
        }

        elasticSearchService.index(document)
        //Add to the search index.
        return document

    }

    def findDocs(searchString){
        throw new NotImplementedException()
    }

    private def createUniqueWorkDirectory(){
        throw new NotImplementedException()
    }

    private def cleanUniqueWorkDirectory(){
        throw new NotImplementedException()
    }

}
