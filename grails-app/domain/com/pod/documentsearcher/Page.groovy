package com.pod.documentsearcher

class Page {

    static mapWith = "none"

    String id
    String parentDocumentId
    Integer number
    String filename
    String contentType
    String attachment // base64 encoded file contents

    static searchable = {
        //all = [term_vector: 'with_positions_offsets']
        //attachment [attachment: true, term_vector: 'with_positions_offsets']
        attachment attachment: true, term_vector: 'with_positions_offsets'
        //"store": true,
        //"term_vector": "with_positions_offsets"
      }

    static constraints = {
        parentDocumentId: nullable: false
        filename nullable:false
        id nullable: false
        number nullable: false
        attachment nullable: false
        contentType nullable:false
        attachment nullable:false
    }

}
