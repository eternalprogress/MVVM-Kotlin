package com.joker.mvvm.kotlin.basemvvm.net.exception

enum class ERROR(private val code:Int,private val err:String) {

    /**
     * 未知错误
     */
    UNKNOWN(10000, "未知错误"),
    /**
     * 解析错误
     */
    PARSE_ERROR(10001, "解析错误"),
    /**
     * 网络错误
     */
    NETWORD_ERROR(10002, "网络错误"),
    /**
     * 协议出错
     */
    HTTP_ERROR(10003, "协议出错"),

    /**
     * 证书出错
     */
    SSL_ERROR(10004, "证书出错"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(10006, "连接超时");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }


}