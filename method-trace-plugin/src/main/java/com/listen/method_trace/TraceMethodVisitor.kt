package com.listen.method_trace

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodVisitor(private var context: MethodTraceContext,
                         private var className: String, api: Int, mv: MethodVisitor?,
                         access: Int, var methodName: String?, desc: String?
) : AdviceAdapter(api, mv, access, methodName, desc) {

    override fun onMethodEnter() {
        super.onMethodEnter()
        if (methodName != null) {
            context.logger.i("TraceMethodVisitor", "onMethodEnter: className=$className method=${methodName}")
            mv.visitLdcInsn("$className#$methodName")
            mv.visitMethodInsn(INVOKESTATIC, "android/os/Trace", "beginSection", "(Ljava/lang/String;)V", false)
        }
    }

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitMethodInsn(INVOKESTATIC, "android/os/Trace", "endSection", "()V", false)
    }
}