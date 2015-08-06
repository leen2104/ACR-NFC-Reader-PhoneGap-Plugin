package com.frankgreen.task;

import android.os.AsyncTask;

import com.frankgreen.apdu.command.UID;
import com.frankgreen.apdu.command.ntag.InitChip;
import com.frankgreen.apdu.command.ntag.NTagAuth;
import com.frankgreen.apdu.command.ntag.StartSession;
import com.frankgreen.apdu.command.ntag.StopSession;

/**
 * Created by kevin on 6/2/15.
 */
public class InitNTAGTask extends AsyncTask<InitNTAGParams, Void, Boolean> {

    final private String TAG = "InitNTAGTask";


    @Override
    protected Boolean doInBackground(InitNTAGParams... InitNTAGParamses) {
        InitNTAGParams initNTAGParams = InitNTAGParamses[0];
        if (initNTAGParams == null) {
            return false;
        }
        StartSession startSession = new StartSession(initNTAGParams);
        NTagAuth nTagAuth = new NTagAuth(initNTAGParams);
        InitChip initChip = new InitChip(initNTAGParams);
        StopSession stopSession = new StopSession(initNTAGParams);
        try{
            startSession.run();
            if(nTagAuth.run()) {
                initChip.run();
            }
        }finally {
            stopSession.run();
        }
        return true;
    }

}

