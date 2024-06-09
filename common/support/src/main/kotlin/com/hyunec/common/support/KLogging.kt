package com.hyunec.common.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class KLogging {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)
}
