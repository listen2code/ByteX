package com.listen.method_trace

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.BaseContext
import org.gradle.api.Project

class MethodTraceContext(project: Project,
                         android: AppExtension, extension: MethodTraceExtension
) : BaseContext<MethodTraceExtension>(project, android, extension) {

    fun needTrace(className: String?, name: String?): Boolean {
        val noConstruct = name?.contains("<clinit>") == false && !name.contains("<init>")
        return noConstruct && isInTraceList(className)
    }

    private fun isInTraceList(className: String?): Boolean {
        if (className == null) return false
        traceList.forEach {
            if (className.contains(it)) {
                return true
            }
        }
        return false
    }

    var traceList = ArrayList<String>()

    override fun init() {
        super.init()
        traceList = extension.traceList as ArrayList<String>
    }
}