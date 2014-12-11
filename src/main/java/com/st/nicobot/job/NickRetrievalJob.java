package com.st.nicobot.job;

import com.st.nicobot.job.task.NickRetrievalTask;
import org.picocontainer.annotations.Inject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Logs on 11-12-14.
 */
public class NickRetrievalJob extends AbstractJob {

    @Inject
    private NickRetrievalTask task;

    public NickRetrievalJob() {}

    public void start() {
        delay = TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
        startAtCreation = true;
        name = "NickRetrievalJob";

        super.task = task;
    }

}
