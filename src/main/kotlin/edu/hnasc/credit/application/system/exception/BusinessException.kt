package edu.hnasc.credit.application.system.exception

data class BusinessException(
    override val message: String?) : RuntimeException(message) {}
