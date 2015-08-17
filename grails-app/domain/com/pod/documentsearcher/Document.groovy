package com.pod.documentsearcher

class Document {

    static mapWith = "none"

    static hasMany = [pages:Page]

    String id
    HashMap metadata
    HashMap chapters

    static searchable = true

    static constraints = {
        metadata nullable: true
        chapters nullable: true
    }

}
