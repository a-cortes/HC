package com.realestate.hcrawler;

import com.realestate.hcrawler.util.CheckedFunction;
import com.realestate.hcrawler.webcontext.Requester;

import java.util.List;

public interface TaskSubmitter {
    List<CheckedFunction<Requester, List<Property>>> getTasks() throws Exception;
}
