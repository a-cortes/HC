package com.realestate.hcrawler;

import com.real_estates.util.CheckedFunction;
import com.realestate.hcrawler.webcontext.Requester;

import java.util.List;

public interface TaskSubmitter {
    List<CheckedFunction<Requester, List<Property>>> getTasks() throws Exception;
}
