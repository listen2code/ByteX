package com.listen.method_trace;

import com.ss.android.ugc.bytex.common.BaseExtension;

import java.util.ArrayList;
import java.util.List;

public class MethodTraceExtension extends BaseExtension {

    private List<String> traceList = new ArrayList<>();

    @Override
    public String getName() {
        return "MethodTracePlugin";
    }

    public List<String> getTraceList() {
        return traceList;
    }

    public void setTraceList(List<String> traceList) {
        this.traceList = traceList;
    }
}